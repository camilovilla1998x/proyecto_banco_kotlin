package com.example.proyecto_banco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.contactos_page.*

class contactosPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.contactos_page)
        val id = intent.getLongExtra("id", 0)

        btn_crearContacto.setOnClickListener {
            val int = Intent(this, crearContactoPage::class.java)
            int.putExtra("id", id)
            startActivity(int)
        }
        btn_verContacto.setOnClickListener {
            val int = Intent(this, verContactoPage::class.java)
            int.putExtra("id", id)
            startActivity(int)
        }

    }
}