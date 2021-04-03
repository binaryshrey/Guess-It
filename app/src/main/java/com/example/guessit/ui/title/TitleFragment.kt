package com.example.guessit.ui.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.guessit.R
import com.example.guessit.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private lateinit var binding : FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_title,container,false)

        binding.playButton.setOnClickListener { view : View->
            Toast.makeText(context,"Play Clicked",Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }


}