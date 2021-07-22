package pers.nchz.thatmvp.java.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public interface IRvBaseView<T> {
    IRvBaseView onCreate(ViewGroup container, List<T> list,RecyclerView.Adapter adapter);
    int  getRootLayoutId();
    View getRootView();
    void initView();
    void setData(T data);
    IRvBaseView setParent(RecyclerView recyclerView);
    void bind(int position);
}
