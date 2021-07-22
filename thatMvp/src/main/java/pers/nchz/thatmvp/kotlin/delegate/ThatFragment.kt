package pers.nchz.thatmvp.kotlin.delegate

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pers.nchz.thatmvp.R
import pers.nchz.thatmvp.kotlin.view.IThatView

abstract class ThatFragment:Fragment() {
    protected var rootView: View? = null
    protected var view: IThatView? = null
    protected var mActivity: ThatActivity? = null
    private var isLoad = false
    private val savedInstanceState: Bundle? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as ThatActivity
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //            if((presenter==null)&&mActivity.getPresenterClass() == getPresenterClass()){
//                this.presenter= (P) mActivity.getPresenter();
//            }
        var save = arguments

        try { //分开写为了防止 fragment通过外部注入presenter或者view
            if (view == null) {
                view=getViewClass().newInstance()
            }
        } catch (e: Exception) {
            rootView = inflater.inflate(R.layout.fragment_error, container, false)
            return rootView
        }

        save = if (save == null) {
            onViewCreate(arguments)
        } else {
            val args = arguments
            if (args != null) {
                save.putAll(arguments)
            }
            onViewCreate(savedInstanceState)
        }

        if (rootView == null) {
            view?.let { _view ->
                _view.onCreate(inflater, container, this.savedInstanceState, this)
                _view.getInterface()?.let {
                    mActivity?.addView(_view)//activity presenter添加
                }
                rootView = _view.getRootView()
                _view.initView(save)
            }
        } else {
            view?.let {_view ->
                if(_view.isNeedClean()){
                    _view.onCreate(inflater, container, this.savedInstanceState, this)
                    rootView = _view.getRootView()
                    _view.initView(save)
                }
                _view.setViewClean(false)
            }
        }

        return rootView
    }

    override fun onResume() {

        if(mActivity?.presenter?.getLastView()!==view){
            view?.let {
                mActivity?.addView(it)
            }
        }
        super.onResume()
        view?.let {
            if(!isLoad||it.isNeedRefresh()&&!isHidden){
                it.lazyData(savedInstanceState)
                isLoad = true
                it.setViewRefresh(false)
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        var parent=  rootView?.parent as ViewGroup
        parent?.removeView(rootView)
    }

    override fun onDestroy() {
        isLoad = false
        //移除presenter对view的引用
        view?.onDetach()
        view?.let {
            it.onDetach()
            mActivity?.removeView(it)
        }
        mActivity = null
        super.onDestroy()
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
         view?.onConfigurationChanged(newConfig)
    }

    fun setLoad(load: Boolean) {
        isLoad = load
    }
    /**
     * 需要对Bundle进行操作 重写此方法
     * @param savedInstanceState bundle
     * @return
     */
    protected fun onViewCreate(savedInstanceState: Bundle?): Bundle? {
        return savedInstanceState
    }

    /**
     * view的实际class
     * @return view class
     */
    protected abstract fun getViewClass(): Class<out IThatView>

}