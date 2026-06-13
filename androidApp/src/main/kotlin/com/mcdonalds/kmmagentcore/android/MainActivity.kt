package com.mcdonalds.kmmagentcore.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mcdonalds.kmmagentcore.di.SharedModule

/**
 * Single Android entry point. UI is 100% native Jetpack Compose, while all
 * orchestration / parsing / schema resolution comes from the shared KMP layer.
 */
class MainActivity : ComponentActivity() {

    private val orchestrator by lazy { SharedModule.provideOrchestrator() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DynamicScreen(
                        orchestrator = orchestrator,
                        screenId = "restaurant_menu"
                    )
                }
            }
        }
    }
}

