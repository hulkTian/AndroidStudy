package com.hulk.androidstudy.activities.gridtopager

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionSet
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.hulk.androidstudy.R
import com.hulk.androidstudy.activities.gridtopager.ImageData.IMAGE_DRAWABLES
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by tzh on 2020/11/12.
 */
class GridAdapter(fragment: Fragment) : RecyclerView.Adapter<GridAdapter.ImageViewHolder>() {
    /**
     * A listener that is attached to all ViewHolders to handle image loading events and clicks.
     */
    interface ViewHolderListener {
        fun onLoadCompleted(view: ImageView, position: Int)
        fun onItemClicked(view: View, position: Int)
    }

    private var requestManager: RequestManager = Glide.with(fragment)
    private var viewHolderListener: ViewHolderListener

    init {
        viewHolderListener = ViewHolderListenerImpl(fragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_card, parent, false)
        return ImageViewHolder(view, requestManager, viewHolderListener)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return IMAGE_DRAWABLES.size
    }

    private class ViewHolderListenerImpl(val fragment: Fragment) : ViewHolderListener {
        val enterTransitionStarted: AtomicBoolean = AtomicBoolean()

        override fun onLoadCompleted(view: ImageView, position: Int) {
            // Call startPostponedEnterTransition only when the 'selected' image loading is completed.
            //第一个图片加载完成后调用startPostponedEnterTransition
            if (SharedElementActivity.currentPosition != position) {
                return
            }
            if (enterTransitionStarted.getAndSet(true)) {
                return
            }
            fragment.startPostponedEnterTransition()
        }

        /**
         * 处理图片点击事件，根据点击位置进入ImagePagerFragment
         */
        override fun onItemClicked(view: View, position: Int) {
            //设置选中的位置
            SharedElementActivity.currentPosition = position
            //获取设置的退出过渡，排除上次添加的控件，避免重复
            (fragment.exitTransition as TransitionSet).excludeTarget(view, true)

            val transitionView = view.findViewById<ImageView>(R.id.card_image)
            fragment.parentFragmentManager?.beginTransaction()
                ?.setReorderingAllowed(true)
                ?.addSharedElement(transitionView, transitionView.transitionName)
                ?.replace(
                    R.id.fragment_container,
                    ImagePagerFragment(),
                    ImagePagerFragment::class.java.simpleName
                )
                ?.addToBackStack(null)
                ?.commit()
        }

    }

    class ImageViewHolder(
        itemView: View, private val requestManager: RequestManager,
        private val viewHolderListener: ViewHolderListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var image: ImageView = itemView.findViewById(R.id.card_image)

        init {
            image.setOnClickListener(this)
        }

        fun onBind() {
            val adapterPosition = adapterPosition
            setImage(adapterPosition)
            image.transitionName = IMAGE_DRAWABLES[adapterPosition].toString()
        }

        private fun setImage(adapterPosition: Int) {
            requestManager
                .load(IMAGE_DRAWABLES[adapterPosition])
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        viewHolderListener.onLoadCompleted(image, adapterPosition)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        viewHolderListener.onLoadCompleted(image, adapterPosition)
                        return false
                    }
                }).into(image)
        }

        override fun onClick(view: View) {
            // Let the listener start the ImagePagerFragment.
            viewHolderListener.onItemClicked(view, adapterPosition)
        }
    }
}