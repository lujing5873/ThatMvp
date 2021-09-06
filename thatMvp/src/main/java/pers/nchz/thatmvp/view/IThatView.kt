package pers.nchz.thatmvp.kotlin.view

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

interface IThatView {
    /**
     * view的布局id
     */
    fun getRootLayoutId(): Int

    /**
     * 初始化view
     */
    fun initView(savedInstanceState: Bundle?)

    /**
     * 懒加载
     */
    fun lazyData(savedInstanceState: Bundle?)

    /**
     * 创建view
     */
    fun onCreate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        fragment: Fragment?
    )

    /**
     * 根view
     */

    fun getRootView(): View?

    /**
     * 获取接口class
     */
    fun getInterface(): Class<out IThatView>?

    /**
     *屏幕方向切换
     */
    fun onConfigurationChanged(newConfig: Configuration?)

    /**
     * 销毁方法
     */
    fun onDetach()

    /**
     * 是否消化返回键
     */
    fun onBackPressed(): Boolean

    /**
     * 显示键盘
     */
    fun showJustPan(show: Boolean)

    /**
     * 是否正在显示dialog
     */
    fun isShowDialog(): Boolean

    /**
     * view是否需要重新初始化
     */
    fun isNeedClean(): Boolean

    /**
     * view是否需要刷新
     */
    fun isNeedRefresh(): Boolean

    /**
     * view需要重新初始化
     */
    fun setViewClean(need: Boolean)

    /**
     * 设置view需要重刷新数据
     */
    fun setViewRefresh(need: Boolean)

    /**
     * activityResult
     */
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}