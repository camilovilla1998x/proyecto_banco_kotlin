package com.example.proyecto_banco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyecto_banco.adaptadores.ListViewAdapter
import com.example.proyecto_banco.base_de_datos.AppDataBase
import com.example.proyecto_banco.entidades.Detalles
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.detalles_page.*

class detallesTransaccionPage : AppCompatActivity() {

    //Creo una variable para usar la base de datos
    private var db : AppDataBase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalles_page)
        db = AppDataBase.getDatabase(this)
        val id = intent.getLongExtra("id", 0)

        db!!.transaccionDAO().getAll(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Aquí recorro todas las trasacciones que he hecho
                    var tranx = ArrayList<Detalles>()
                       for (trans in it){
                           tranx.add(Detalles(trans.monto.toInt(), trans.idUsuario, trans.fecha))
                       }
                        val adapter = ListViewAdapter(this, tranx)
                        ListView.adapter = adapter

            },{

            })

        btnCerrarSesionDETALLES.setOnClickListener {
            val int = Intent(this, loginPage::class.java)
            startActivity(int)
        }


    }
}