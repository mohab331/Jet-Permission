package com.example.jetpermission

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpermission.ui.screens.camera.view_model.CameraViewModel
import com.example.jetpermission.ui.screens.home.HomeScreen
import com.example.jetpermission.ui.theme.JetPermissionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val cameraViewModel = viewModel<CameraViewModel>()
            JetPermissionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(cameraViewModel,innerPaddingValues = innerPadding,)
                }
            }
        }
    }
}