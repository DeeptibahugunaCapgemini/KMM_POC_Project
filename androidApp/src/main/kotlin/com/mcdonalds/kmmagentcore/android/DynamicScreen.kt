package com.mcdonalds.kmmagentcore.android

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mcdonalds.kmmagentcore.component.ChipItemComponent
import com.mcdonalds.kmmagentcore.component.McDonaldsTopBar
import com.mcdonalds.kmmagentcore.component.OrderInputBar
import com.mcdonalds.kmmagentcore.component.ProductItemCard
import com.mcdonalds.kmmagentcore.data.dto.*
import com.mcdonalds.kmmagentcore.data.dto.ContentSection
import com.mcdonalds.kmmagentcore.data.dto.Header
import com.mcdonalds.kmmagentcore.data.dto.Section
import com.mcdonalds.kmmagentcore.data.dto.TextComponent
import com.mcdonalds.kmmagentcore.data.dto.UIComponent
import com.mcdonalds.kmmagentcore.presentation.AgentOrchestrator
import com.mcdonalds.kmmagentcore.presentation.ScreenState

/**
 * Observes the shared [AgentOrchestrator] state and renders it natively.
 * The composable simply reacts to [ScreenState]; no business logic lives here.
 */
@Composable
fun DynamicScreen(
    orchestrator: AgentOrchestrator,
    screenId: String
) {
    val state by orchestrator.state.collectAsState()

    LaunchedEffect(screenId) {
        orchestrator.loadScreen(screenId)
    }

    when (val current = state) {
        is ScreenState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is ScreenState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Something went wrong:\n${current.message}",
                    color = Color.Red
                )
            }
        }

        is ScreenState.Ready -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                RenderScreen(
                    screen = current.components
                )

            }
        }
    }
}

/**
 * Recursively maps a resolved schema node to a native Compose widget.
 */
@Composable
fun RenderComponent(component: UIComponent) {

    when (component) {

        is TextComponent -> {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
                text = component.props.value
            )
        }

        is Header -> {
            McDonaldsTopBar(component.props.title)
        }

        is ChipComponent -> {
            ChipItemComponent(modifier = Modifier.padding(start = 16.dp), chipProps =component.props)
        }

        is Section -> {
            Column {
                component.components.forEach {
                    RenderComponent(it)
                }
            }
        }

        is ContentSection -> {
            Column {
                component.components.forEach {
                    RenderComponent(it)
                }
            }
        }

        is HeaderSection -> {
            Column {
                component.components.forEach {
                    RenderComponent(it)
                }
            }
        }

        is FooterSection -> {
            Column {
                component.components.forEach {
                    RenderComponent(it)
                }
            }
        }

        is ListComponent -> {
            Column {
                component.props.items.forEach {
                    RenderComponent(it)
                }
            }
        }

        is ProductCard -> {

            ProductItemCard(component.props)
        }

        is QuickActions -> {
            Column {

                // ✅ Top Divider
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp
                )

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .padding(start = 24.dp, top = 8.dp, bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // ✅ space between chips
                ) {
                    component.props.items.forEach {
                        ChipItemComponent(chipProps = it)
                    }
                }
                Divider(
                    color = Color.LightGray,
                    thickness = 1.dp
                )
            }
        }

        is InputBar -> {
            OrderInputBar(component.props.placeholder, component.props.actions)

        }
    }
}

@Composable
fun RenderScreen(screen: List<UIComponent>) {

    val header = screen.find { it is Header }
    val inputBar = screen.find { it is InputBar }
    val quickActions = screen.find { it is QuickActions }

    val content = screen.filter {
        it !is Header && it !is InputBar && it !is QuickActions
    }

    Scaffold(
        topBar = {
            (header as? Header)?.let {
                McDonaldsTopBar(it.props.title)
            }
        },
        bottomBar = {
            Column {
                (quickActions as? QuickActions)?.let {
                    RenderComponent(it)
                }

                (inputBar as? InputBar)?.let {
                    OrderInputBar(it.props.placeholder, it.props.actions)
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(content) { component ->
                RenderComponent(component)
            }
        }
    }
}


