package pers.nchz.thatmvpdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.bean.TestBean;

/**
 *
 */

public class NestedAdapter extends ListBaseAdapter<TestBean> {

    public NestedAdapter(Context context, List<TestBean> mList) {
        super(context, mList);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInfalter.inflate(R.layout.model_item_nested, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        System.out.println("onbindView");
        ViewHolder viewHolder= (ViewHolder) holder;
        viewHolder.itemT1.setText("this is the "+position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_t1)
        TextView itemT1;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
