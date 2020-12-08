package com.example.wizlan.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.wizlan.*
import com.example.wizlan.activities.Translator
import com.example.wizlan.activities.pdfScanner
import com.example.wizlan.activities.qrGenerator
import com.example.wizlan.activities.qrScanner


class HomeFragment : Fragment() {

    private var QrScanner: CardView? = null
    private var QrGenerator: CardView? = null
    private var PdfScanner: CardView? = null
    private var Trans: CardView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false);

        Trans = view.findViewById(R.id.cvtexttranslate)
        PdfScanner = view.findViewById(R.id.cvpdfscanner)
        QrScanner = view.findViewById(R.id.cvqrscanner)
        QrGenerator = view.findViewById(R.id.cvqrgenerator)

        PdfScanner!!.setOnClickListener {
            startActivity(Intent(activity, pdfScanner::class.java))
        }
        QrGenerator!!.setOnClickListener {
            startActivity(Intent(activity, qrGenerator::class.java))
        }
        QrScanner!!.setOnClickListener {
            startActivity(Intent(activity, qrScanner::class.java))
        }
        Trans!!.setOnClickListener {
            startActivity(Intent(activity, Translator::class.java))
        }

        return view


    }





}