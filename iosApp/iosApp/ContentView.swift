import SwiftUI
import shared

/// Observes the shared AgentOrchestrator and exposes plain Swift state to SwiftUI.
/// All orchestration / parsing / schema resolution stays in the shared KMP layer.
@MainActor
final class ScreenViewModel: ObservableObject {
    @Published var title: String = ""
    @Published var components: [ResolvedComponent] = []
    @Published var errorMessage: String? = nil
    @Published var isLoading: Bool = true

    private let orchestrator: AgentOrchestrator
    private let observer: ScreenStateObserver

    init(screenId: String) {
        orchestrator = SharedModule.shared.provideOrchestrator()
        observer = ScreenStateObserver(orchestrator: orchestrator)
        observer.observe { [weak self] state in
            self?.apply(state: state)
        }
        orchestrator.load(screenId: screenId)
    }

    deinit {
        observer.dispose()
    }

    private func apply(state: ScreenState) {
        if let ready = state as? ScreenStateReady {
            title = ready.title
            components = ready.components
            errorMessage = nil
            isLoading = false
        } else if let error = state as? ScreenStateError {
            errorMessage = error.message
            isLoading = false
        } else {
            isLoading = true
        }
    }
}

struct ContentView: View {
    @StateObject private var viewModel = ScreenViewModel(screenId: "restaurant_menu")

    var body: some View {
        Group {
            if viewModel.isLoading {
                ProgressView()
            } else if let message = viewModel.errorMessage {
                Text("Something went wrong:\n\(message)")
                    .foregroundColor(.red)
                    .padding()
            } else {
                ScrollView {
                    VStack(alignment: .leading, spacing: 12) {
                        Text(viewModel.title)
                            .font(.system(size: 28, weight: .bold))
                        ForEach(viewModel.components, id: \.id) { component in
                            ComponentView(component: component)
                        }
                    }
                    .padding()
                    .frame(maxWidth: .infinity, alignment: .leading)
                }
            }
        }
    }
}

/// Recursively maps a resolved schema node to a native SwiftUI view.
struct ComponentView: View {
    let component: ResolvedComponent

    var body: some View {
        let type = component.type
        if type == ComponentType.column {
            VStack(alignment: .leading, spacing: CGFloat(Int(component.spacing))) {
                ForEach(component.children, id: \.id) { child in
                    ComponentView(component: child)
                }
            }
        } else if type == ComponentType.row {
            HStack(spacing: CGFloat(Int(component.spacing))) {
                ForEach(component.children, id: \.id) { child in
                    ComponentView(component: child)
                }
            }
        } else if type == ComponentType.text {
            Text(component.text ?? "")
                .font(.system(size: CGFloat(component.fontSize?.intValue ?? 14)))
                .fontWeight(component.fontWeight == "bold" ? .bold : .regular)
                .foregroundColor(Color(hex: component.colorHex))
        } else if type == ComponentType.button {
            Button(action: { /* TODO: dispatch component.action?.type + payload */ }) {
                Text(component.text ?? "")
            }
            .padding(.top, CGFloat(Int(component.spacing)))
        } else {
            EmptyView()
        }
    }
}

private extension Color {
    /// Builds a Color from a "#RRGGBB" / "#AARRGGBB" token value.
    init(hex: String?) {
        guard let raw = hex?.replacingOccurrences(of: "#", with: ""),
              let value = UInt64(raw, radix: 16) else {
            self = .black
            return
        }
        let a, r, g, b: Double
        if raw.count == 8 {
            a = Double((value & 0xFF000000) >> 24) / 255.0
            r = Double((value & 0x00FF0000) >> 16) / 255.0
            g = Double((value & 0x0000FF00) >> 8) / 255.0
            b = Double(value & 0x000000FF) / 255.0
        } else {
            a = 1.0
            r = Double((value & 0xFF0000) >> 16) / 255.0
            g = Double((value & 0x00FF00) >> 8) / 255.0
            b = Double(value & 0x0000FF) / 255.0
        }
        self = Color(.sRGB, red: r, green: g, blue: b, opacity: a)
    }
}

