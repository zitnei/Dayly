package com.rhythmlife.app.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rhythmlife.app.ui.MainViewModel

@Composable
fun HomeScreen(vm: MainViewModel) {

    // 最新の1件を取得
    val latest = vm.entries.firstOrNull()

    val score = latest?.energy ?: 0
    val (c1, c2) = moodColors(latest?.mood ?: 2)

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("RhythmLife", style = MaterialTheme.typography.headlineMedium)

        Card(Modifier.fillMaxWidth()) {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("今日のリズム", style = MaterialTheme.typography.titleMedium)
                Text("スコア: $score / 100", style = MaterialTheme.typography.bodyLarge)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.fillMaxSize()) {
                        val center = Offset(size.width / 2f, size.height / 2f)
                        val radius = size.minDimension * 0.32f

                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(c1, c2, Color.Black.copy(alpha = 0.2f)),
                                center = center,
                                radius = radius * 1.8f
                            ),
                            radius = radius,
                            center = center
                        )

                        val ringRadius = radius * 1.35f
                        val sweep = (score / 100f) * 360f

                        drawArc(
                            brush = Brush.linearGradient(listOf(c2, c1)),
                            startAngle = -90f,
                            sweepAngle = sweep,
                            useCenter = false,
                            topLeft = Offset(center.x - ringRadius, center.y - ringRadius),
                            size = androidx.compose.ui.geometry.Size(ringRadius * 2, ringRadius * 2),
                            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 18f)
                        )
                    }
                }

                val note = latest?.note?.takeIf { it.isNotBlank() }
                    ?: "まだ記録がありません。Addから入れてください。"

                Text("メモ: $note")
            }
        }
    }
}

private fun moodColors(mood: Int): Pair<Color, Color> = when (mood.coerceIn(0, 4)) {
    0 -> Color(0xFF2E335A) to Color(0xFF0B0F2A)
    1 -> Color(0xFF3B4CCA) to Color(0xFF18235F)
    2 -> Color(0xFF6A5ACD) to Color(0xFF2B1E5C)
    3 -> Color(0xFFFF7A18) to Color(0xFFFFC300)
    else -> Color(0xFF00C853) to Color(0xFFB2FF59)
}
