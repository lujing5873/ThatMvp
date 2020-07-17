package pers.nchz.thatmvpdemo.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.adapter.NestedAdapter;
import pers.nchz.thatmvpdemo.bean.ImageBean;
import pers.nchz.thatmvpdemo.bean.TestBean;
import pers.nchz.thatmvpdemo.presenter.NestedPresenter;

/**
 * Created by dell on 2018/3/8.
 */

public class NestedView extends ThatBaseView<NestedPresenter> implements INestedView {
    private NestedAdapter mAdapter;
    private RecyclerView recyclerView;
    private  List<TestBean> mList=new ArrayList<>();

    @Override
    public void initView(Bundle savedInstanceState) {
        presenter.getData();
    }

    @Override
    public void initData() {

    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.model_activity_nested;
    }


    @Override
    protected Class<NestedPresenter> getPresenterClass() {
        return NestedPresenter.class;
    }

    @Override
    public void newVoid() {
        for(int i=0;i<10;i++){
            mList.add(new TestBean());
        }
        recyclerView=getView(R.id.test_ry);
        mAdapter=new NestedAdapter(rootView.getContext(),mList );
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void getDataSuccess(List<ImageBean> list) {

    }

}
