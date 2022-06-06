package com.kotlinapp.demoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kotlinapp.demoapp.dataClass.ImageApi
import com.kotlinapp.demoapp.dataClass.ImageApiItem
import com.kotlinapp.demoapp.databinding.RowitemBinding


class  AdapterImg : RecyclerView.Adapter<AdapterImg.MainViewHolder>() {
    var homeimgList = mutableListOf<ImageApiItem>()
    lateinit var context: Context

    fun setImg(mainlist:  ImageApi  ,context: Context) {
        this.context=context
        this.homeimgList = mainlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RowitemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val place = homeimgList[position]
        holder.binding.textView.text = place.author
        Glide.with(context).load(place.download_url)
            .into(holder.binding.imageView)
        holder.itemView.setOnClickListener {
            Toast.makeText(context, place.author, Toast.LENGTH_SHORT).show()
         }
    }


    override fun getItemCount(): Int {
        return homeimgList.size
    }

    class MainViewHolder(val binding: RowitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

}