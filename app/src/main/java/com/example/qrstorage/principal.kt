package com.example.qrstorage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.qrstorage.databinding.ActivityMainBinding
import com.example.qrstorage.databinding.ActivityPrincipalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.zxing.integration.android.IntentIntegrator as IntentIntegrator1

class principal : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnCerrarSesion.setOnClickListener {
            signOut()
        }

        binding.btnQr.setOnClickListener {
            setUpQr()
        }
    }

    private fun setUpQr() {
        IntentIntegrator1(this)
            .setBeepEnabled(true)
            .setDesiredBarcodeFormats(IntentIntegrator1.QR_CODE)
            .setPrompt("Scan Qr")
            .initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val resultado = IntentIntegrator1.parseActivityResult(requestCode, resultCode, data)

        if (resultado != null){
            if (resultado.contents !== null){
                binding.textView2.text = resultado.contents
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun signOut() {
        Firebase.auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}