package com.example.proyecto_banco.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyecto_banco.entidades.Cuenta
import com.example.proyecto_banco.entidades.Transaccion
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface TransaccionDAO {
    @Insert
    fun insert(transaccion: Transaccion): Completable


    //Querys
    @Query("SELECT * FROM transaccion WHERE Destino=:code")
    fun getAllT(code:Long): Single<Transaccion>

    @Query("SELECT * FROM transaccion WHERE Destino=:code")
    fun getAll(code:Long): Flowable<List<Transaccion>>

}