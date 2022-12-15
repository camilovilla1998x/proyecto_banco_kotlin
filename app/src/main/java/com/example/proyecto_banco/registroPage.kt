package com.example.proyecto_banco

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_banco.base_de_datos.AppDataBase
import com.example.proyecto_banco.entidades.Cuenta
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.registro_page.*

class registroPage : AppCompatActivity() {

    //Creo una variable para usar la base de datos
    private var db : AppDataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_page)

        //Aquí invoco el botón de registrarse
        btnRegistrarseReg.setOnClickListener {
            db = AppDataBase.getDatabase(this)

            // Aquí capturo los datos
            val cedula : Long? = editTCedulaReg.text.toString().toLongOrNull()
            val nombre : String? = editTNombreReg.text.toString()
            val pass : String? = editTPassReg.text.toString()
            val monto: Double? = editTMontoReg.text.toString().toDoubleOrNull()

            //Aquí valido que los datos no sean null
            if (nombre != null && cedula != null && pass != null && monto != null){
                var persona = Cuenta(cedula, nombre, pass, monto)
               db!!.cuentaDAO().insert(persona)
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe({
                       Toast.makeText(this, "Registro éxitoso, bienvenido", Toast.LENGTH_SHORT).show()
                       val int = Intent(this, loginPage::class.java)
                       startActivity(int)
                   },{
                       Toast.makeText(this, "Error al registrar, revise nuevamente", Toast.LENGTH_SHORT).show()
                   })
            }
            else{
                Toast.makeText(this, "Error al registrar, datos vacíos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}