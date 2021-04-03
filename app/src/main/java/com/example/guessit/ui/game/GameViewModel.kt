package com.example.guessit.ui.game

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var _currentWord = MutableLiveData<String>()
    val currentWord: LiveData<String>
        get() = _currentWord

    private var _currentScore = MutableLiveData<Int>()
    val currentScore: LiveData<Int>
        get() = _currentScore

    private val _gameFinished = MutableLiveData<Boolean>()
    val gameFinished: LiveData<Boolean>
        get() = _gameFinished

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L

        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L

        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000L
    }


    lateinit var wordList: MutableList<String>
    private val timer : CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    init {
        Log.i("GameViewModel", "GameViewModel created")
        _currentScore.value = 0
        _currentWord.value = ""
        _gameFinished.value = false
        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished / ONE_SECOND)
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _gameFinished.value = true
            }
        }

        timer.start()
    }


    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel cleared")
        timer.cancel()

    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            //gameFinished()
//            _gameFinished.value = true
            resetList()
            _currentTime.value = DONE
            _gameFinished.value = true
        }
        _currentWord.value = wordList.removeAt(0)

    }


    fun onSkip() {
        _currentScore.value = (currentScore.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _currentScore.value = (currentScore.value)?.plus(1)
        nextWord()
    }

    fun onGameFinishComplete() {
        _gameFinished.value = false
    }

}