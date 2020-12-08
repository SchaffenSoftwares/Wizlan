package com.example.wizlan.activities

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.SparseArray
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.isNotEmpty
import com.example.wizlan.R
import com.example.wizlan.fragments.HomeFragment
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_qr_scanner.*
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_qr_scanner.view.*


class qrScanner : AppCompatActivity() {

    private val requestCodeCameraPermission = 1001
    private lateinit var cameraSource: CameraSource
    private lateinit var detector: BarcodeDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner)
        if (ContextCompat.checkSelfPermission(
                this@qrScanner,
              android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ){
            askForCameraPermission()
        } else {
            setupControls()
        }

        logoQrScreen.setOnClickListener {
            var mainFragment: HomeFragment = HomeFragment()
            supportFragmentManager.beginTransaction().add(R.id.container, mainFragment)
                .commit()
        }
    }

    private fun setupControls(){
        detector = BarcodeDetector.Builder(this@qrScanner).build()
        cameraSource = CameraSource.Builder(this@qrScanner, detector)
        .setAutoFocusEnabled(true)
            .build()
        cameraSurfaceView.holder.addCallback(surfaceCallBack)
        detector.setProcessor(processor)

    }

    private fun askForCameraPermission(){
        ActivityCompat.requestPermissions(
            this@qrScanner,
            arrayOf(android.Manifest.permission.CAMERA),
            requestCodeCameraPermission
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCodeCameraPermission && grantResults.isEmpty())
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                setupControls()
            }else {
                Toast.makeText(applicationContext,"Permission Denied",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val surfaceCallBack = object :SurfaceHolder.Callback{

        override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
            try {
                if (ActivityCompat.checkSelfPermission(
                        this@qrScanner,
                        android.Manifest.permission.CAMERA
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                cameraSource.start(surfaceHolder)
            }catch (exception:Exception){
                Toast.makeText(applicationContext,"Something went wrong", Toast.LENGTH_SHORT).show()
            }

        }

        override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

        }

        override fun surfaceDestroyed(p0: SurfaceHolder) {
            cameraSource.stop()
        }
    }
    private val processor = object : Detector.Processor<Barcode>{

        override fun release() {

        }

        override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
            if (detections != null && detections.detectedItems.isNotEmpty()){
                val qrCodes: SparseArray<Barcode> = detections.detectedItems
                val code = qrCodes.valueAt(0)
               scanResult.text = code.displayValue
                //val scanResult: TextView = findViewById(R.id.textView)
                //val text :String=code.displayValue
                //scanResult.movementMethod = ScrollingMovementMethod()
              //  scanResult.text=  text
               // scanResult.movementMethod = ScrollingMovementMethod()
            }else{
                scanResult.text=""
            }
        }
    }
}