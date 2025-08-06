package com.example.jetpermission.ui.screens.camera.view_model

import android.content.Context
import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.example.jetpermission.ui.screens.camera.camer_helper.CameraController
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val cameraController: CameraController,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _hasPermission = MutableStateFlow(false)
    val hasPermission: StateFlow<Boolean> = _hasPermission

    private val _capturedImageUri = MutableStateFlow<Uri?>(null)
    val capturedImageUri: StateFlow<Uri?> = _capturedImageUri


    fun startCamera(previewView: PreviewView, lifecycleOwner: LifecycleOwner) {
        cameraController.startCamera(previewView, lifecycleOwner)
    }

    fun capturePhoto() {
        val file = File(
            context.cacheDir,
            "captured_photo_${System.currentTimeMillis()}.jpg"
        )
        cameraController.takePhoto(file) { uri ->
            _capturedImageUri.value = uri
        }
    }


    fun updatePermissionStatus(granted: Boolean) {
        _hasPermission.value = granted
    }
}
