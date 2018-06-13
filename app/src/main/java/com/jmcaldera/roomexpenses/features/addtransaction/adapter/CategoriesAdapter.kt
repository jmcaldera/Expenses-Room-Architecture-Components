package com.jmcaldera.roomexpenses.features.addtransaction.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.jmcaldera.roomexpenses.features.model.CategoryView

class CategoriesAdapter(ctx: Context, private var list: List<CategoryView>):
        ArrayAdapter<CategoryView>(ctx, android.R.layout.simple_spinner_item, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getView(position, convertView, parent)
        val textView = view.findViewById<TextView>(android.R.id.text1)

        textView.text = list[position].name

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = super.getView(position, convertView, parent)
        val textView = view.findViewById<TextView>(android.R.id.text1)

        textView.text = list[position].name

        return view
    }

    fun setList(newList: List<CategoryView>) = this.addAll(newList)

}