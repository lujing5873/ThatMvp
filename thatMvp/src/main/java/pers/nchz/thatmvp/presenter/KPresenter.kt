package pers.nchz.thatmvp.presenter

import androidx.lifecycle.Lifecycle
import pers.nchz.thatmvp.kotlin.view.IThatView
import java.util.*

open class KPresenter {
    private var mViews = LinkedHashMap<Class<out IThatView>, IThatView>()
    var lifecycle: Lifecycle? = null

    fun <T : IThatView> addView(view: T) {
        mViews[view.getInterface()!!] = view;
    }

    fun <T : IThatView> removeView(view: T) {
        if (mViews[view.getInterface()] === view) {
            mViews.remove(view.getInterface())
        }
    }

    fun <T : IThatView> getView(viewClass: Class<T>): T? {
        mViews[viewClass]?.let {
            return it as? T
        }
        return null
    }

    fun cleanView() {
        mViews.clear()
        lifecycle = null
    }


    fun <T : IThatView> getLastView(): T? {
        val cache: Array<IThatView> = mViews.values.toTypedArray()
        return if (cache.isEmpty()) {
            null
        } else cache[cache.size - 1] as? T
    }

    fun getViewSize(): Int {
        return mViews.size
    }


    fun getLastBackView(): IThatView? {
        val cache: Array<IThatView> = mViews.values.toTypedArray()
        if (cache.isEmpty()) {
            return null
        }
        var position = -1
        for (i in cache.indices.reversed()) {
            val view = cache[i]
            if (view.onBackPressed()) {
                position = i
                break
            }
        }
        return if (position == -1) null else cache[position]
    }

     fun getViews(): ArrayList<IThatView> {
        return ArrayList(mViews?.values)
    }


}
