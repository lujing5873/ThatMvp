package pers.nchz.thatmvpdemo.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nhcz500.base.base.view.ActivityBaseView
import pers.nchz.thatmvpdemo.ui.fragment.MainFrag
import pers.nchz.thatmvpdemo.ui.presenter.MainPre

class MainAcView :ActivityBaseView<MainPre> (){
    override fun nowFragment(savedInstanceState: Bundle?): Fragment {
        return MainFrag()
    }
}