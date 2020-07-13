/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
private const val SCORE_STATE = "score_state"

class GameFragment : Fragment() {
    private lateinit var viewModel: GameViewModel


    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i("GameFragment", "Called Viewmodel!")
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )
        binding.gameViewModel = viewModel
        binding.setLifecycleOwner { this.lifecycle }

//        binding.correctButton.setOnClickListener {
//            viewModel.onCorrect()
//        }
//        binding.skipButton.setOnClickListener {
//            viewModel.onSkip()
//        }

        //obserlivedata
//        viewModel.score.observe(this, Observer {
//            binding.scoreText.text=it.toString()
//        })
//        viewModel.word.observe(this, Observer { word->
//            binding.wordText.text=word
//        })
        viewModel.eventGameFinish.observe(this, Observer { gameFished ->
            if (gameFished) {
                gameFinished()
                viewModel.onGameFinishedComplete()
            }
        })
        viewModel.buzzState.observe(this, Observer {
            if (it != BuzzType.NO_BUZZ)
                buzz(it.pattern)
        })
//        viewModel.currentTime.observe(this, Observer {
//            binding.timerText.text=DateUtils.formatElapsedTime(it)
//        })
//
//        updateScoreText()
//        updateWordText()
        return binding.root

    }


    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?: 0)
        findNavController(this).navigate(action)
    }


    /** Methods for updating the UI **/

    private fun updateWordText() {
        binding.wordText.text = viewModel.word.value

    }

    private fun updateScoreText() {

        binding.scoreText.text = viewModel.score.toString()
    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }


}