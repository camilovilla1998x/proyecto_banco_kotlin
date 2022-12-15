package com.example.proyecto_banco

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.proyecto_banco.base_de_datos.AppDataBase
import com.example.proyecto_banco.entidades.Cuenta
import com.example.proyecto_banco.entidades.Transaccion
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.enviardinero_page.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class enviarDineroPage : AppCompatActivity() {

    //Creo una variable para usar la base de datos
    private var db : AppDataBase? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enviardinero_page)
        //Botón enviar dinero
        btnEnviarDineroEnv.setOnClickListener {
            db = AppDataBase.getDatabase(this)

            //Recibo el id
            val id = intent.getLongExtra("id", 0)

            //Creo una variable de tipo random
            val ran = Random()
            //Creo un número aleatorio para registrar el número ID de la transacción
            val nAleatorio = ran.nextInt(1000000)+9

            //Obtengo la cantidad que voy a enviar
            val cantidad : Double? = editTMontoEnv.text.toString().toDoubleOrNull()
            //Obtengo la cédula de la persona a la que le voy a enviar el dinero dos veces
            val ced: Long = editTCedulaEnv.text.toString().toLong()
            val ced2: Long = editTCedula2Env.text.toString().toLong()

            //Valido que la cantidad no sea null y
            // los dos espacios de las cédulas sean iguales
            if (cantidad != null && ced == ced2){
                db!!.cuentaDAO().getByUser(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                        //Suscribe 1
                    .subscribe({
                        //Acá valido si el saldo actual es mayor a la canditad
                        // que voy a enviar
                        if (it.saldo > cantidad){

                            //Aquí creo una variable de tipo transacción para
                                // guardarla como transacción
                            val trans = Transaccion(nAleatorio, ced, id, "00/00/00", cantidad)
                            //Inserto transacción
                            db!!.transaccionDAO().insert(trans)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                //Suscribe 2
                                .subscribe({
                                    //Actualizo cuenta actual
                                    val act = Cuenta(id, it.nombre, it.password, (it.saldo-cantidad))
                                    db!!.cuentaDAO().update(act)
                                        .subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        //Suscribe 3
                                        .subscribe({
                                            //Filtro cuenta 2
                                            db!!.cuentaDAO().getByUser(ced)
                                                .subscribeOn(Schedulers.io())
                                                .observeOn(AndroidSchedulers.mainThread())
                                                //Suscribe 4
                                                .subscribe({

                                                    //Aquí actualizo la segunda cuenta a la que le envié el dinero
                                                    val act2 = Cuenta(ced, it.nombre, it.password, (it.saldo+cantidad))
                                                    db!!.cuentaDAO().update(act2)
                                                        .subscribeOn(Schedulers.io())
                                                        .observeOn(AndroidSchedulers.mainThread())
                                                        //Suscribe 5
                                                        .subscribe({
                                                            Toast.makeText(this, "Transferencia éxitosa", Toast.LENGTH_SHORT).show()
                                                            val int = Intent(this, homePage::class.java)
                                                            int.putExtra("id", id)
                                                            startActivity(int)
                                                               },{
                                                                   Toast.makeText(this, "Falló transferencia", Toast.LENGTH_SHORT).show()
                                                               })
                                                           //fin Suscribe 5
                                                       },{
                                                           Toast.makeText(this, "Falló act2", Toast.LENGTH_SHORT).show()
                                                       })
                                                //Fin suscribe 4
                                               },{
                                                   Toast.makeText(this, "Error inesperado, F", Toast.LENGTH_SHORT).show()
                                               })
                                                //Fin Suscribe 3
                                       },{

                                           Toast.makeText(this, "Error, act1", Toast.LENGTH_SHORT).show()
                                       })
                                //Fin Suscribe 2
                               }
                        else{
                            Toast.makeText(this, "SALDO INSUFICIENTE", Toast.LENGTH_SHORT).show()
                        }
                    },{

                        Toast.makeText(this, "Error, Transacción", Toast.LENGTH_SHORT).show()
                    })
                                    //Fin suscribe 1
                                }
            else{
                Toast.makeText(this, "Datos vacíos, repita nuevamente", Toast.LENGTH_SHORT).show()
            }
        }

        btnCerrarSesionENV.setOnClickListener {
            val int = Intent(this, loginPage::class.java)
            startActivity(int)

        }
    }
}
