package pers.nchz.thatmvp.kotlin.view

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface IRvView<T> {
    fun onCreate(
        container: ViewGroup,
        list: List<T>,
        adapter: RecyclerView.Adapter<*>
    ): IRvView<T>

    fun getRootLayoutId(): Int
    fun getRootView(): View?
    fun initView()
    fun setData(data: T)
    fun setParent(recyclerView: RecyclerView?): IRvView<T>
    fun bind(position: Int)
}