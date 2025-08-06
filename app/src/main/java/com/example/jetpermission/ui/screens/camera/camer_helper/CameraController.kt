package com.example.jetpermission.ui.screens.camera.camer_helper

import android.content.Context
import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.io.File

/*
It’s a reusable Kotlin class in Android that:
Starts and configures the CameraX preview
Takes a photo and returns its URI
Is clean, reusable, and decoupled from the UI or ViewModel (like a service/helper in Flutter)
*/


// CameraController is a class responsible for all camera operations.

class CameraController(private val context: Context) {
    private var imageCapture: ImageCapture? = null

    // previewView is an instance of PreviewView — a special Android View used to display the live camera feed.
    // “Bind camera to this screen’s lifecycle so it knows when to start and stop.”
    fun startCamera(previewView: PreviewView, lifecycleOwner: LifecycleOwner) {
        // Asynchronously fetches the camera system.
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        // This is like saying: "when Camera is available, run this block."
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().apply {
                setSurfaceProvider(previewView.surfaceProvider)
            }

            // Initializes the capture functionality.
            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(context))
    }

    fun takePhoto(outputFile: File, onResult: (Uri?) -> Unit) {
        val capture = imageCapture ?: return

        // Tells CameraX to save to this specific file.
        val outputOptions = ImageCapture.OutputFileOptions.Builder(outputFile).build()

        capture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri ?: Uri.fromFile(outputFile)
                    onResult(savedUri)
                }

                override fun onError(exception: ImageCaptureException) {
                    exception.printStackTrace()
                    onResult(null)
                }
            }
        )
    }
}
