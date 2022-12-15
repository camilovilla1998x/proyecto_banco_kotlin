package com.example.proyecto_banco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_banco.base_de_datos.AppDataBase
import com.example.proyecto_banco.entidades.Cuenta
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.android.synthetic.main.adaptador_transaccion.*
import kotlinx.android.synthetic.main.login_page.*
import kotlinx.android.synthetic.main.versaldo_page.*
import kotlinx.android.synthetic.main.versaldo_page.txtMonto

class loginPage : AppCompatActivity() {

    //Creo una variable para usar la base de datos
    private var db : AppDataBase? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)
        //Botón de iniciar sesión
        btnIniciarSesionLOG.setOnClickListener {

            //Obtengo la base de datos en este contexto en la variable db
            db = AppDataBase.getDatabase(this)

            //Obtengo cada uno de los datos
            val id : Long = editTUserLOG.text.toString().toLong()
            val pass : String = editTPassLOG.text.toString()

            //Aquí invoco de mi DAO cuenta si el usuario existe
            db!!.cuentaDAO().getByUser(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //Aquí invoco de mi DAO cuenta si la contraseña existe
                    db!!.cuentaDAO().getByPassword(pass)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({

                            //Aquí paso al homePage si todo sale bien y envío el id como referencia de usuario
                            Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                            val int = Intent(this, homePage::class.java)
                            int.putExtra("id", id)
                            startActivity(int)
                        },{

                            //Si no es correcto saca los errores
                            Toast.makeText(this, "Contraseña incorrecta, revise nuevamente", Toast.LENGTH_SHORT).show()
                        })
                },{
                    Toast.makeText(this, "Usuario incorrecto, revise nuevamente", Toast.LENGTH_SHORT).show()
                })
            
        }

        btnRegistrarseLOG.setOnClickListener {
            val int = Intent(this, registroPage::class.java)
            startActivity(int)
        }



    }
}