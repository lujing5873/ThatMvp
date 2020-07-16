package pers.nchz.thatmvpdemo.widget;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.core.view.NestedScrollingParent;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by dell on 2018/3/8.
 */

public class MyNestedScrollParent extends LinearLayout implements NestedScrollingParent {
    private View top,end;
    private  final int freshenHeight=180;
    private Scroller mScroller;
    private boolean isStart;
    private boolean isFreshen;
    public MyNestedScrollParent(Context context) {
        this(context,null);
    }

    public MyNestedScrollParent(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyNestedScrollParent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, 0);
        mScroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        top=getChildAt(0);
        end=getChildAt(2);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        System.out.println(child);
        System.out.println(target);
//        System.out.println(nestedScrollAxes);
        isStart=false;
        System.out.println("onStartNestedScroll>>>");
        return true;
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        System.out.println("dyUnconsumed:"+dyUnconsumed+">>>>>>>>dyConsumed:"+dyConsumed);
        RecyclerView recyclerView=null;
        if(target instanceof  RecyclerView){
            recyclerView= (RecyclerView) target;
        }
        if(recyclerView==null){
            return;
        }

        if(dyUnconsumed<0){
            int addHeight=(int) (Math.abs(dyUnconsumed)*0.8)+top.getHeight();
            top.getLayoutParams().height=addHeight;
            top.requestLayout();
        }else if(dyUnconsumed>0){
//            System.out.println("dyUnconsumed:"+dyUnconsumed);
            int addHeight=(int) (Math.abs(dyUnconsumed)*0.8)+end.getHeight();
            System.out.println("end:"+addHeight);
            end.getLayoutParams().height=addHeight;
            end.requestLayout();
            scrollBy(0, (int) (0.8*dyUnconsumed));
        }
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        isStart=true;
        System.out.println("onNestedPreScroll>>>>>>>>");
        RecyclerView recyclerView=null;
        if(target instanceof  RecyclerView){
            recyclerView= (RecyclerView) target;
        }
        System.out.println("onNestedPreScroll>>>>>>>>2");
        if(recyclerView==null){
            return;
        }
        System.out.println("onNestedPreScroll>>>>>>>>3");
        int height=top.getHeight();
        if(dy>0){
            if(height>0){ //回收上拉
                System.out.println("dy:"+dy);
                System.out.println(top.getHeight());
                consumed[1]=dy;
                int hg=height-dy;
                if(hg<0){
                    hg=0;
                }
                top.getLayoutParams().height=hg;
                top.requestLayout();
            }
        }else if(dy<0){
            if(getScrollY()!=0){

                System.out.println("getScrollY:"+getScrollY());
                consumed[1]=dy;
                if(getScrollY()+dy<=0){
                    dy=-getScrollY();
                }
                scrollBy(0,dy);
            }
        }


//        System.out.println("isEnd:"+!target.canScrollVertically(1));
//        System.out.println("start:"+!target.canScrollVertically(-1));
//        if(!recyclerView.canScrollVertically(-1)&&dy<0){
//            consumed[1]=dy;
//            System.out.println("height:"+Math.abs(dy));
//            int addHeight=(int) (Math.abs(dy)*0.8)+top.getHeight();
//            System.out.println("addHeight:"+addHeight);
//            System.out.println("oldHeight:"+ top.getLayoutParams().height);
//            top.getLayoutParams().height=addHeight;
//            top.requestLayout();
//            System.out.println("newHeight:"+top.getLayoutParams().height);
//
//        }
    }

    @Override
    public void computeScroll() {
//        //判断是否还在滚动，还在滚动为true
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            end.getLayoutParams().height= mScroller.getCurrY();
            end.requestLayout();
            //更新界面
            postInvalidate();
        }
        super.computeScroll();
    }
    private  void smallScroll(int startX,int startY,int dx,int dy,int duration){
        mScroller.startScroll(startX,startY,dx,dy,duration);
        invalidate();//通知UI线程的更新
    }
    private void smallScroll(int startY,int endY){
//        System.out.println(startY+"endY:"+endY);
        ValueAnimator animator = ValueAnimator.ofInt(startY,endY);  //定义动画
        int range=startY-endY;
        range=200;
        animator.setDuration(range).start();
        Interpolator interpolator=new DecelerateInterpolator();
        animator.setInterpolator(interpolator);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int y= (int) animation.getAnimatedValue();
//                System.out.println("Y:"+y);
                top.getLayoutParams().height=y;
                top.requestLayout();
            }
        });
    }
    @Override
    public void onStopNestedScroll(View child) {
        if(!isStart){
            return;
        }
        System.out.println("stop>>>>>>>"+top.getHeight());
        if(top.getHeight()>freshenHeight){//回调刷新
            smallScroll(top.getHeight(),freshenHeight);
        }else
        if(end.getHeight()>freshenHeight){
            smallScroll(0,getScrollY(),0,-getScrollY()+freshenHeight,200);
        }

//        top.getLayoutParams().height=0;
//        top.requestLayout();
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        System.out.println("vy:"+velocityY);
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }
    public void setFreshen(boolean isFreshen){

        if(isFreshen){

        }else{

        }
        this.isFreshen=isFreshen;
    }
}
