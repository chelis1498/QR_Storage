package com.example.qrstorage

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.FragmentManager
import com.example.qrstorage.databinding.FragmentCrearBinding
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference


class CrearFragment : Fragment() {

    private lateinit var binding: FragmentCrearBinding

    private lateinit var auth : FirebaseAuth

    private lateinit var dbReference : DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crear, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var listener: DatePickerDialog.OnDateSetListener? = null

        binding = FragmentCrearBinding.bind(view)

        binding.fechaCreacionP.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnCrearProyecto.setOnClickListener {

            val folio = binding.folioCrearProyecto.text.toString()
            val fechaCreacion = binding.fechaCreacionP.text.toString()
            val cantSamp = resources.getStringArray(R.array.cantMuestras)


            val adpCantSamp = ArrayAdapter(this, R.layout.list_item, cantSamp)
        }
    }


    private fun showDatePickerDialog() {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->
            // +1 because January is zero
            val selectedDate = day.toString() + " / " + (month + 1) + " / " + year
            binding.fechaCreacionP.setText(selectedDate)
        })

        newFragment.show(this.parentFragmentManager, "date picker")
    }


}