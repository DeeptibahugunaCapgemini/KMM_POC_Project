package com.mcdonalds.kmmagentcore.android

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                current.components.forEach { RenderComponent(it) }
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
                text = component.props.value
            )
        }

        is Header -> {
            Text(
                text = component.props.title,
                style = MaterialTheme.typography.titleLarge
            )
        }

        is ChipComponent -> {
            Button(onClick = { /* handle action */ }) {
                Text(component.props.label)
            }
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
            Column {
                Text(text = component.props.title)

                component.props.actions.forEach {
                    Button(onClick = { /* handle action */ }) {
                        Text(it.label)
                    }
                }
            }
        }

        is QuickActions -> {
            Row {
                component.props.items.forEach {
                    Button(onClick = { }) {
                        Text(it.label)
                    }
                }
            }
        }

        is InputBar -> {
            Row {
                TextField(
                    value = "",
                    onValueChange = {},
                    placeholder = {
                        Text(component.props.placeholder)
                    }
                )

                component.props.actions.forEach {
                    Button(onClick = { }) {
                        Text(it.label)
                    }
                }
            }
        }
    }
}
/** Converts a "#RRGGBB" / "#AARRGGBB" token value into a Compose [Color]. */
private fun parseColor(hex: String?): Color {
    val cleaned = hex?.removePrefix("#") ?: return Color.Black
    val value = cleaned.toLongOrNull(16) ?: return Color.Black
    return when (cleaned.length) {
        6 -> Color(0xFF000000 or value)
        8 -> Color(value)
        else -> Color.Black
    }
}

