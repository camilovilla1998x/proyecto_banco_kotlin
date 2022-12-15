package com.example.proyecto_banco.daos

import androidx.room.*
import com.example.proyecto_banco.entidades.Contacto
import com.example.proyecto_banco.entidades.Cuenta
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ContactoDAO {

    @Insert
    fun insert(contacto: Contacto) : Completable
    @Update
    fun update(contacto: Contacto) : Completable
    @Delete
    fun delete(contacto: Contacto) : Completable

    @Query("SELECT * FROM contacto WHERE user=:code")
    fun getAll(code:Long): Flowable<List<Contacto>>

}