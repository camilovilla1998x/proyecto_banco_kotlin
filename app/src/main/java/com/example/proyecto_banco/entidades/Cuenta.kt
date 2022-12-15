package com.example.proyecto_banco.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cuenta(
    @PrimaryKey
    val user:Long,
    @ColumnInfo(name="Nombre")
    val nombre:String,
    @ColumnInfo(name="Password")
    val password:String,
    @ColumnInfo(name="Saldo")
    val saldo:Double) {


}