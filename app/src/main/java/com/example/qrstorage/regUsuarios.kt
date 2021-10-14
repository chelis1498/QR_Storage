package com.example.qrstorage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.qrstorage.databinding.ActivityMainBinding
import com.example.qrstorage.databinding.ActivityRegUsuariosBinding

class regUsuarios : AppCompatActivity() {

    //val autCompEquipo = findViewById<AutoCompleteTextView>(R.id.selectEquipo)
    private lateinit var binding: ActivityRegUsuariosBinding

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