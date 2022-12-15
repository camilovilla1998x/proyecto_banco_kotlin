package com.example.proyecto_banco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_banco.base_de_datos.AppDataBase
import com.example.proyecto_banco.entidades.Notas
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.crear_nota_page.*

class crearNotaPage : AppCompatActivity() {
    private var db : AppDataBase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_nota_page)
        //Recibo el id
        val id = intent.getLongExtra("id", 0)

        btnValidar.setOnClickListener {
            //invoco base de datos
            db = AppDataBase.getDatabase(this)

            //Capturo la categoría y la descripción
            val cat: String = editTCategoria.text.toString()
            val des: String = editTDescripcion.text.toString()
            //creo la nueva nota de tipo NOTAS
            val notaNueva = Notas(cat,id, des)
            db!!.notasDao().insert(notaNueva)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Toast.makeText(this, "Se guardó su nota correctamente", Toast.LENGTH_SHORT).show()
                    val int = Intent(this, notasPage::class.java)
                    int.putExtra("id", id)
                    startActivity(int) },
                    {
                        Toast.makeText(this, "ERROR AL INSERTAR NOTA", Toast.LENGTH_SHORT).show()
                    })
        }
    }
}