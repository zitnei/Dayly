package com.rhythmlife.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt
import com.rhythmlife.app.ui.MainViewModel
@Composable
fun AddScreen(vm: MainViewModel) {
    var mood by remember { mutableStateOf(2f) }
    var energy by remember { mutableStateOf(60f) }
    var note by remember { mutableStateOf("") }
    var savedToast by remember { mutableStateOf<String?>(null) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("今日の記録", style = MaterialTheme.typography.headlineSmall)

        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("気分（0=重い / 4=最高）: ${mood.roundToInt()}")
                Slider(value = mood, onValueChange = { mood = it }, valueRange = 0f..4f, steps = 3)

                Text("元気（0..100）: ${energy.roundToInt()}")
                Slider(value = energy, onValueChange = { energy = it }, valueRange = 0f..100f)

                OutlinedTextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("メモ（短く）") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        vm.add(mood.roundToInt(), energy.roundToInt(), note)

                        savedToast = "保存しました"
                        note = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("保存")
                }

                savedToast?.let { Text(it) }
            }
        }
    }
}
