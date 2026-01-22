package com.rhythmlife.app.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rhythmlife.app.ui.MainViewModel

@Composable
fun LogScreen(vm: MainViewModel) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("記録一覧", style = MaterialTheme.typography.headlineMedium)

        if (vm.entries.isEmpty()) {
            Text("まだ記録がありません")
        } else {
            vm.entries.forEach { entry ->
                Card(Modifier.fillMaxWidth()) {
                    Column(
                        Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text("気分: ${entry.mood}")
                        Text("元気: ${entry.energy}")
                        if (entry.note.isNotBlank()) {
                            Text("メモ: ${entry.note}")
                        }
                    }
                }
            }
        }
    }
}
