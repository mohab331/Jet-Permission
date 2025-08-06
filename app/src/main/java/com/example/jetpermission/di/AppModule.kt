package com.example.jetpermission.di

import android.content.Context
import com.example.jetpermission.ui.screens.camera.camer_helper.CameraController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCameraController(@ApplicationContext context: Context): CameraController {
        return CameraController(context)
    }
}
