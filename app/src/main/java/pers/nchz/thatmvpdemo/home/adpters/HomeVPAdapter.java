package pers.nchz.thatmvpdemo.home.adpters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pers.nchz.thatmvp.presenter.ThatBasePresenter;
import pers.nchz.thatmvpdemo.home.views.imp.HomeContentView;

public class HomeVPAdapter extends RecyclerView.Adapter<HomeVPAdapter.ViewPagerViewHolder> {
    private ThatBasePresenter presenter;
    public HomeVPAdapter(ThatBasePresenter presenter){
        this.presenter=presenter;
    }
    private List<Integer> list=new ArrayList<>();
    {
        list.add(1);
        list.add(2);
    }
    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeContentView homeContentView =new HomeContentView(parent.getContext(),1);
        homeContentView.setPresenter(presenter);
        homeContentView.onCreate(LayoutInflater.from(parent.getContext()),parent,null);
        ViewPagerViewHolder viewPagerViewHolder=new ViewPagerViewHolder(homeContentView);
        return viewPagerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        Bundle bundle=new Bundle();
        bundle.putInt(HomeContentView.TYPE,list.get(position));
        holder.view.initView(bundle);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        HomeContentView view;
        public ViewPagerViewHolder(HomeContentView view) {
            super(view.getRootView());
            this.view=view;
        }
    }
}
