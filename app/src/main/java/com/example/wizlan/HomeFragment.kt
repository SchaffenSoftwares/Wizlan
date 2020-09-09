package com.example.wizlan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*


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
            startActivity(Intent(activity,qrScanner::class.java))
        }
        Trans!!.setOnClickListener {
            startActivity(Intent(activity,Translator::class.java))
        }

        return view


    }





}