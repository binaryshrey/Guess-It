package com.example.guessit.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.FragmentGameBinding


class GameFragment : Fragment() {


    private var currentWord = ""
    private var currentScore = 0
    private lateinit var wordList : MutableList<String>

    private lateinit var binding : FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game,container,false)

        resetList()
        nextWord()

        binding.skipButton.setOnClickListener { view : View->
            Toast.makeText(context,"Skip Clicked", Toast.LENGTH_SHORT).show()
            onSkip()
        }
        binding.nextButton.setOnClickListener { view : View->
            Toast.makeText(context,"Next Clicked", Toast.LENGTH_SHORT).show()
            onCorrect()
        }

        return binding.root
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
            Toast.makeText(context,"Game Finished",Toast.LENGTH_SHORT).show()
            gameFinished()
        } else {
            currentWord = wordList.removeAt(0)
        }
        updateWordText()
        updateScoreText()
    }

    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(currentScore)
        findNavController(this).navigate(action)
    }

    private fun onSkip() {
        currentScore--
        nextWord()
    }

    private fun onCorrect() {
        currentScore++
        nextWord()
    }



    private fun updateWordText() {
        binding.wordTextView.text = currentWord

    }

    private fun updateScoreText() {
        binding.scoreTextView.text = "Score : " + currentScore.toString()
    }
}