package com.example.qrstorage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.qrstorage.databinding.ActivityRegUsuariosBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import java.lang.ref.Reference

class regUsuarios : AppCompatActivity(), AdapterView.OnItemClickListener,
    AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityRegUsuariosBinding

    private lateinit var auth : FirebaseAuth

    private lateinit var dbReference : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_usuarios)

        binding = ActivityRegUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        dbReference = FirebaseDatabase.getInstance().getReference("Usuarios")

        val equipo = resources.getStringArray(R.array.equipo)
        val puesto = resources.getStringArray(R.array.puesto)
        val aptEquipo = ArrayAdapter(this, R.layout.list_item, equipo)
        val adpPuesto = ArrayAdapter(this, R.layout.list_item, puesto)



        with(binding.selectEquipo) {
            setAdapter(aptEquipo)
            onItemClickListener = this@regUsuarios
            //onItemSelectedListener = this@regUsuarios
        }

        with(binding.selectPuesto) {
            setAdapter(adpPuesto)
            onItemClickListener = this@regUsuarios
        }

       // aptEquipo.getPosition(onItemClick())

        binding.btnRegUsuario.setOnClickListener {

            /*val correo = binding.correoRegUsuario.text.toString()
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
            }*/



            val correo = binding.correoRegUsuario.text.toString()
            val contraseña = binding.contraseARegUsuario.text.toString()
            val nombreU = binding.nombre.text.toString()
            val aPaterno = binding.aPaterno.text.toString()
            val aMaterno = binding.aMaterno.text.toString()
            val equipo = binding.selectEquipo.text.toString()//: AutoCompleteTextView = binding.selectEquipo

            val puesto = binding.selectPuesto.text.toString()

            /*equipo.onItemClickListener = AdapterView.OnItemClickListener {parent, view, position, id ->
                val item1 = parent?.getItemAtPosition(position).toString()
                Toast.makeText(this,item1,Toast.LENGTH_SHORT).show()
                if (item1.equals("")){
                    binding.selectEquipo.setError("Requerid")
                }
            }*/

            if (correo.equals("") || contraseña.equals("") || nombreU.equals("") || aPaterno.equals("") || aMaterno.equals("") || equipo.equals("")
                || puesto.equals("")){
                validacion()
            }
            else{
                registroUsuarios(correo, contraseña, nombreU, aPaterno, aMaterno, equipo, puesto)
                limpiar()
            }

        }

    }

    private fun limpiar() {
        binding.correoRegUsuario.setText("")
        binding.contraseARegUsuario.setText("")
        binding.nombre.setText("")
        binding.aPaterno.setText("")
        binding.aMaterno.setText("")

    }

    private fun validacion() {
        val correo = binding.correoRegUsuario.text.toString()
        val contraseña = binding.contraseARegUsuario.text.toString()
        val nombreU = binding.nombre.text.toString()
        val aPaterno = binding.aPaterno.text.toString()
        val aMaterno = binding.aMaterno.text.toString()
        val equipo = binding.selectEquipo.text.toString()
        val puesto = binding.selectPuesto.onItemSelectedListener



        if(correo.equals("")){
            binding.correoRegUsuario.setError("Requerid")
        }
        else if(contraseña.equals("")){
            binding.contraseARegUsuario.setError("Requerid")
        }
        else if (contraseña.length <= 5){
            Toast.makeText(this,"La contraseña es muy corta",Toast.LENGTH_SHORT).show()
        }
        else if(nombreU.equals("")){
            binding.nombre.setError("Requerid")
        }
        else if(aPaterno.equals("")){
            binding.aPaterno.setError("Requerid")
        }
        else if(aMaterno.equals("")){
            binding.aMaterno.setError("Requerid")
        }
        else if(equipo.equals("")){
            binding.selectEquipo.setError("Requerid")
        }
        else if(puesto.equals("0")){
            binding.selectPuesto.setError("Requerid")
        }
    }

    private fun registroUsuarios(email : String, password : String, nombreU : String, aPaterno : String, aMaterno : String, equipo : String, puesto : String){
         auth.createUserWithEmailAndPassword(email, password)
             .addOnCompleteListener(this) { task ->
                 if (task.isSuccessful) {
                     // Sign in success, update UI with the signed-in user's information
                     val  usuario = Users(nombreU, aPaterno, aMaterno, equipo, puesto)
                     dbReference.child("Usuarios").setValue(usuario)
                     Log.d("TAG", "createUserWithEmail:success")
                     limpiar()
                     finish()
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
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position).toString()
        //val pos = parent?.getPositionForView(view).toString()
        //Toast.makeText(this,item,Toast.LENGTH_SHORT).show()
        //if (item == 0)
          //  binding.selectEquipo.setError("Requerid")
    }


    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            Toast.makeText(this,
                "OnItemSelectedListener : " + parent.getItemAtPosition(position).toString(),
                Toast.LENGTH_SHORT).show()
        };
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}

private fun AdapterView.OnItemSelectedListener.onItemSelected(){


}




