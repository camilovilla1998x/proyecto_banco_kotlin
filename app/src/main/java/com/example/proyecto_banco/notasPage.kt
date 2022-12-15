package com.example.proyecto_banco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.notas_page.*

class notasPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notas_page)
        val id = intent.getLongExtra("id", 0)

        btnCrearNota.setOnClickListener {
            val int = Intent(this, crearNotaPage::class.java)
            int.putExtra("id", id)
            startActivity(int)
        }

        btnVerNota.setOnClickListener {
            val int = Intent(this, misNotasPage::class.java)
            int.putExtra("id", id)
            startActivity(int)
        }

    }
}