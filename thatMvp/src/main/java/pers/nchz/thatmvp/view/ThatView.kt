package pers.nchz.thatmvp.view

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import pers.nchz.thatmvp.delegate.ThatActivity
import pers.nchz.thatmvp.presenter.KPresenter
import java.lang.reflect.ParameterizedType

abstract class ThatView<P : KPresenter> :IThatView,LifecycleObserver {
    protected var context: Context? = null
    protected var mActivity: AppCompatActivity?= null
    protected var baseActivity: ThatActivity?= null
    protected var fragment: Fragment?= null
    protected var presenter: P? = null
    protected var root: View? = null
    protected var rootViewGroup: ViewGroup? = null
    protected val mViews = SparseArray<View>()
    protected var lifecycle: Lifecycle? = null

    private var classOfView: Class<out IThatView>? = null
    private var isShowingDialog  = false //是否显示dialog
    private var needRefresh = false //是否需要刷新数据
    private var needClean = false //是否需要清空页面数据


    override fun onCreate(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
        fragment: Fragment?
    ) {
        val rootLayoutId = getRootLayoutId()
        if (rootLayoutId >= 0) {
            if (needClean) {
                mViews.clear()
            }
            var interfaces = javaClass.interfaces
            for (inter in interfaces) { //自己的接口
                if (IThatView::class.java.isAssignableFrom(inter)) {
                    classOfView = inter as Class<out IThatView>
                    break
                }
            }
            if (classOfView == null) {
                interfaces = javaClass.superclass.interfaces
                for (inter in interfaces) { //寻找父view的 接口
                    if (IThatView::class.java.isAssignableFrom(inter)) {
                        classOfView = inter as? Class<out IThatView?>
                        break
                    }
                }
            }

            root = inflater.inflate(rootLayoutId, container, false)

            if (root is ViewGroup) {
                rootViewGroup=root as ViewGroup
            }

            context = root?.context
            mActivity = context as AppCompatActivity
            if (mActivity is ThatActivity) {
                baseActivity = mActivity as ThatActivity
            }

            try {
                presenter = getClassType(this)?.newInstance()
            } catch (e: Exception) {
                println(e.message)
            }

            if (fragment != null) {
                this.fragment = fragment
                lifecycle = fragment.lifecycle
            } else {
                lifecycle = mActivity?.lifecycle
            }
            //添加监听
            presenter?.addView(this)
            presenter?.lifecycle = lifecycle
            lifecycle?.addObserver(this)

        }
    }

    /**
     * 获取根view
     */
    override fun getRootView(): View? {
        return root
    }


    private  fun <T : View?> bindView(id: Int): T? {
        var view: T? = mViews[id] as T
        if (view == null) {
            view = root?.findViewById<View>(id) as T?
            mViews.put(id, view)
        }
        return view
    }

    /**
     * 获取view
     */
    fun <T : View?> getView(id: Int): T? {
        return bindView(id)
    }

    /**
     * 设置点击事件
     */
    fun setOnClickListener(listener: View.OnClickListener, vararg ids: Int) {
        ids?.let {
            for (id in it) {
                getView<View>(id)?.setOnClickListener(listener)
            }
        }
    }

    /**
     * 添加子View
     * @param view
     * @param savedInstanceState
     * @param index
     * @param viewGroup
     */
    private  fun addView(
        view: IThatView,
        savedInstanceState: Bundle?,
        index: Int,
        viewGroup: ViewGroup
    ) {
        view.onCreate(LayoutInflater.from(context), rootViewGroup, savedInstanceState, fragment)
        view.getRootView()?.let {
            viewGroup.addView(view.getRootView(), index)
            presenter?.addView(view)
            view.initView(savedInstanceState)
        }
    }

    /**
     * 获取presenter泛型
     */
    private fun getClassType(view: IThatView): Class<P>? {
        val superClass = view.javaClass.genericSuperclass
        val type = (superClass as ParameterizedType).actualTypeArguments[0]
        return if (type is ParameterizedType) {
                type.rawType as Class<P>
            } else {
                type as Class<P>
            }
    }
    /**
     * 添加子view
     */
    open fun addSubView(view: IThatView, savedInstanceState: Bundle?) {

        rootViewGroup?.let {
            addView(view, savedInstanceState, it.childCount!!, it)
        }

    }
    /**
     * 添加子view
     */
    open fun addSubView(view: IThatView, savedInstanceState: Bundle?, index: Int, id: Int) {
        val addViewGroup: ViewGroup = root?.findViewById(id) ?: return
        addView(view, savedInstanceState, index, addViewGroup)
    }

    /**
     * 添加子view
     */
    open fun addSubView(view: IThatView, savedInstanceState: Bundle?, index: Int) {
        rootViewGroup?.let {
            addView(view, savedInstanceState, index, it)
        }

    }


    /**
     * 从activity的presenter中获取别的View
     * @param viewClass
     * @param <T>
     * @return
    </T> */
    protected open fun <T : IThatView> activityView(viewClass: Class<T>): T? {
        return if (baseActivity == null) null else baseActivity!!.activityView(viewClass)
    }

    /**
     * 懒加载
     */
    override fun lazyData(savedInstanceState: Bundle?) {
        presenter?.let {
            for (sub in it.getViews()) {
                if(sub===this){
                    continue
                }
               sub?.lazyData(savedInstanceState)
            }
        }
    }

    /**
     * 横竖屏切换回调
     */
    override fun onConfigurationChanged(newConfig: Configuration?) {

    }

    /**
     * 是否需要监听返回键
     */
    override fun onBackPressed(): Boolean {
        return false
    }

    /**
     * view销毁
     */
    override fun onDetach() {
        lifecycle?.removeObserver(this)
        presenter?.cleanView()
        mViews.clear()
        fragment = null
        mActivity = null
        context = null
        baseActivity = null
        root = null
        rootViewGroup = null
        lifecycle = null
    }

    /**
     * 获取view接口
     */
    override fun getInterface(): Class<out IThatView>? {
        return classOfView?:null
    }

    protected open fun isDarkStatus(): Boolean {
        return false
    }

    protected open fun isNeedStatus(): Boolean {
        return false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected  fun setStatusText() {
        if (isAndroidMOrAbove() && (isDarkStatus() || isNeedStatus())) {
            setStatusTextColor(isDarkStatus())
        }
    }


    //Android Api 23以上
    private  fun isAndroidMOrAbove(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    /**
     * 设置状态栏颜色
     */
    protected  fun setStatusTextColor(isDark: Boolean) {
        val decor = mActivity!!.window.decorView
        if (isDark) {
            decor.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decor.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    /**
     * 返回上一级
     */
    open fun popBackView() {
        mActivity?.let {
            if (it.supportFragmentManager.backStackEntryCount == 0) {
                it.finish()
            } else {
                it.supportFragmentManager.popBackStack()
            }

        }
    }

    /**
     * 获取资源文件
     */
    protected fun getString(@StringRes resId: Int): String {
        return mActivity?.resources?.getString(resId)?:""
    }

    /**
     * activityResult
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    /**
     * 键盘弹起放下回调
     */
    override fun showJustPan(show: Boolean) {

    }

    /**
     * 是否正在显示dialog
     */
    override fun isShowDialog(): Boolean {
        return isShowingDialog
    }

    /**
     * 是否需要清空页面
     */
    override fun isNeedClean(): Boolean {
        return needClean
    }

    /**
     * 是否需要刷新
    */
    override fun isNeedRefresh(): Boolean {
        return needRefresh
    }

    /**
     * 设置view清空
     */
    override fun setViewClean(need: Boolean) {
        needClean = need
    }

    /**
     * 设置view刷新
     */
    override fun setViewRefresh(need: Boolean) {
        needRefresh = need
    }
}