package com.example.proyecto_banco.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Transaccion(
    @PrimaryKey
    val idTransaccion: Int,
    @ColumnInfo(name="Destino")
    val idDestino: Long,
    @ColumnInfo(name="Usuario")
    val idUsuario: Long,
    @ColumnInfo(name="Fecha")
    val fecha: String,
    @ColumnInfo(name="Monto")
    val monto: Double
    )   {
}