package com.nhcz500.base.weiget;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nhcz500.base.utils.DimenTransitionUtil;

import org.jetbrains.annotations.NotNull;

public class GridItem extends RecyclerView.ItemDecoration {
    private float dividerWidthDp;
    private float dividerWidthTopDp;
    private float dividerWidthBotDp;

    public GridItem( Context context,float dividerWidthDp) {
        this.dividerWidthDp = DimenTransitionUtil.dp2px(context,dividerWidthDp);
    }

    public GridItem(Context context,float dividerWidthDp, float dividerWidthTopDp, float dividerWidthBotDp) {
        this.dividerWidthDp = DimenTransitionUtil.dp2px(context,dividerWidthDp);
        this.dividerWidthTopDp = DimenTransitionUtil.dp2px(context,dividerWidthTopDp);
        this.dividerWidthBotDp = DimenTransitionUtil.dp2px(context,dividerWidthBotDp);
    }

    @Override
    public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View child, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
        super.getItemOffsets(outRect, child, parent, state);
        int pos = parent.getChildAdapterPosition(child);
        int spanCount=((GridLayoutManager)parent.getLayoutManager()).getSpanCount();//获取spanCount
        int column = (pos) % spanCount+1;// 计算这个child 处于第几列
        outRect.top = (int) dividerWidthTopDp;
        outRect.bottom = (int) dividerWidthBotDp;
        //注意这里一定要先乘 后除  先除数因为小于1然后强转int后会为0
        outRect.left = (int) ((column-1) * dividerWidthDp / spanCount); //左侧为(当前条目数-1)/总条目数*divider宽度
        outRect.right = (int) ((spanCount-column)* dividerWidthDp / spanCount);//右侧为(总条目数-当前条目数)/总条目数*divider宽度
    }
}
