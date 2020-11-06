package com.hulk.androidstudy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hulk.androidstudy.activities.ActivitiesHomeActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val listData = arrayListOf("Activities", "架构组件", "导航组件", "Intent和Intent过滤器",
            "界面", "动画和过度", "图片和图形", "音频和视频", "服务", "后台任务", "权限", "应用数据和文件",
            "用户数据和身份", "用户位置", "触摸和输入", "CameraX", "相机", "传感器", "连接性", "Renderscript",
            "基于网络的内容", "Android App Bundle", "Google Play", "应用操作", "切片", "依赖注入",
            "测试", "性能", "无障碍", "隐私设置", "安全性", "为数十亿用户打造产品", "为企业打造产品")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_list.adapter = Adapter(this, listData, object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> startActivity(ActivitiesHomeActivity::class.java)
                }
            }
        })
    }

    private fun startActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

    private class Adapter(val context: Context, val listData: ArrayList<String>, val listener: OnItemClickListener)
        : RecyclerView.Adapter<MyViewHolder>() {

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

    private class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMain: TextView = itemView.findViewById(R.id.tv_main)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}