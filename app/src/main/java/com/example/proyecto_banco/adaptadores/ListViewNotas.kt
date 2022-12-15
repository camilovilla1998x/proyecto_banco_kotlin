package com.example.proyecto_banco.adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.proyecto_banco.R
import com.example.proyecto_banco.entidades.Notas

class ListViewNotas(private val context: Context, private val list:ArrayList<Notas>) :
    BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val valores: View = LayoutInflater.from(context).inflate(R.layout.adaptador_notas, parent, false)

        val txtCat = valores.findViewById<TextView>(R.id.txtCat)
        val txtDesc = valores.findViewById<TextView>(R.id.txtDesc)

        txtCat.text = ("Categoría: " + list[position].categoria)
        txtDesc.text = list[position].descripcion


        return valores

    }

    override fun getItem(position: Int): Any {
        return list[position]
    }


    override fun getItemId(position: Int): Long {
        return list.toString().toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

}