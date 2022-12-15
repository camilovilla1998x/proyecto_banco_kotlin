package com.example.proyecto_banco.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.proyecto_banco.R
import com.example.proyecto_banco.entidades.Contacto
import com.example.proyecto_banco.entidades.Cuenta
import com.example.proyecto_banco.entidades.Detalles
import com.example.proyecto_banco.entidades.Transaccion
//ListView para el adaptador
class ListViewAdapter(private val context: Context, private val trans: ArrayList<Detalles>) :
    BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var valores: View = LayoutInflater.from(context).inflate(R.layout.adaptador_transaccion, parent, false)
        //Obtengo los id que voy a llenar en el adaptador
        var txtMonto = valores.findViewById<TextView>(R.id.txtMonto)
        var txtId = valores.findViewById<TextView>(R.id.txtId)
        var txtFecha = valores.findViewById<TextView>(R.id.txtFecha)
        //Arreglo la VIEW con los valores
        txtMonto.text = ("$" + trans[position].monto.toString())
        txtId.text = ("REMITENTE: "+ trans[position].id.toString())
        txtFecha.text = ("FECHA: "+ trans[position].fecha)

        return valores

    }


    override fun getItem(position: Int): Any {
        return trans[position]
    }


    override fun getItemId(position: Int): Long {
        return trans.toString().toLong()
    }

    override fun getCount(): Int {
        return trans.size
    }

}