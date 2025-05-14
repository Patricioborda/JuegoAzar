package com.example.juegoazar.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.juegoazar.data.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = DataStoreManager(application)

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    private val _maxScore = MutableStateFlow(0)
    val maxScore: StateFlow<Int> = _maxScore

    private val _randomNumber = MutableStateFlow(generateRandom())
    val randomNumber: StateFlow<Int> = _randomNumber

    private var failCount = 0

    init {
        viewModelScope.launch {
            dataStore.maxScore.collect {
                _maxScore.value = it
            }
        }
    }

    private fun generateRandom(): Int = Random.nextInt(1, 6)

    fun guessNumber(userGuess: Int) {
        if (userGuess == _randomNumber.value) {
            _score.value += 10
            failCount = 0
            if (_score.value > _maxScore.value) {
                viewModelScope.launch { dataStore.saveMaxScore(_score.value) }
            }
        } else {
            failCount++
            if (failCount >= 5) {
                _score.value = 0
                failCount = 0
            }
        }
        _randomNumber.value = generateRandom()
    }
}
