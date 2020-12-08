package com.example.wizlan.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.wizlan.R
import com.example.wizlan.fragments.HomeFragment
import com.example.wizlan.utilityclasses.Constants.Companion.language
import kotlinx.android.synthetic.main.activity_translator.*

class Translator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translator)

        logoTransScreen.setOnClickListener {
            var mainFragment: HomeFragment = HomeFragment()
            supportFragmentManager.beginTransaction().add(R.id.container, mainFragment)
                .commit()
        }


        val langAdaptor= ArrayAdapter(this,android.R.layout.simple_spinner_item,language)
        translang.adapter=langAdaptor

    }
}