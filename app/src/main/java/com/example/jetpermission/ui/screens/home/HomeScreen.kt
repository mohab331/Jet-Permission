package com.example.jetpermission.ui.screens.home

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpermission.ui.screens.camera.CameraCaptureScreen
import com.example.jetpermission.ui.screens.camera.CameraPermissionScreen
import com.example.jetpermission.ui.screens.camera.view_model.CameraViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    viewModel: CameraViewModel = viewModel(),
    innerPaddingValues: PaddingValues = PaddingValues(0.dp)
) {
    val hasPermission by viewModel.hasPermission.collectAsState()
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    /*     It runs a block of suspend code only once (or when the key changes).
     Run coroutines safely inside Composable
     Run one-time logic like:
     1)  initialization
     2) permission request
     3) collecting flow
     4) triggering events
     5) Like code written in init state
     */
    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
        viewModel.updatePermissionStatus(cameraPermissionState.status.isGranted)
    }
    if (!hasPermission) {
        Box(modifier = Modifier.padding(innerPaddingValues)) {
            CameraPermissionScreen(
                cameraPermissionState,
                onPermissionGranted = {
                    viewModel.updatePermissionStatus(true)
                }
            )
        }
    } else {
        CameraCaptureScreen(
            viewModel,
        )
    }
}