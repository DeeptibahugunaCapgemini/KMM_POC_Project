import SwiftUI
import shared

struct ContentView: View {

    let viewModel = ScreenViewModel(screenId: "restaurant_menu")

    var body: some View {
        VStack {
            Text("Hello from KMM")
                .font(.title)

            Text(viewModel.screenId)
                .padding()
        }
    }
}

