package com.example.proyecto_banco

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto_banco.adaptadores.ListViewAdapter
import com.example.proyecto_banco.adaptadores.ListViewContactos
import com.example.proyecto_banco.base_de_datos.AppDataBase
import com.example.proyecto_banco.entidades.Contacto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.detalles_page.*
import kotlinx.android.synthetic.main.detalles_page.ListView
import kotlinx.android.synthetic.main.ver_contacto_page.*

class verContactoPage : AppCompatActivity() {

    private var db : AppDataBase? = null
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ver_contacto_page)

        db = AppDataBase.getDatabase(this)
        val id = intent.getLongExtra("id", 0)
        db!!.contactoDAO().getAll(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Aquí recorro todas las trasacciones que he hecho
                    var transx = ArrayList<Contacto>()

                for (tranx in it){
                    transx.add(Contacto(tranx.idContacto, id, tranx.nombreContacto))
                }
                val adapter = ListViewContactos(this, transx)
                ListViewContacto.adapter = adapter

            },{

            })

    }
}