package com.gillian.fotosbootcamp

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pick_button.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED){
                    val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                }
                else{
                    pickImageFromGalery()
                }
            }
            else{
                pickImageFromGalery()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGalery()
                }
                else{
                    Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
    private fun pickImageFromGalery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object{
        private val PERMISSION_CODE = 1000
        private val IMAGE_PICK_CODE = 1001
    }
}