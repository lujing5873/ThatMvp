package pers.nchz.thatmvp.view

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import pers.nchz.thatmvp.delegate.ThatActivity

abstract class  RvView<T> : IRvView<T> {
    protected lateinit  var root: View
    protected var viewGroup: ViewGroup? = null
    protected lateinit var mActivity: AppCompatActivity
    protected  var baseActivity: ThatActivity? = null
    protected lateinit  var context: Context
    protected val mViews = SparseArray<View>()
    protected var position = 0
    protected lateinit  var list: List<T>
    protected lateinit  var adapter: RecyclerView.Adapter<*>

    override fun onCreate(
        container: ViewGroup,
        list: List<T>,
        adapter: RecyclerView.Adapter<*>
    ): IRvView<T> {

        if(getRootLayoutId()>0){
            root =
                LayoutInflater.from(container!!.context).inflate(getRootLayoutId(), container, false)
            if (root is ViewGroup) {
                viewGroup = root as ViewGroup
            }
            context = root?.context
            mActivity = context as AppCompatActivity
            if (mActivity is ThatActivity) {
                baseActivity = mActivity as ThatActivity
            }
            this.list = list
            this.adapter = adapter
            initView()
        }

        return this
    }

    override fun getRootView(): View? {
        return root
    }

    private  fun <T : View?> bindView(id: Int): T? {
        var view: T? = mViews[id] as T
        if (view == null) {
            view = root.findViewById<View>(id) as T?
            mViews.put(id, view)
        }
        return view
    }

       fun <T : View?> getView(id: Int): T? {
        return bindView(id)
    }
     fun setOnClickListener(listener: View.OnClickListener?, vararg ids: Int) {
        if (ids == null) {
            return
        }
        for (id in ids) {
            getView<View>(id)?.setOnClickListener(listener)
        }
    }

    override fun setParent(recyclerView: RecyclerView?): IRvView<T> {
        return this
    }

    override fun bind(position: Int) {
       this.position=position
    }

    /**
     * 从activity的presenter中获取别的View
     * @param viewClass
     * @param <T>
     * @return
    </T> */
    protected  fun <T : IThatView> activityView(viewClass: Class<T>): T? {
        var  view:T? =null
        baseActivity?.let {
            view=it.activityView(viewClass)
        }
        return view
    }
}