package com.example.proyecto_banco.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contacto(
    @PrimaryKey
    val idContacto: Long,
    @ColumnInfo(name="user")
    val userYo:Long,
    @ColumnInfo(name="NombreContacto")
    val nombreContacto: String,
) {
}