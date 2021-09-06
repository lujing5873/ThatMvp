package com.nhcz500.base.utils;

import android.graphics.Rect;
import android.view.View;

import com.nhcz500.freedialog.FreeCusDialog;

public class AndroidBug5497Workaround {
    // For more information, see https://issuetracker.google.com/issues/36911528
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    public static void assistView (FreeCusDialog view) {
        new AndroidBug5497Workaround(view);
    }


    private View mChildOfContent;
    private int usableHeightPrevious;
    private AndroidBug5497Workaround(FreeCusDialog dialog) {
        if (dialog != null&&dialog.getRootView()!=null) {
            mChildOfContent =dialog.getRootView();
            mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
                possiblyResizeChildOfContent(dialog);
            });
        }
    }

    private void possiblyResizeChildOfContent(FreeCusDialog dialog) {
        int usableHeightNow = computeUsableHeight();
        if(usableHeightPrevious==0){
            usableHeightPrevious = usableHeightNow;
        }
        System.out.println(">>>>>>>>>>>>>>>>"+usableHeightNow);
        if(usableHeightPrevious!=usableHeightNow){


            if(usableHeightPrevious>usableHeightNow){
                System.out.println("dialog弹出键盘>>>>>>>>>>>>");
            }else{
                System.out.println("dialog关闭键盘>>>>>>>>>>>>");
            }
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