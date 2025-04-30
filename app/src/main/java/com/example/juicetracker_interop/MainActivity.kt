package com.example.juicetracker_interop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.juicetracker_interop.ui.home.HomeScreen
import com.example.juicetracker_interop.ui.theme.JuiceTracker_InteropTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JuiceTracker_InteropTheme {
                HomeScreen()
            }
        }
    }
}