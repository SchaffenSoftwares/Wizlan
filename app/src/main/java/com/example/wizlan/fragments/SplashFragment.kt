package com.example.wizlan.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wizlan.R
import com.example.wizlan.utilityclasses.Constants
import kotlinx.android.synthetic.main.fragment_splash.view.*
import kotlin.random.Random


class SplashFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler().postDelayed({
            if(onBoardingFinished()){
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }else{
                findNavController().navigate(R.id.action_splashFragment_to_viewPagerFragment)
            }
        }, 5000)
        val view =inflater.inflate(R.layout.fragment_splash, container, false)
        val text = "WIZLAN"
        val random = Random.nextInt(Constants.tagLineSize)
        view.splashrandomn.text = Constants.tagLines[random]
        return view


        //Animation





    }
    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }


}