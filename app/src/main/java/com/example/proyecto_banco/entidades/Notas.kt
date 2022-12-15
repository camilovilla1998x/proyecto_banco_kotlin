package com.example.proyecto_banco.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notas
    (
    @PrimaryKey
    val categoria: String,
    @ColumnInfo(name="user")
    val user: Long,
    @ColumnInfo(name="Nota")
    val descripcion: String
){
}