package com.nhcz500.base.adapter;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nhcz500.base.R;
import com.nhcz500.base.model.BaseMultipleEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import pers.nchz.thatmvp.java.view.IRvBaseView;
import pers.nchz.thatmvp.kotlin.view.IRvView;

public abstract class EasyMuiAdapter<T extends BaseMultipleEntity> extends BaseQuickAdapter<T, BaseVH<IRvView>> {
    public EasyMuiAdapter() {
        super(R.layout.item_empty);
    }
    public EasyMuiAdapter(List<T> list) {
        super(R.layout.item_empty,list);
    }
    private int last=-1;
    private List<Object> realList=new ArrayList<>();
    @NotNull
    @Override
    protected BaseVH<IRvView> onCreateDefViewHolder(@NotNull ViewGroup parent, int viewType) {
        IRvView view=createView(parent,viewType);
        if(realList.isEmpty()){
            for(T cache:getData()){
                realList.add(cache.getData());
            }
        }
        view.onCreate(parent,realList,this);
        BaseVH<IRvView> vh=new BaseVH<>(view);
        view.setParent(getRecyclerView());
        return vh;
    }


    @Override
    protected void convert(@NotNull BaseVH<IRvView> helper, T t) {
        helper.view.bind(helper.getAdapterPosition());
        helper.view.setData(t.getData());
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

//        setEmptyView( R.layout.item_empty);
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    protected abstract IRvView createView(@NotNull ViewGroup parent, int viewType);

    @Override
    protected int getDefItemViewType(int position) {
        return getData().get(position).getItemType();
    }
}
