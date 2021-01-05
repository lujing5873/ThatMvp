package com.nhcz500.base.weiget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.journeyapps.barcodescanner.ViewfinderView;
import com.nhcz500.base.R;

public class CustomViewfinderView extends ViewfinderView {
    private ValueAnimator anim;
    private float value;
    private Rect srcRect;
    private Rect dscRect;
    private Bitmap bitmap;
    // view 的宽高
    private int mTotalWidth, mTotalHeight;
    public CustomViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.scan_line);
       }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap,srcRect,dscRect,paint);
    }
   public  void initAnimator(){
        anim=ValueAnimator.ofFloat(0,1);
        anim.setRepeatCount(ValueAnimator.INFINITE);//设置无限重复
//        anim.setRepeatMode(ValueAnimator.INFINITE);//设置重复模式
        anim.setDuration(1500);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (Float) animation.getAnimatedValue();
                if(dscRect!=null){
                    dscRect.bottom= (int) (mTotalHeight*value);
                    dscRect.top= (int) (mTotalHeight*value)-bitmap.getHeight();
                }
                postInvalidate();
            }
        });
        anim.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w;
        mTotalHeight = h;
        srcRect=new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        dscRect=new Rect(0,0,mTotalWidth,bitmap.getHeight());
    }
}
