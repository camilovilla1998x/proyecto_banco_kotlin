package com.example.proyecto_banco.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.proyecto_banco.entidades.Cuenta
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CuentaDAO {
    @Insert
    fun insert(cuenta: Cuenta) : Completable
    @Update
    fun update(cuenta: Cuenta) : Completable

    //Consultar usuario
    @Query("SELECT * FROM cuenta WHERE user =:code  LIMIT 1")
    fun getByUser(code:Long): Single<Cuenta>
    //Consultar contraseña
    @Query("SELECT * FROM cuenta WHERE Password =:pass  LIMIT 1")
    fun getByPassword(pass:String): Single<Cuenta>
    //Consultar DATOS usuario
    @Query ("SELECT * FROM cuenta WHERE user=:code")
    fun getAll(code:Long): Flowable<List<Cuenta>>
    //Consultar monto
    //@Query("SELECT Saldo FROM cuenta WHERE user=:code")
    //fun consultSaldo(code:Long): Single<Cuenta>
    //Actualizar MONTO usuario
    /*@Query("SELECT * FROM cuenta WHERE user=:code")
    fun updateMonto(code:Long, monto:Double): Single<Cuenta>
     */


}