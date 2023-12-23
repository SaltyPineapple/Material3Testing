package com.learn.material3testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.learn.material3testing.ui.theme.Material3TestingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Material3TestingTheme {
                Material3TestingApp()
            }
        }
    }
}
