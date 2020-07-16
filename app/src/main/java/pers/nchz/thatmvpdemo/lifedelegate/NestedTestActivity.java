package pers.nchz.thatmvpdemo.lifedelegate;

import androidx.core.widget.NestedScrollView;

import pers.nchz.thatmvp.delegate.ThatBaseActivity;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.delegate.NestedDelegate;

/**
 *
 */

public class NestedTestActivity extends ThatBaseActivity<NestedDelegate> {

    @Override
    protected Class getDelegateClass() {
        return NestedDelegate.class;
    }

}
