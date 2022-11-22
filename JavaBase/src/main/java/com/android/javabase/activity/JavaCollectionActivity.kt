package com.android.javabase.activity

import com.hulk.common.base.CommonListActivity

class JavaCollectionActivity : CommonListActivity() {

    override fun getListData(): ArrayList<String> {
        return arrayListOf(
            "ArrayList",
            "Java LinkedList",
            "Java 注解",
            "Java 反射",
            "Java I/O",
            "Java 序列化",
        )
    }
    
    override fun onItemClick(position: Int) {
        
    }
}