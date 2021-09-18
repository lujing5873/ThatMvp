package com.nhcz500.base.adapter;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nhcz500.base.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pers.nchz.thatmvp.view.IRvView;


public abstract class EasyAdapter<T> extends BaseQuickAdapter<T, BaseVH<IRvView>> {

    private int last=-1;

    private  RecyclerView.RecycledViewPool recycledViewPool;
    public EasyAdapter() {
        super(R.layout.item_empty);
    }

    public EasyAdapter(List<T> list) {
        super(R.layout.item_empty,list);
    }

    @NotNull
    @Override
    protected BaseVH<IRvView> onCreateDefViewHolder(@NotNull ViewGroup parent, int viewType) {
        IRvView view=createView(parent,viewType);
        view.onCreate(parent,getData(),this);
        BaseVH<IRvView> vh=new BaseVH(view.getRootView());
        vh.setView(view);
        view.setParent(getRecyclerView());
        return vh;
    }


    @Override
    protected void convert(@NotNull BaseVH<IRvView> helper, T t) {
        helper.view.bind(helper.getAdapterPosition());
        helper.view.setData(t);

    }

    protected abstract IRvView createView(@NotNull ViewGroup parent, int viewType);

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

//        setEmptyView( R.layout.item_empty);
    }

    public RecyclerView.RecycledViewPool getNewRecyclerViewPool(){
        if(recycledViewPool==null){
            recycledViewPool=new RecyclerView.RecycledViewPool();
        }
        return recycledViewPool;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public  interface ItemTouchHelperAdapter {
        //数据交换
        void onItemMove(int fromPosition,int toPosition);
        //数据删除
        void onItemDissmiss(int position);
    }
}
