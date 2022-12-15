package com.example.proyecto_banco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.home_page.*

class homePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        //Obtengo el id para volverlo a enviar a las siguientes páginas
        val id = intent.getLongExtra("id", 0)

        //A partir de cada botón que use el ususario lo llevo a la página que decida
        // y envío el id como referencia
        btnCerrarSesionHOME.setOnClickListener {
            val int = Intent(this, loginPage::class.java)
            startActivity(int)
        }

        btnVerSaldoHOME.setOnClickListener {
            val int = Intent(this, verSaldoPage::class.java)
            int.putExtra("id", id)
            startActivity(int)
        }

        btnRetirarSaldoHOME.setOnClickListener {
            val int = Intent(this, retirarSaldoPage::class.java)
            int.putExtra("id", id)
            startActivity(int)
        }

        btnEnviarDineroHOME.setOnClickListener {
            val int = Intent(this, enviarDineroPage::class.java)
            int.putExtra("id", id)
            startActivity(int)
        }

        btnContactos.setOnClickListener {

            val int = Intent(this, contactosPage::class.java)
            int.putExtra("id", id)
            startActivity(int)
        }

        btnNotas.setOnClickListener {

            val int = Intent(this, notasPage::class.java)
            int.putExtra("id", id)
            startActivity(int)

        }

    }
}