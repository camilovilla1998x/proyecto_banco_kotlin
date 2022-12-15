package com.example.proyecto_banco.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.proyecto_banco.R
import com.example.proyecto_banco.entidades.Contacto

//ListView para el adaptador
class ListViewContactos(private val context: Context, private val trans2:ArrayList<Contacto>) :
    BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var valores: View = LayoutInflater.from(context).inflate(R.layout.adaptador_contactos, parent, false)
        //Obtengo los id que voy a llenar en el adaptador
        var txtIdContacto = valores.findViewById<TextView>(R.id.txtIdContacto)
        var txtNombreContacto = valores.findViewById<TextView>(R.id.txtNombreContacto)
        //Arreglo la VIEW con los valores
        txtIdContacto.text = ("Contacto: " + trans2[position].idContacto.toString())
        txtNombreContacto.text = ("Nombre: "+ trans2[position].nombreContacto)

        return valores

    }


    override fun getItem(position: Int): Any {
        return trans2[position]
    }


    override fun getItemId(position: Int): Long {
        return trans2.toString().toLong()
    }

    override fun getCount(): Int {
        return trans2.size
    }

}