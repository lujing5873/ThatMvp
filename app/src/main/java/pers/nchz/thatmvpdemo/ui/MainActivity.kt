package pers.nchz.thatmvpdemo.ui

import pers.nchz.thatmvp.kotlin.delegate.ThatActivity
import pers.nchz.thatmvp.kotlin.presenter.KPresenter
import pers.nchz.thatmvp.kotlin.view.IThatView
import pers.nchz.thatmvpdemo.ui.presenter.MainPre
import pers.nchz.thatmvpdemo.ui.view.MainAcView

class MainActivity:ThatActivity() {
    override fun getViewClass(): Class<out IThatView> {
        return MainAcView::class.java
    }

    override fun getPresenterClass(): Class<out KPresenter> {
        return MainPre::class.java
    }
}