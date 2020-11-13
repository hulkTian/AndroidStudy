package com.hulk.androidstudy.activities.gridtopager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hulk.androidstudy.activities.gridtopager.ImageData.IMAGE_DRAWABLES

/**
 * Created by tzh on 2020/11/12.
 */
class ImagePagerAdapter(fragment: Fragment) : FragmentStatePagerAdapter(
    fragment.childFragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    override fun getCount(): Int = IMAGE_DRAWABLES.size

    override fun getItem(position: Int): Fragment = ImageFragment.newInstance(IMAGE_DRAWABLES[position])
}