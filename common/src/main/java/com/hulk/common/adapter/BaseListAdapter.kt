package com.hulk.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.constants.Constants

/**
 * @description RecycleView adapter 适用于垂直布局的列表
 * @author zehao.tian
 */
abstract class BaseListAdapter(
    protected val context: Context,
    protected val listData: ArrayList<BaseItemBean>,
    protected val listener: OnItemClickListener? = null
) : RecyclerView.Adapter<BaseListAdapter.BaseListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder {
        return BaseListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(context), viewType, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseListViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener?.onItemClick(position)
        }
        onItemConvert(holder.root, position)
    }

    override fun getItemCount(): Int =
        if (listData.isNullOrEmpty()) Constants.EMPTY else listData.size

    override fun getItemViewType(position: Int): Int =
        if (listData.isNullOrEmpty()) Constants.EMPTY else listData[position].itemLayoutId

    abstract fun onItemConvert(root: ViewDataBinding, position: Int)

    class BaseListViewHolder(val root: ViewDataBinding) : RecyclerView.ViewHolder(root.root)

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}