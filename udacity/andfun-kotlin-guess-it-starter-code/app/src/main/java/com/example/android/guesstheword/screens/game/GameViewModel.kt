package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)

enum class BuzzType(val pattern: LongArray) {
    CORRECT(CORRECT_BUZZ_PATTERN),
    GAME_OVER(GAME_OVER_BUZZ_PATTERN),
    COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
    NO_BUZZ(NO_BUZZ_PATTERN)
}

class GameViewModel : ViewModel() {
    // The current word
    private var _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word
    private var _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinished

    //    val _buzzState
    private var _buzzState = MutableLiveData<BuzzType>()
    public val buzzState
        get() = _buzzState

    // The current score
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private var _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime
    val currentTimeString = Transformations.map(currentTime) {
        DateUtils.formatElapsedTime(it)
    }

    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>

    companion object {
        const val DONE = 0L

        const val ONE_SECOND = 1000L

        const val COUNTDOWN_TIME = 10000L
    }

    val timer: CountDownTimer

    init {
        Log.i("GameViewModel", "Created!")

        _score.value = 0
        _currentTime.value = COUNTDOWN_TIME / ONE_SECOND
        _eventGameFinished.value = false
        _buzzState.value = BuzzType.NO_BUZZ
        resetList()
        nextWord()

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = currentTime.value?.minus(1)
            }

            override fun onFinish() {
                _eventGameFinished.value = true
                _buzzState.value = BuzzType.GAME_OVER
            }
        }

        timer.start()
    }

    override fun onCleared() {
        Log.i("GameViewModel", "Destroyed!")

    }

    /**
     * Resets the list of words and randomizes the order
     */
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

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty())
            resetList()

        _word.value = wordList.removeAt(0)


    }

    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = _score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = _score.value?.plus(1)
        nextWord()
        buzzState.value = BuzzType.CORRECT
    }

    fun onGameFinishedComplete() {
        _eventGameFinished.value = false
    }

    fun onBuzzComplete() {
        _buzzState.value = BuzzType.NO_BUZZ
    }
}