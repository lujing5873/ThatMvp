package pers.nchz.thatmvp.java.utils;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;

import pers.nchz.thatmvp.java.view.IThatBaseView;

public class AndroidBug5497Workaround {
    // For more information, see https://issuetracker.google.com/issues/36911528
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    public static void assistView (IThatBaseView view) {
        new AndroidBug5497Workaround(view);
    }


    private View mChildOfContent;
    private int usableHeightPrevious;
    private ViewGroup.LayoutParams frameLayoutParams;

    private AndroidBug5497Workaround(IThatBaseView view) {
        if (view != null&&view.getRootView()!=null) {
            mChildOfContent = view.getRootView();
            mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
                if(view.isShowDialog()){
                    return;
                }
                possiblyResizeChildOfContent(view);
            });
            frameLayoutParams = mChildOfContent.getLayoutParams();
        }
    }

    private void possiblyResizeChildOfContent(IThatBaseView view) {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious) {
            //如果两次高度不一致 //将计算的可视高度设置成视图的高度
            if(usableHeightPrevious>usableHeightNow){
                view.showJustPan(true);
            }else{
                view.showJustPan(false);
            }
            frameLayoutParams.height = usableHeightNow;
            mChildOfContent.requestLayout();
            //请求重新布局
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        //计算视图可视高度
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return (r.bottom);
    }
}