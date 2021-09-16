package pers.nchz.thatmvp.kotlin.delegate

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pers.nchz.thatmvp.R
import pers.nchz.thatmvp.presenter.KPresenter
import pers.nchz.thatmvp.kotlin.view.IThatView

abstract class ThatActivity: AppCompatActivity() {
    /**
     * 保存的根view
     */
    protected var rootView: View? = null

    /**
     * 实际的view对象
     */
    protected var view: IThatView? = null

    /**
     * 实际的presenter对象
     */
    var presenter: KPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //实例化view和presenter
        //实例化view和presenter
        if (view == null || presenter == null) {
            try {
                view = getViewClass().newInstance()
                presenter = getPresenterClass().newInstance()
            } catch (e: Exception) {
                //出错加载错误页面
                println(e)
                setContentView(R.layout.activity_error)
                return
            }
        }
        var save: Bundle?=null
        //设置presenter
        intent?.extras?.let {
            save = it
        }
        rootView?: view?.let { //如果根View还没有创建
            it.onCreate(layoutInflater, null, savedInstanceState, null) //view 初始化
            rootView = it.getRootView() //赋值
        }
        view?.getInterface()?.let {
                presenter?.addView(view!!) //否则把view设置到presenter里
        }
        setContentView(rootView)
        view?.initView(save)

   }
    /**
     * view的实际class
     * @return view class
     */
    protected abstract fun getViewClass(): Class<out IThatView>

    /**
     * presenter的实际类
     * @return presenter class
     */
    protected abstract fun getPresenterClass(): Class<out KPresenter>


    override fun onDestroy() {
        println("onDestroy activity")
        presenter?.cleanView()
        presenter=null;
        view?.onDetach()
        super.onDestroy()
    }

    fun removeView(view: IThatView) {
        presenter?.removeView(view)
    }

     fun addView(view: IThatView) {
        presenter?.addView(view)
    }
     fun <T : IThatView> activityView(tClass: Class<T>): T? {
        return presenter?.getView(tClass)
    }

    fun getViewSize(): Int {
        return presenter?.getViewSize()?:0
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        view?.onConfigurationChanged(newConfig)
    }

    override fun onBackPressed() {
        if(presenter?.getLastBackView()!=null){
            return
        }
        super.onBackPressed()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter?.getLastBackView()?.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }


    open fun allFresh() {
        presenter?.let {
            for (sub in  it.getViews()){
                sub.setViewRefresh(true)
            }
        }
    }
}