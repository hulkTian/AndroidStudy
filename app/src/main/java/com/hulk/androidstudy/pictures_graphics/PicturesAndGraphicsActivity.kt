package com.hulk.androidstudy.pictures_graphics

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hulk.androidstudy.R
import com.hulk.androidstudy.databinding.ActivityPicturesGraphicsBinding


class PicturesAndGraphicsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityPicturesGraphicsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_pictures_graphics)
        dataBinding.tvTitle.typeface = Typeface.createFromAsset(assets, "SourceHanSansCN-Bold.otf")
    }
}