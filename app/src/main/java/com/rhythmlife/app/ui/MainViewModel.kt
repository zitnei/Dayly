package com.rhythmlife.app.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.rhythmlife.app.data.EmotionEntry

class MainViewModel : ViewModel() {

    // 感情ログの一覧（画面が自動更新されるリスト）
    val entries = mutableStateListOf<EmotionEntry>()

    // 新しい記録を追加する
    fun add(mood: Int, energy: Int, note: String) {
        entries.add(
            0,
            EmotionEntry(
                time = System.currentTimeMillis(),
                mood = mood,
                energy = energy,
                note = note
            )
        )
    }
}
