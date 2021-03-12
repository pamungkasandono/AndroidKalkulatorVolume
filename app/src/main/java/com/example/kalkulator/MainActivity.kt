package com.example.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtPanjang: EditText
    private lateinit var edtLebar: EditText
    private lateinit var edtTinggi: EditText
    private lateinit var tvHasil: TextView

    companion object {
        private const val STATE_RES = "state_res"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtPanjang = findViewById(R.id.edt_panjang)
        edtLebar = findViewById(R.id.edt_lebar)
        edtTinggi = findViewById(R.id.edt_tinggi)
        tvHasil = findViewById(R.id.tv_hasil)
        val btnHasil = findViewById<Button>(R.id.btn_hitung)

        btnHasil.setOnClickListener(this)

        if (savedInstanceState !== null) {
            val res = savedInstanceState.getString(STATE_RES)
            tvHasil.text = res
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_hitung) {
            val inputPnj = edtPanjang.text.toString().trim()
            val inputLbr = edtLebar.text.toString().trim()
            val inputTinggi = edtTinggi.text.toString().trim()

            var notEmpty = true
            when {
                inputPnj.isEmpty() -> {
                    notEmpty = false
                    edtPanjang.requestFocus()
                    edtPanjang.error = "Field panjang tidak boleh kosong"
                }
                inputLbr.isEmpty() -> {
                    notEmpty = false
                    edtLebar.requestFocus()
                    edtLebar.error = "Field lebar tidak boleh kosong"
                }
                inputTinggi.isEmpty() -> {
                    notEmpty = false
                    edtTinggi.requestFocus()
                    edtTinggi.error = "Field tinggi tidak boleh kosong"
                }
            }

            if (notEmpty) {
                val volume = inputPnj.toDouble() * inputLbr.toDouble() * inputTinggi.toDouble()
                tvHasil.text = volume.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RES, tvHasil.text.toString())
    }
}