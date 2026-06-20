package com.example

import android.app.Application
import com.example.di.Graph
import android.content.Intent
import android.os.Build
import com.example.service.RoutineService

class VolumeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
        
        startRoutineService()
    }
    
    private fun startRoutineService() {
        val intent = Intent(this, RoutineService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent)
        } else {
            startService(intent)
        }
    }
}
