package com.hulk.androidstudy.widget.layout_manager

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hulk.androidstudy.R
import kotlin.math.sqrt

/**
 * created by tzh on 2021/9/4
 */
class MyLayoutManagerActivity : Activity() {
    private val listData = arrayListOf(
        "https://img0.baidu.com/it/u=838969580,114622876&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=750",
        "https://img0.baidu.com/it/u=3607495788,154615704&fm=26&fmt=auto&gp=0.jpg",
        "https://img1.baidu.com/it/u=2298484978,1703903334&fm=26&fmt=auto&gp=0.jpg",
        "https://img2.baidu.com/it/u=1174417038,3455224278&fm=26&fmt=auto&gp=0.jpg",
        "https://img2.baidu.com/it/u=3939898287,685761324&fm=26&fmt=auto&gp=0.jpg",
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_layout_manager)
        /*rv_data.layoutManager = MyLayoutManager()
        rv_data.adapter = Adapter(this, listData)
        ItemTouchHelper(
            MySimpleCallback(
                rv_data.adapter as Adapter, listData
            )
        ).attachToRecyclerView(rv_data)*/
    }

    private class MySimpleCallback(
        private val adapter: RecyclerView.Adapter<*>,
        private val listData: ArrayList<String>
    ) :
        ItemTouchHelper.SimpleCallback(0, 15) {

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = false

        @SuppressLint("NotifyDataSetChanged")
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val data = listData.removeAt(viewHolder.layoutPosition)
            listData[0] = data
            adapter.notifyDataSetChanged()
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

            val maxDistance = recyclerView.width * 0.5f
            val distance: Float = sqrt(dX * dX + dY * dY)
            var fraction = distance / maxDistance
            if (fraction > 1) {
                fraction = 1f
            }

            for (i in 0..recyclerView.childCount) {
                val view = recyclerView.getChildAt(i)
                val level = recyclerView.childCount - i - 1
                if (level > 0) {
                    if (level < MyLayoutManager.CardConfig.maxCount - 1) {
                        view.translationY =
                            MyLayoutManager.CardConfig.transitionY * (level - fraction)
                        view.scaleX = 1 - MyLayoutManager.CardConfig.scaleGap * (level + fraction)
                        view.scaleY = 1 - MyLayoutManager.CardConfig.scaleGap * (level + fraction)
                    }
                }
            }

        }

        override fun getAnimationDuration(
            recyclerView: RecyclerView, animationType: Int, animateDx: Float, animateDy: Float
        ): Long {
            return super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy)
        }

    }

    private class Adapter(
        private val context: Context, private val listData: ArrayList<String>,
    ) : RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(context)
                .inflate(R.layout.adapter_layout_manager, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            Glide.with(context).load(listData[position]).into(holder.iv)
        }

        override fun getItemCount(): Int {
            return listData.size
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iv: ImageView = itemView.findViewById(R.id.iv)
    }
}