package com.techno.gapsi.ui.home.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techno.gapsi.R
import com.techno.gapsi.data.model.Item
import kotlin.collections.ArrayList


class HomeAdapter (items: List<Item>, context: Context): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var items: MutableList<Item> = ArrayList()
    private var originalItems: MutableList<Item> = ArrayList()
    private var context :Context = context


    init {
        this.items          = items.toMutableList()
        this.originalItems  = items.toMutableList()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false))
    }


    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val imgItem         :ImageView = view.findViewById(R.id.iv_item_row)
        val tvTitulo        :TextView  = view.findViewById(R.id.tvTitulo)
        val tvPrice         :TextView  = view.findViewById(R.id.tvPrice)
    }





    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items[position]
        Glide.with(context).load(item.image).placeholder(R.drawable.ic_launcher_background).into(holder.imgItem)
        holder.tvTitulo.text = item.title
        holder.tvPrice.text = item.price.toString()



        holder.itemView.setOnClickListener {

            it.requestFocus()

        }

    }





    override fun getItemCount(): Int {
        return items.size
    }





}