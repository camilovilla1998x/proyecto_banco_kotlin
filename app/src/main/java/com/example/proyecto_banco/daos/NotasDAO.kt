package com.example.proyecto_banco.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.proyecto_banco.entidades.Cuenta
import com.example.proyecto_banco.entidades.Notas
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NotasDAO {
    @Insert
    fun insert(notas: Notas): Completable

    @Query ("SELECT * FROM notas WHERE user=:code")
    fun getAll(code:Long): Flowable<List<Notas>>
}