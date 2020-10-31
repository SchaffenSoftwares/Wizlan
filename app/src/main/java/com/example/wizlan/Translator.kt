package com.example.wizlan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}