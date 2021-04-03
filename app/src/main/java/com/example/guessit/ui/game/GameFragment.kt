package com.example.guessit.ui.game

import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding : FragmentGameBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_game,container,false)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.currentScore.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreTextView.text = "Score : " + newScore.toString()

        })
//        viewModel.currentWord.observe(viewLifecycleOwner, Observer { newWord ->
//            binding.wordTextView.text = newWord
//        })

        viewModel.gameFinished.observe(viewLifecycleOwner, Observer { hasFinished ->
            if (hasFinished){
                Toast.makeText(context,"Game Finished",Toast.LENGTH_SHORT).show()
                gameFinished()
                viewModel.onGameFinishComplete()
            }
        })

        viewModel.currentTime.observe(viewLifecycleOwner, Observer { newTime ->
            binding.timerTextView.text = DateUtils.formatElapsedTime(newTime)

        })

//        binding.skipButton.setOnClickListener { view : View->
//            Toast.makeText(context,"Skip Clicked", Toast.LENGTH_SHORT).show()
//            viewModel.onSkip()
//
//        }
//        binding.nextButton.setOnClickListener { view : View->
//            Toast.makeText(context,"Next Clicked", Toast.LENGTH_SHORT).show()
//            viewModel.onCorrect()
//
//        }


        return binding.root
    }



    private fun gameFinished() {
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(viewModel.currentScore.value?: 0)
        findNavController(this).navigate(action)
    }





}