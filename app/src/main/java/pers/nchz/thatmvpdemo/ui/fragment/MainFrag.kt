package pers.nchz.thatmvpdemo.ui.fragment

import pers.nchz.thatmvp.delegate.ThatFragment
import pers.nchz.thatmvp.view.IThatView
import pers.nchz.thatmvpdemo.ui.view.MainView

class MainFrag: ThatFragment() {
    override fun getViewClass(): Class<out IThatView> {
        return MainView::class.java
    }
}