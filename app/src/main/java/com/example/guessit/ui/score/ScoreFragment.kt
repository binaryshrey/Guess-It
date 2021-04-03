package com.example.guessit.ui.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.guessit.R
import com.example.guessit.databinding.FragmentScoreBinding
import com.example.guessit.databinding.FragmentTitleBinding
//import com.example.guessit.ui.game.GameFragmentArgs


class ScoreFragment : Fragment() {

    private lateinit var binding : FragmentScoreBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_score,container,false)

        val args = ScoreFragmentArgs.fromBundle(requireArguments())
        binding.resultTextView.setText(args.currenScore.toString())
        return  binding.root
    }


}