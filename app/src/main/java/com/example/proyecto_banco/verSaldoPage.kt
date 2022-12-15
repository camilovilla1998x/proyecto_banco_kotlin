package com.example.proyecto_banco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_banco.base_de_datos.AppDataBase
import com.example.proyecto_banco.entidades.Cuenta
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.versaldo_page.*

class verSaldoPage : AppCompatActivity() {
    private var db : AppDataBase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.versaldo_page)
        val id = intent.getLongExtra("id", 0)
        txtCodigoSAL.text = id.toString()
        db = AppDataBase.getDatabase(this)
        db!!.cuentaDAO().getAll(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                for (cliente in it){
                    txtNombreSAL.text = cliente.nombre
                    txtMonto.text = cliente.saldo.toString()
                }
            },{
                Toast.makeText(this, "Ocurrió un error inesperado, reinicie", Toast.LENGTH_SHORT).show()
            })

        btnDetallesSAL.setOnClickListener {
            val int = Intent(this, detallesTransaccionPage::class.java)
            int.putExtra("id", id)
            startActivity(int)
        }


        btnCerrarSesionSAL.setOnClickListener {
            val int = Intent(this, loginPage::class.java)
            startActivity(int)
        }

    }
}