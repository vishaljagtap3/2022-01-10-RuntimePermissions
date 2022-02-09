package com.bitcode.permissions

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.bitcode.permissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSms.setOnClickListener {
            //if (checkSelfPermission(Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) == PackageManager.PERMISSION_GRANTED) {
                smsAction()
            } else {
                /*requestPermissions(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    1
                )*/
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.READ_SMS
                    ),
                    1
                )
            }
        }

        binding.btnStorage.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE) == PackageManager.PERMISSION_GRANTED) {
                storageAction()
            }
            else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf( Manifest.permission.READ_EXTERNAL_STORAGE),
                    2
                )
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1) {
            if(permissions[0].equals(Manifest.permission.READ_SMS)) {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    smsAction()
                }
                else {
                    mt("SMS Permission denied!");
                }
            }
        }
        if(requestCode == 2) {
            if(permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    storageAction()
                }
                else {
                    mt("Storage permissions denied")
                }
            }
        }
    }

    private fun storageAction() {
        mt("R/W Storage");
    }

    private fun smsAction() {
        mt("Sms sent and read!")
    }

    private fun mt(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}