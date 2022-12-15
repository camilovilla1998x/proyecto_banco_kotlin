package com.example.proyecto_banco.base_de_datos

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyecto_banco.daos.ContactoDAO
import com.example.proyecto_banco.daos.CuentaDAO
import com.example.proyecto_banco.daos.NotasDAO
import com.example.proyecto_banco.daos.TransaccionDAO
import com.example.proyecto_banco.entidades.Contacto
import com.example.proyecto_banco.entidades.Cuenta
import com.example.proyecto_banco.entidades.Notas
import com.example.proyecto_banco.entidades.Transaccion

//Se definen las entidades de la base de datos
@Database(entities = [Cuenta::class, Transaccion::class, Contacto::class, Notas::class], version = 1)

//Se crea e instancia la base de datos
abstract class AppDataBase : RoomDatabase() {
    abstract fun cuentaDAO() : CuentaDAO
    abstract fun transaccionDAO(): TransaccionDAO
    abstract fun contactoDAO():  ContactoDAO
    abstract fun notasDao(): NotasDAO


    companion object {
        var INSTANCE : AppDataBase?= null
        //Se crea el método para obtener/instanciar la base de datos
        fun getDatabase(context: Context): AppDataBase?{
            if (INSTANCE == null){
                synchronized(AppDataBase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDataBase::class.java, "database.room").build()
                }
            }
            return INSTANCE
        }


    }
}