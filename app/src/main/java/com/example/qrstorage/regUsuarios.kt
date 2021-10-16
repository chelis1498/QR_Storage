package com.example.qrstorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import com.example.qrstorage.databinding.ActivityMainBinding
import com.example.qrstorage.databinding.ActivityRegUsuariosBinding
import com.google.firebase.auth.FirebaseAuth

class regUsuarios : AppCompatActivity() {

    private lateinit var binding: ActivityRegUsuariosBinding

    private lateinit var logAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_usuarios)

        binding = ActivityRegUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val equipo = resources.getStringArray(R.array.equipo)
        val puesto = resources.getStringArray(R.array.puesto)
        val aptEquipo = ArrayAdapter(this, R.layout.list_item, equipo)
        val adpPuesto = ArrayAdapter(this, R.layout.list_item, puesto)


        //autCompEquipo.setAdapter(aptEquipo)

        with(binding.selectEquipo){
            setAdapter(aptEquipo)
        }

        with(binding.selectPuesto){
            setAdapter(adpPuesto)
        }
    }
}