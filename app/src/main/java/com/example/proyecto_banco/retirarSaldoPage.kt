package com.example.proyecto_banco

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_banco.base_de_datos.AppDataBase
import com.example.proyecto_banco.entidades.Cuenta
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.retirarsaldo_page.*
import kotlinx.android.synthetic.main.versaldo_page.*

class retirarSaldoPage : AppCompatActivity() {
    private var db : AppDataBase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.retirarsaldo_page)
        btnRetirarDineroRet.setOnClickListener {
            val cantidad : Double? = editTMontoRet.text.toString().toDoubleOrNull()
            val pass1: String = editTPassRet.text.toString()
            val pass2: String = editTPass2Ret.text.toString()
            val id = intent.getLongExtra("id", 0)

            if (cantidad != null && pass1 == pass2){
                db = AppDataBase.getDatabase(this)

                db!!.cuentaDAO().getByUser(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        val user: Long = id
                        val nomb: String = it.nombre
                        val pass: String = pass1
                        val sald: Double = it.saldo
                        if (sald > cantidad){
                            val actualizacion = Cuenta(user, nomb, pass, (sald-cantidad))
                            db!!.cuentaDAO().update(actualizacion)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe({
                                    Toast.makeText(this, "Transferencia con éxito ${it.saldo-cantidad}", Toast.LENGTH_SHORT).show()
                                    val int = Intent(this, homePage::class.java)
                                    int.putExtra("id", id)
                                    startActivity(int)

                                },{
                                    Toast.makeText(this, "Error inesperado, F", Toast.LENGTH_SHORT).show()
                                })
                        }
                        else{
                            Toast.makeText(this, "SALDO INSUFICIENTE", Toast.LENGTH_SHORT).show()
                        }




                    },{
                        Toast.makeText(this, "Ocurrió un error inesperado, reinicie", Toast.LENGTH_SHORT).show()
                    })
            }
            else{
                Toast.makeText(this, "Error, datos vacíos", Toast.LENGTH_SHORT).show()
            }

        }



        btnCerrarSesionRET.setOnClickListener {
            val int = Intent(this, loginPage::class.java)
            startActivity(int)
        }



    }
}