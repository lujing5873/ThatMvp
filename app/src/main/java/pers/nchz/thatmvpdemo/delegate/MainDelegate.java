package pers.nchz.thatmvpdemo.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import pers.nchz.thatmvp.delegate.ThatBaseDelegate;
import pers.nchz.thatmvpdemo.view.viewImpl.TestBaseView;
import pers.nchz.thatmvpdemo.view.viewImpl.TestView1;

public class MainDelegate extends ThatBaseDelegate<TestBaseView> {
    @Override
    protected Class<TestBaseView> getViewClass() {
        return TestBaseView.class;
    }


}
