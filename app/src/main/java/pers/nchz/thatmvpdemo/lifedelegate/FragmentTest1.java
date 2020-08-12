package pers.nchz.thatmvpdemo.lifedelegate;

import pers.nchz.thatmvp.delegate.ThatBaseFragment;
import pers.nchz.thatmvpdemo.delegate.MainDelegate;

public class FragmentTest1 extends ThatBaseFragment<MainDelegate> {
    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }
}
