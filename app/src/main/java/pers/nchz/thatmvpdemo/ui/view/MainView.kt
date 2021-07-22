package pers.nchz.thatmvpdemo.ui.view

import android.os.Bundle
import com.nhcz500.base.base.view.BaseView
import pers.nchz.thatmvpdemo.R
import pers.nchz.thatmvpdemo.ui.presenter.MainPre

class MainView :BaseView<MainPre>() {
    override fun initView(savedInstanceState: Bundle?) {

    }

    override fun getRootLayoutId(): Int {
       return R.layout.activity_main
    }
}