package com.example.proyecto_banco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_banco.base_de_datos.AppDataBase
import com.example.proyecto_banco.entidades.Contacto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.crear_contacto_page.*
import kotlinx.android.synthetic.main.registro_page.*

class crearContactoPage : AppCompatActivity() {
    private var db : AppDataBase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_contacto_page)
        val id = intent.getLongExtra("id", 0)
        btnGuardarContacto.setOnClickListener {
            db = AppDataBase.getDatabase(this)
            val NumeroContact : Long? = editTNumeroContact.text.toString().toLongOrNull()
            val nombreContacto: String = editTNombreContacto.text.toString()
            if(NumeroContact != null){
                val nuevoContacto= Contacto(NumeroContact, id, nombreContacto)
                db!!.contactoDAO().insert(nuevoContacto)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Toast.makeText(this, "Contacto agregado", Toast.LENGTH_SHORT).show()
                        val int = Intent(this, contactosPage::class.java)
                        int.putExtra("id", id)
                        startActivity(int)
                    },{
                        Toast.makeText(this, "Error al agregar - Contacto repetido, intente nuevamente", Toast.LENGTH_SHORT).show()
                    })
            }
            else{
                Toast.makeText(this, "Valores nulos, revise", Toast.LENGTH_SHORT).show()
            }

        }


    }
}