package com.android.framework.activity

import com.android.framework.R
import com.hulk.common.base.BaseActivity
import com.hulk.common.base.CommonMenuListActivity
import com.hulk.common.bean.BaseItemBean
import com.hulk.common.databinding.CommonLayoutRecycleViewBinding

/**
 * @description
 * @author: zehao.tian
 * @date: 2022/11/23
 */
class BinderActivity: CommonMenuListActivity() {

    override fun getLayoutId(): Int = R.layout.common_layout_recycle_view
    override fun getListData(): ArrayList<BaseItemBean> {
        TODO("Not yet implemented")
    }

    override fun getActivityListData(): ArrayList<Class<*>> {
        TODO("Not yet implemented")
    }


}