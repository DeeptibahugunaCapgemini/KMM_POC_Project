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
import androidx.compose.material3.Text
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
import com.mcdonalds.kmmagentcore.domain.model.ComponentType
import com.mcdonalds.kmmagentcore.domain.model.ResolvedComponent
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
                Text(
                    text = current.title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                current.components.forEach { RenderComponent(it) }
            }
        }
    }
}

/**
 * Recursively maps a resolved schema node to a native Compose widget.
 */
@Composable
fun RenderComponent(component: ResolvedComponent) {
    when (component.type) {
        ComponentType.COLUMN -> {
            // A column with a resolved background color is treated as a "card".
            if (component.colorHex != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = parseColor(component.colorHex)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    border = BorderStroke(1.dp, Color(0xFFE0E0E0))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(component.spacing.dp)
                    ) {
                        component.children.forEach { RenderComponent(it) }
                    }
                }
            } else {
                Column(
                    verticalArrangement = Arrangement.spacedBy(component.spacing.dp)
                ) {
                    component.children.forEach { RenderComponent(it) }
                }
            }
        }

        ComponentType.ROW -> Row(
            horizontalArrangement = Arrangement.spacedBy(component.spacing.dp)
        ) {
            component.children.forEach { RenderComponent(it) }
        }

        ComponentType.TEXT -> Text(
            text = component.text.orEmpty(),
            color = parseColor(component.colorHex),
            fontSize = (component.fontSize ?: 14).sp,
            fontWeight = if (component.fontWeight == "bold") FontWeight.Bold else FontWeight.Normal
        )

        ComponentType.BUTTON -> Button(
            onClick = { /* TODO: dispatch component.action?.type + payload */ },
            modifier = Modifier.padding(top = component.spacing.dp)
        ) {
            Text(component.text.orEmpty())
        }

        ComponentType.IMAGE,
        ComponentType.SPACER,
        ComponentType.UNKNOWN -> Unit
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

