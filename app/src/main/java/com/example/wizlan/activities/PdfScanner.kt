package com.example.wizlan.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wizlan.*
import com.example.wizlan.presentation.ScannerActivity
import kotlinx.android.synthetic.main.activity_pdf_scanner.*

class pdfScanner : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_scanner)

        cvpdfscan.setOnClickListener {
            startActivity(Intent(this, ScannerActivity::class.java))
            //finish()
        }
        cvtextreader.setOnClickListener {
            startActivity(Intent(this, TextreaderActivity::class.java))
            //finish()
        }
        cvtexttopdf.setOnClickListener {
            startActivity(Intent(this, TexttopdffileActivity::class.java))
            //finish()
        }
        cvtexttopdfconv.setOnClickListener {
            startActivity(Intent(this, TexttopdfconverterActivity::class.java))
            //finish()
        }
        cvsplitpdf.setOnClickListener {
            startActivity(Intent(this, SplitpdfActivity::class.java))
            //finish()
        }

    }
}