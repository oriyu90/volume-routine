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
    var name by remember { mutableStateOf("") }
    var triggerType by remember { mutableStateOf("WIFI") }
    var ssid by remember { mutableStateOf("") }
    
    var isSilentMode by remember { mutableStateOf(false) }
    var mediaVolume by remember { mutableStateOf(-1f) }
    var alarmVolume by remember { mutableStateOf(-1f) }
    var ringtoneVolume by remember { mutableStateOf(-1f) }
    var hour by remember { mutableStateOf(-1) }
    var minute by remember { mutableStateOf(-1) }
    var daysOfWeek by remember { mutableStateOf("") }
    
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
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        OutlinedTextField(
                            value = if (hour == -1) "" else hour.toString(),
                            onValueChange = { hour = it.toIntOrNull() ?: -1 },
                            label = { Text(stringResource(R.string.time_hour)) },
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedTextField(
                            value = if (minute == -1) "" else minute.toString(),
                            onValueChange = { minute = it.toIntOrNull() ?: -1 },
                            label = { Text(stringResource(R.string.time_minute)) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    OutlinedTextField(
                        value = daysOfWeek,
                        onValueChange = { daysOfWeek = it },
                        label = { Text(stringResource(R.string.time_days)) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (triggerType == "WIFI") {
                    OutlinedTextField(
                        value = ssid,
                        onValueChange = { ssid = it },
                        label = { Text(stringResource(R.string.wifi_ssid)) },
                        modifier = Modifier.fillMaxWidth()
                    )
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
                                        daysOfWeek = daysOfWeek
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
