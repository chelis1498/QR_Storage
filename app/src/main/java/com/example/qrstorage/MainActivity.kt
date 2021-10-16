package com.example.qrstorage

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.qrstorage.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

import com.google.firebase.ktx.Firebase

import android.content.Intent





class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    private lateinit var logAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        logAuth = Firebase.auth

        binding.btnRegistro.setOnClickListener {
            val intent = Intent (this, regUsuarios::class.java)
            this.startActivity(intent)
        }

        binding.btnLogIn.setOnClickListener(){
            val uEmail = binding.logEmail.text.toString()
            val uPass = binding.logPass.text.toString()

            when {
                uEmail.isEmpty() || uPass.isEmpty() -> {
                    Toast.makeText(baseContext, "Correo o contraseña icorrectos.",
                        Toast.LENGTH_SHORT).show()
                } else -> {
                SignIn(uEmail, uPass)
            }
            }
        }

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = logAuth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun SignIn(email : String, contraseña : String){
        logAuth.signInWithEmailAndPassword(email, contraseña)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = logAuth.currentUser
                    reload()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun reload() {
        val intent = Intent (this, principal::class.java)
        this.startActivity(intent)
    }

}