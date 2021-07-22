package com.nhcz500.base.adapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhcz500.base.R;
import com.nhcz500.base.utils.DimenTransitionUtil;
import java.util.List;
import pers.nchz.thatmvp.kotlin.view.IRvView;
import pers.nchz.thatmvp.kotlin.view.RvView;

public abstract class VpRvListView<T> extends RvView<List<T>> {
    protected RecyclerView rv;
    protected EasyAdapter<T> adp;
    @Override
    public int getRootLayoutId() {
        return R.layout.item_rv2;
    }

    @Override
    public void initView() {
        rv=getView(R.id.item_rv);
        setLayoutManger();
        adp=getAdp();
        rv.setAdapter(adp);

        setOther();
    }
    protected abstract EasyAdapter<T> getAdp();
    protected abstract void setOther();
    public void setLayoutManger(){
        rv.setLayoutManager(new LinearLayoutManager(mActivity));
    }
    @Override
    public IRvView<List<T>> setParent(RecyclerView recyclerView) {
        rv.setRecycledViewPool(recyclerView.getRecycledViewPool());
        return super.setParent(recyclerView);
    }

    public void setMarginTop(int top){
        RecyclerView.LayoutParams layoutParams= (RecyclerView.LayoutParams) root.getLayoutParams();
        layoutParams.topMargin= DimenTransitionUtil.dp2px(mActivity,top);
    }

    @Override
    public void setData(List<T> data) {
        if(adp!=null){
            adp.setList(data);
        }
    }
}
