package com.example.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.data.Routine
import com.example.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRoutineDialog(
    onDismiss: () -> Unit,
    onSave: (Routine) -> Unit
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    var name by remember { mutableStateOf("") }
    var triggerType by remember { mutableStateOf("WIFI") }
    var ssid by remember { mutableStateOf("") }
    
    var isSilentMode by remember { mutableStateOf(false) }
    var mediaVolume by remember { mutableStateOf(-1f) }
    var alarmVolume by remember { mutableStateOf(-1f) }
    var ringtoneVolume by remember { mutableStateOf(-1f) }
    var hour by remember { mutableStateOf(-1) }
    var minute by remember { mutableStateOf(-1) }
    val daysOfWeekSet = remember { mutableStateListOf<Int>() }
    
    var hasEndTime by remember { mutableStateOf(false) }
    var endHour by remember { mutableStateOf(-1) }
    var endMinute by remember { mutableStateOf(-1) }
    var revertOnEnd by remember { mutableStateOf(false) }

    val daysArray = context.resources.getStringArray(R.array.day_initials)

    Dialog(
        onDismissRequest = onDismiss,
        properties = androidx.compose.ui.window.DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxWidth(0.9f).widthIn(max = 400.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(stringResource(R.string.create_routine), style = MaterialTheme.typography.headlineSmall)
                
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(stringResource(R.string.routine_name)) },
                    modifier = Modifier.fillMaxWidth()
                )
                
                // Trigger Selection
                Text(stringResource(R.string.trigger), style = MaterialTheme.typography.titleMedium)
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    TriggerChip("TIME", triggerType) { triggerType = it }
                    TriggerChip("WIFI", triggerType) { triggerType = it }
                    TriggerChip("MANUAL", triggerType) { triggerType = it }
                }
                
                if (triggerType == "TIME") {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(stringResource(R.string.start_time))
                        Button(onClick = {
                            val dialog = android.app.TimePickerDialog(context, { _, h, m ->
                                hour = h
                                minute = m
                            }, if (hour != -1) hour else 12, if (minute != -1) minute else 0, true)
                            dialog.show()
                        }) {
                            Text(if (hour == -1) stringResource(R.string.select_time) else String.format("%02d:%02d", hour, minute))
                        }
                    }

                    Text(stringResource(R.string.days_of_week))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                        val dayMap = listOf(1 to daysArray[0], 2 to daysArray[1], 3 to daysArray[2], 4 to daysArray[3], 5 to daysArray[4], 6 to daysArray[5], 7 to daysArray[6])
                        dayMap.forEach { (code, label) ->
                            val selected = daysOfWeekSet.contains(code)
                            FilterChip(
                                selected = selected,
                                onClick = {
                                    if (selected) daysOfWeekSet.remove(code) else daysOfWeekSet.add(code)
                                },
                                label = { Text(label) }
                            )
                        }
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = hasEndTime, onCheckedChange = { hasEndTime = it })
                        Text(stringResource(R.string.has_end_time))
                    }

                    if (hasEndTime) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(stringResource(R.string.end_time))
                            Button(onClick = {
                                val dialog = android.app.TimePickerDialog(context, { _, h, m ->
                                    endHour = h
                                    endMinute = m
                                }, if (endHour != -1) endHour else 12, if (endMinute != -1) endMinute else 0, true)
                                dialog.show()
                            }) {
                                Text(if (endHour == -1) stringResource(R.string.select_time) else String.format("%02d:%02d", endHour, endMinute))
                            }
                        }
                        
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = revertOnEnd, onCheckedChange = { revertOnEnd = it })
                            Text(stringResource(R.string.revert_on_end))
                        }
                    }
                }

                if (triggerType == "WIFI") {
                    OutlinedTextField(
                        value = ssid,
                        onValueChange = { ssid = it },
                        label = { Text(stringResource(R.string.wifi_ssid)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(checked = revertOnEnd, onCheckedChange = { revertOnEnd = it })
                        Text(stringResource(R.string.revert_on_disconnect))
                    }
                }

                HorizontalDivider()

                // Actions
                Text(stringResource(R.string.actions), style = MaterialTheme.typography.titleMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = isSilentMode, onCheckedChange = { isSilentMode = it })
                    Text(stringResource(R.string.enable_silent_mode))
                }
                
                Text("${stringResource(R.string.media_volume)}: ${if (mediaVolume.toInt() == -1) stringResource(R.string.no_change) else "${mediaVolume.toInt()}%"}")
                Slider(
                    value = mediaVolume,
                    onValueChange = { mediaVolume = it },
                    valueRange = -1f..100f,
                    steps = 101
                )

                Text("${stringResource(R.string.alarm_volume)}: ${if (alarmVolume.toInt() == -1) stringResource(R.string.no_change) else "${alarmVolume.toInt()}%"}")
                Slider(
                    value = alarmVolume,
                    onValueChange = { alarmVolume = it },
                    valueRange = -1f..100f,
                    steps = 101
                )

                if (!isSilentMode) {
                    Text("${stringResource(R.string.ringtone_volume)}: ${if (ringtoneVolume.toInt() == -1) stringResource(R.string.no_change) else "${ringtoneVolume.toInt()}%"}")
                    Slider(
                        value = ringtoneVolume,
                        onValueChange = { ringtoneVolume = it },
                        valueRange = -1f..100f,
                        steps = 101
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) { Text(stringResource(R.string.cancel)) }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {
                            if (name.isNotBlank()) {
                                onSave(
                                    Routine(
                                        name = name,
                                        triggerType = triggerType,
                                        ssid = ssid,
                                        isSilentMode = isSilentMode,
                                        mediaVolume = mediaVolume.toInt(),
                                        alarmVolume = alarmVolume.toInt(),
                                        ringtoneVolume = ringtoneVolume.toInt(),
                                        hour = hour,
                                        minute = minute,
                                        daysOfWeek = daysOfWeekSet.joinToString(","),
                                        hasEndTime = hasEndTime,
                                        endHour = endHour,
                                        endMinute = endMinute,
                                        revertOnEnd = revertOnEnd
                                    )
                                )
                            }
                        },
                        enabled = name.isNotBlank()
                    ) {
                        Text(stringResource(R.string.save))
                    }
                }
            }
        }
    }
}

@Composable
private fun TriggerChip(label: String, selectedLabel: String, onSelect: (String) -> Unit) {
    FilterChip(
        selected = label == selectedLabel,
        onClick = { onSelect(label) },
        label = { Text(label) }
    )
}
