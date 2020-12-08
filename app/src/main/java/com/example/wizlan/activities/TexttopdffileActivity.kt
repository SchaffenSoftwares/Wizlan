package com.example.wizlan.activities

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.wizlan.R
import com.itextpdf.text.BaseColor
import com.itextpdf.text.Document
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.pdf.draw.LineSeparator
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.PermissionRequestErrorListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_texttopdffile.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class TexttopdffileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_texttopdffile)

        requestReadPermissions()
        val texttopdffileInput: EditText = findViewById(R.id.texttopdffileInput)
        val bt_txtpdfconv: Button = findViewById(R.id.bt_txtpdfconv)

        bt_txtpdfconv.setOnClickListener {
            savePdf()
        }
    }

    fun savePdf(){
        val mDoc = Document(PageSize.A4,8f,8f,8f,8f)
        val folder = File(Environment.getExternalStorageDirectory(),"PdfSample1")
        Log.e("MainActivity",folder.absolutePath)
        var success = true
        if(!folder.exists()){
            success = folder.mkdirs()
            Toast.makeText(this, "Folder created",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Folder exists",Toast.LENGTH_SHORT).show()
        }
        val mFileName = SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault())
            .format(System.currentTimeMillis())
        val filePath =folder.absolutePath + "/" + mFileName + ".pdf"
        try {
            PdfWriter.getInstance(mDoc,FileOutputStream(filePath))
            mDoc.open()
            val lineSeparator = LineSeparator()
            lineSeparator.lineColor = BaseColor.BLACK


            val mText = texttopdffileInput.text.toString()
            mDoc.addAuthor("Wizlan")
            mDoc.add(Paragraph(mText))

            mDoc.close()
            Toast.makeText(this, "Pdf Created at $mFileName.pdf at $filePath",Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
        }
    }
    private fun requestReadPermissions() {

        Dexter.withActivity(this)
            .withPermissions( Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    // check if all permissions are granted
                    if (report.areAllPermissionsGranted()) {
                        Toast.makeText(applicationContext, "All permissions are granted by user!", Toast.LENGTH_SHORT)
                            .show()
                    }

                    // check for permanent denial of any permission
                    if (report.isAnyPermissionPermanentlyDenied) {
                        // show alert dialog navigating to Settings
                        //openSettingsDialog();
                    }
                }

                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                    token.continuePermissionRequest()
                }
            }).withErrorListener(object : PermissionRequestErrorListener {
                override fun onError(error: DexterError) {
                    Toast.makeText(applicationContext, "Some Error!!! ", Toast.LENGTH_SHORT).show()
                }
            })
            .onSameThread()
            .check()
    }
}