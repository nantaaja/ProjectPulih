package com.example.projectpulih

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

class SelfCareAdapter(
    context: Context,
    private val items: MutableList<SelfCareItem>,
    private val onItemClick: (position: Int) -> Unit
) : ArrayAdapter<SelfCareItem>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.item_self_care, parent, false
        )

        val item = getItem(position) ?: return view

        val ivCheck = view.findViewById<ImageView>(R.id.ivCheckStatus)
        val txtTitle = view.findViewById<TextView>(R.id.txtTaskTitle)
        val txtTime = view.findViewById<TextView>(R.id.txtTaskTime)

        txtTitle.text = item.title
        txtTime.text = item.time

        if (item.isCompleted) {
            ivCheck.setImageResource(R.drawable.ic_check_circle)
            txtTitle.setTextColor(ContextCompat.getColor(context, R.color.dark_slate_secondary))
            txtTitle.paintFlags = txtTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            ivCheck.setImageResource(R.drawable.ic_circle_outline)
            txtTitle.setTextColor(ContextCompat.getColor(context, R.color.dark_slate))
            txtTitle.paintFlags = txtTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        view.setOnClickListener {
            onItemClick(position)
        }

        return view
    }
}
