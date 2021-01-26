package pers.nchz.thatmvpdemo.home.adpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.home.model.HomeData;
import pers.nchz.thatmvpdemo.home.views.imp.HomeView;

public class HomeVPAdapter extends RecyclerView.Adapter<HomeVPAdapter.ViewPagerViewHolder> {
    private List<String> list=new ArrayList<>();

    {
        list.add("1");
        list.add("2");
    }
    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeView homeView=new HomeView();
        homeView.onCreate(LayoutInflater.from(parent.getContext()),parent,null);
        ViewPagerViewHolder viewPagerViewHolder=new ViewPagerViewHolder(homeView.getRootView(),homeView);
        return viewPagerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        holder.view.initView(null);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        HomeView view;
        public ViewPagerViewHolder(@NonNull View itemView,HomeView view) {
            super(itemView);
            this.view=view;
        }
    }
}
