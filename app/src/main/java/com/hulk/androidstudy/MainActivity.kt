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
import com.hulk.androidstudy.activities.GetResultActivity
import com.hulk.androidstudy.java_base.io.JavaIOActivity
import com.hulk.androidstudy.java_base.proxy.ProxyActivity
import com.hulk.androidstudy.java_base.reflect.ReflectActivity
import com.hulk.androidstudy.java_base.retrofit.RetrofitActivity
import com.hulk.androidstudy.java_base.rxjava.RXJavaActivity
import com.hulk.androidstudy.java_base.thread.ThreadActivity
import com.hulk.androidstudy.widget.WidgetActivity
import com.hulk.common.service.IWebViewService
import com.hulk.common.unit.ServiceLoaderUnit
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val listData = arrayListOf(
        "Activities", "架构组件", "导航组件", "Intent和Intent过滤器",
        "界面", "动画和过度", "图片和图形", "音频和视频", "服务", "后台任务", "权限", "应用数据和文件",
        "用户数据和身份", "用户位置", "触摸和输入", "CameraX", "相机", "传感器", "连接性", "Renderscript",
        "基于网络的内容", "Android App Bundle", "Google Play", "应用操作", "切片", "依赖注入",
        "测试", "性能", "无障碍", "隐私设置", "安全性", "为数十亿用户打造产品", "为企业打造产品", "反射",
        "代理", "Retrofit", "多线程", "RXJava", "Java I/O", "自定义View", "webView框架"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_list.adapter = Adapter(this, listData, object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> startActivity(GetResultActivity::class.java)
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
                    }
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