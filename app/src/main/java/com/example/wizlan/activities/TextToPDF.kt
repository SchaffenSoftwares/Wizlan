package com.example.wizlan.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wizlan.R
import kotlinx.android.synthetic.main.activity_text_to_p_d_f.*
import java.security.Permissions


class TextToPDF : AppCompatActivity() {

    private val STORAGE_CODE: Int = 100;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_to_p_d_f)

        //button click
        texttopdfConvert.setOnClickListener {
            //handling runtime permission for device Kitkat and above
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                    val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permissions, STORAGE_CODE)
                }
            }
        }
    }
}