package com.nhcz500.base.base.view;

import androidx.fragment.app.Fragment;

public interface IAcBaseView extends IBaseView {
    void replaceFragment(Fragment newFrag);
    void replaceFragmentNoBack(Fragment newFrag);
    void popFragment(int  index);
}
