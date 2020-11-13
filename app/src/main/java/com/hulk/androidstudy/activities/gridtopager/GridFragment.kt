package com.hulk.androidstudy.activities.gridtopager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLayoutChangeListener
import android.view.ViewGroup
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.hulk.androidstudy.R

/**
 * Created by tzh on 2020/11/12.
 */
class GridFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recyclerView = inflater.inflate(R.layout.fragment_grid, container, false) as RecyclerView
        recyclerView.adapter = GridAdapter(this)

        prepareTransitions()
        //推迟进入过渡，等待控件初始化完成
        postponeEnterTransition()
        return recyclerView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scrollToPosition()
    }

    private fun scrollToPosition() {
        recyclerView.addOnLayoutChangeListener(object : OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                recyclerView.removeOnLayoutChangeListener(this)
                val layoutManager = recyclerView.layoutManager
                val viewAtPosition =
                    layoutManager?.findViewByPosition(SharedElementActivity.currentPosition)
                if (viewAtPosition == null || layoutManager.isViewPartiallyVisible(
                        viewAtPosition, false, true)) {
                    recyclerView.post {
                        layoutManager?.scrollToPosition(SharedElementActivity.currentPosition)
                    }
                }
            }

        })
    }

    /**
     * 配置当前界面的过渡效果
     */
    private fun prepareTransitions() {
        //退出时的过渡
        exitTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.grid_exit_transition)
        //设置过渡回调监听，在Fragment attached 或 detached时回调
        setExitSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                //获取当前选中的ViewHolder
                val selectedViewHolder =
                    recyclerView.findViewHolderForAdapterPosition(SharedElementActivity.currentPosition)
                //设置共享元素，因为选中的共享元素只有一个，所以直接去names的第一个元素即可
                if (selectedViewHolder != null) {
                    sharedElements[names[0]] = selectedViewHolder.itemView.findViewById(R.id.card_image)
                }
            }
        })
    }
}