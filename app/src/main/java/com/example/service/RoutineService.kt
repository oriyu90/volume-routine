package com.example.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.net.wifi.WifiManager
import android.os.Build
import android.os.IBinder
import com.example.R
import com.example.di.Graph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.Calendar

class RoutineService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var wifiManager: WifiManager

    private val timeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == Intent.ACTION_TIME_TICK || intent.action == Intent.ACTION_TIME_CHANGED) {
                checkTimeRoutines()
            }
        }
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            checkWifiRoutines()
        }
        
        override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            checkWifiRoutines()
        }
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        val notification = Notification.Builder(this, "volume_routine_channel")
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.monitoring))
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()
            
        startForeground(1, notification)

        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_TIME_TICK)
            addAction(Intent.ACTION_TIME_CHANGED)
            addAction(Intent.ACTION_TIMEZONE_CHANGED)
        }
        registerReceiver(timeReceiver, filter)

        val request = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        connectivityManager.registerNetworkCallback(request, networkCallback)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeReceiver)
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun checkTimeRoutines() {
        serviceScope.launch {
            val c = Calendar.getInstance()
            val dayOfWeek = c.get(Calendar.DAY_OF_WEEK) // 1=Sunday, 2=Monday...
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val routines = Graph.repository.getEnabledRoutinesSync()
            routines.filter { it.triggerType == "TIME" }.forEach { routine ->
                val routineDays = routine.daysOfWeek.split(",").mapNotNull { it.toIntOrNull() }
                if (routineDays.contains(dayOfWeek) && routine.hour == hour && routine.minute == minute) {
                    val currentTs = System.currentTimeMillis()
                    // Avoid duplicate triggers in the same minute
                    if (currentTs - routine.lastTriggeredTs > 60_000) {
                        RoutineActionHandler.executeRoutine(this@RoutineService, routine)
                        Graph.repository.update(routine.copy(lastTriggeredTs = currentTs))
                    }
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun checkWifiRoutines() {
        serviceScope.launch {
            val connectionInfo = wifiManager.connectionInfo
            var ssid = connectionInfo.ssid
            if (ssid.startsWith("\"") && ssid.endsWith("\"")) {
                ssid = ssid.substring(1, ssid.length - 1)
            }

            val routines = Graph.repository.getEnabledRoutinesSync()
            routines.filter { it.triggerType == "WIFI" }.forEach { routine ->
                if (routine.ssid == ssid && ssid.isNotBlank() && ssid != "<unknown ssid>") {
                    val currentTs = System.currentTimeMillis()
                    // Prevent spamming when capabilities change frequently
                    if (currentTs - routine.lastTriggeredTs > 60_000) {
                        RoutineActionHandler.executeRoutine(this@RoutineService, routine)
                        Graph.repository.update(routine.copy(lastTriggeredTs = currentTs))
                    }
                }
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "volume_routine_channel",
                "Volume Routine Service",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }
}
