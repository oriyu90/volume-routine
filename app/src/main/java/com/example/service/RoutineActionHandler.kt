package com.example.service

import android.app.NotificationManager
import android.content.Context
import android.media.AudioManager
import android.util.Log
import com.example.data.Routine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object RoutineActionHandler {
    
    suspend fun executeRoutine(context: Context, routine: Routine, isReverting: Boolean = false) {
        withContext(Dispatchers.Main) {
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

            val dndAccess = notificationManager.isNotificationPolicyAccessGranted

            if (isReverting) {
                if (dndAccess) {
                    audioManager.ringerMode = if (routine.previousIsSilentMode) AudioManager.RINGER_MODE_SILENT else AudioManager.RINGER_MODE_NORMAL
                }
                if (routine.previousMediaVolume != -1) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, routine.previousMediaVolume, 0)
                }
                if (routine.previousAlarmVolume != -1) {
                    audioManager.setStreamVolume(AudioManager.STREAM_ALARM, routine.previousAlarmVolume, 0)
                }
                // STREAM_RING can only be touched with DND access (see setRingVolumeSafely).
                if (routine.previousRingtoneVolume != -1 && !routine.previousIsSilentMode) {
                    setRingVolumeSafely(audioManager, routine.previousRingtoneVolume, dndAccess)
                }
            } else {
                // Set Silent Mode (DND)
                if (routine.isSilentMode) {
                    if (dndAccess) {
                        audioManager.ringerMode = AudioManager.RINGER_MODE_SILENT
                    } else {
                        Log.w("RoutineAction", "DND access not granted.")
                    }
                } else if (routine.ringtoneVolume != -1) {
                    // If not silent mode but we need a specific ringtone volume, we need to ensure not in silent mode
                    if (dndAccess) {
                        audioManager.ringerMode = AudioManager.RINGER_MODE_NORMAL
                    }
                }

                // Set Media Volume
                if (routine.mediaVolume != -1) {
                    val maxMedia = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
                    val targetVol = (routine.mediaVolume.toFloat() / 100 * maxMedia).toInt()
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, targetVol, 0)
                }

                // Set Alarm Volume
                if (routine.alarmVolume != -1) {
                    val maxAlarm = audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM)
                    val targetVol = (routine.alarmVolume.toFloat() / 100 * maxAlarm).toInt()
                    audioManager.setStreamVolume(AudioManager.STREAM_ALARM, targetVol, 0)
                }

                // Set Ringtone Volume
                if (routine.ringtoneVolume != -1 && !routine.isSilentMode) {
                    val maxRing = audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)
                    val targetVol = (routine.ringtoneVolume.toFloat() / 100 * maxRing).toInt()
                    setRingVolumeSafely(audioManager, targetVol, dndAccess)
                }
            }
        }
    }

    /**
     * Changing STREAM_RING (especially towards 0) without notification policy
     * access throws SecurityException on Android 6+. Skip instead of crashing
     * when DND access hasn't been granted.
     */
    private fun setRingVolumeSafely(audioManager: AudioManager, targetVol: Int, dndAccess: Boolean) {
        if (!dndAccess) {
            Log.w("RoutineAction", "Skipping ringtone volume change: DND access not granted.")
            return
        }
        try {
            audioManager.setStreamVolume(AudioManager.STREAM_RING, targetVol, 0)
        } catch (e: SecurityException) {
            Log.w("RoutineAction", "Failed to set ringtone volume", e)
        }
    }
}
