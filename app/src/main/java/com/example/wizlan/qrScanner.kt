package com.example.wizlan

import android.R.attr
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.AttributeSet
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.isEmpty
import androidx.core.util.isNotEmpty
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_qr_scanner.*
import java.util.*
import java.util.jar.Manifest


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
            }else{
                scanResult.text=""
            }
        }
    }
}