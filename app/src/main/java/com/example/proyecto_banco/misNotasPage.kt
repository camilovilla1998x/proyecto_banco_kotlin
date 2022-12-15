package com.example.proyecto_banco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_banco.adaptadores.ListViewNotas
import com.example.proyecto_banco.base_de_datos.AppDataBase
import com.example.proyecto_banco.entidades.Notas
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.mis_notas_page.*

class misNotasPage : AppCompatActivity() {
    private var db : AppDataBase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        val id = intent.getLongExtra("id", 0)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mis_notas_page)
        db = AppDataBase.getDatabase(this)
        db!!.notasDao().getAll(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                var env = ArrayList<Notas>()
                //Recorro todas mis notas
                for (notas in it) {
                    env.add(Notas(notas.categoria, notas.user, notas.descripcion))
                }
                //Invoco la ListView con las notas
                val adapter = ListViewNotas(this, env)
                ListViewNotas.adapter = adapter
            }, {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            })
    }
}