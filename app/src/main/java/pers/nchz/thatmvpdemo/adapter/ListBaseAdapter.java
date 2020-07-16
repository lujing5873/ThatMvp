package pers.nchz.thatmvpdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public  abstract  class ListBaseAdapter<T> extends RecyclerView.Adapter {
    protected Context mContext;
    protected LayoutInflater mInfalter;
    protected List<T> mList = new ArrayList<>();

    public ListBaseAdapter(Context context, List<T> mList){
        this.mList = mList;
        this.mContext = context;
        this.mInfalter = LayoutInflater.from(context);

    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

    public List<T> getDataList() {
        return mList;
    }

    public void setDataList(Collection<T> list) {
        this.mList.clear();
        this.mList.addAll(list);
        notifyDataSetChanged();
    }

    public void addAll(Collection<T> list) {
        int lastIndex = this.mList.size();
        if (this.mList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void remove(int position) {
        if(this.mList.size() > 0) {
            mList.remove(position);
            notifyItemRemoved(position);
        }

    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

}