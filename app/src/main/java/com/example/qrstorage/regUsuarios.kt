package com.example.qrstorage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.qrstorage.databinding.ActivityRegUsuariosBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class regUsuarios : AppCompatActivity() {

    private lateinit var binding: ActivityRegUsuariosBinding

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_usuarios)

        binding = ActivityRegUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val equipo = resources.getStringArray(R.array.equipo)
        val puesto = resources.getStringArray(R.array.puesto)
        val aptEquipo = ArrayAdapter(this, R.layout.list_item, equipo)
        val adpPuesto = ArrayAdapter(this, R.layout.list_item, puesto)



        with(binding.selectEquipo) {
            setAdapter(aptEquipo)
        }

        with(binding.selectPuesto) {
            setAdapter(adpPuesto)
        }

        binding.btnRegUsuario.setOnClickListener {

            val correo = binding.correoRegUsuario.text.toString()
            val contrasena = binding.contraseARegUsuario.text.toString()


            when {
                correo.isEmpty() || contrasena.isEmpty() -> {
                    Toast.makeText(
                        baseContext, "Ingrese una cuenta de correo valida.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    Toast.makeText(
                        baseContext, " Correo: $correo Contraseña: $contrasena",
                        Toast.LENGTH_SHORT
                    ).show()
                    registroUsuarios(correo, contrasena)
                    reload()
                }
            }
        }

    }
     private fun registroUsuarios(email : String, password : String){
         auth.createUserWithEmailAndPassword(email, password)
             .addOnCompleteListener(this) { task ->
                 if (task.isSuccessful) {
                     // Sign in success, update UI with the signed-in user's information
                     Log.d("TAG", "createUserWithEmail:success")
                     val user = auth.currentUser
                 } else {
                     // If sign in fails, display a message to the user.
                     Log.w("TAG", "createUserWithEmail:failure", task.exception)
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