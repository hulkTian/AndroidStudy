package com.hulk.common.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hulk.common.R
import com.hulk.common.databinding.AdapterMainBinding

class BaseListAdapter(private val context: Context,
                      private val listData: ArrayList<String>,
                      private val listener: OnItemClickListener
) : RecyclerView.Adapter<BaseListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = DataBindingUtil.inflate<AdapterMainBinding>(LayoutInflater.from(context),
            R.layout.adapter_main, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onItemClick(position)
        }
        holder.tvMain.text = listData[position]
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class MyViewHolder(itemView: AdapterMainBinding) : RecyclerView.ViewHolder(itemView.root) {
        val tvMain: TextView = itemView.tvMain
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}