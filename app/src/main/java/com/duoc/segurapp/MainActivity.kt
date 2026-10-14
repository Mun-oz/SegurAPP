package com.duoc.segurapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.duoc.segurapp.ui.home.HomeScreen
import com.duoc.segurapp.ui.theme.SegurAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SegurAPPTheme {
                HomeScreen()
            }
        }
    }
}
