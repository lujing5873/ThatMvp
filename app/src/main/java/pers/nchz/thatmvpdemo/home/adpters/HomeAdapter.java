package pers.nchz.thatmvpdemo.home.adpters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.home.model.HomeData;

public class HomeAdapter extends BaseQuickAdapter<HomeData, BaseViewHolder> {
    public HomeAdapter(List<HomeData> list) {
        super(R.layout.item_home_content,list);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeData homeData) {

    }

}
