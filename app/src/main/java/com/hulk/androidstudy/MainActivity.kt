package com.hulk.androidstudy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.javabase.activity.JavaBaseActivity
import com.hulk.androidstudy.databinding.ActivityMainBinding
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var mBinding :ActivityMainBinding
    private val listData = arrayListOf(
        "Java基础",
        "Java并发编程",
        "Java网络编程",
        "Kotlin语言",
        "算法与数据结构",
        "java/android虚拟机",
        "Android View框架体系",
        "Android View框架体系",
        "Android Framework",
        "Android 性能优化",
        "Android 开源框架",
        "Android Jetpack",
        "Android NDK",
        "webView框架",
        "Gradle"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.rvList.layoutManager = LinearLayoutManager(this)
        mBinding.rvList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        mBinding.rvList.adapter = Adapter(this, listData, object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0-> startActivity(JavaBaseActivity::class.java)
                    /*0 -> startActivity(GetResultActivity::class.java)
                    6 -> startActivity(PicturesAndGraphicsActivity::class.java)
                    33 -> {
                        val intent = Intent(this@MainActivity, ReflectActivity::class.java)
                        intent.putExtra("name", "xiaoming")
                        intent.putExtra("boy", true)
                        startActivity(intent)
                    }
                    34 -> startActivity(ProxyActivity::class.java)
                    35 -> startActivity(RetrofitActivity::class.java)
                    36 -> startActivity(ThreadActivity::class.java)
                    37 -> startActivity(RXJavaActivity::class.java)
                    38 -> startActivity(JavaIOActivity::class.java)
                    39 -> startActivity(WidgetActivity::class.java)
                    40 -> {
                        val iWebViewService: IWebViewService? =
                            ServiceLoaderUnit.load(IWebViewService::class.java)
                        iWebViewService?.startDemoHtml(this@MainActivity)
                    }*/
                }
            }
        })
    }

    private fun startActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

    class Adapter(
        private val context: Context,
        private val listData: ArrayList<String>,
        private val listener: OnItemClickListener
    ) : RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.adapter_main, parent, false)
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

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMain: TextView = itemView.findViewById(R.id.tv_main)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}