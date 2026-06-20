package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routines")
data class Routine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val triggerType: String, // "TIME", "WIFI", "MANUAL"
    
    // For TIME type, comma-separated java.time.DayOfWeek names
    val daysOfWeek: String = "", 
    val hour: Int = -1,
    val minute: Int = -1,
    
    // End time for TIME type
    val hasEndTime: Boolean = false,
    val endHour: Int = -1,
    val endMinute: Int = -1,
    
    // For WIFI type
    val ssid: String = "",
    
    // Actions
    val isSilentMode: Boolean = true,
    val mediaVolume: Int = -1, // -1 means no change
    val alarmVolume: Int = -1,
    val ringtoneVolume: Int = -1,
    
    val isEnabled: Boolean = true,
    val lastTriggeredTs: Long = 0L, // To avoid spamming
    
    // Revert settings tracking
    val revertOnEnd: Boolean = false,
    val isActive: Boolean = false, // True if the routine's conditions are currently met and actions are applied
    
    // State to revert to
    val previousIsSilentMode: Boolean = false,
    val previousMediaVolume: Int = -1,
    val previousAlarmVolume: Int = -1,
    val previousRingtoneVolume: Int = -1
)
