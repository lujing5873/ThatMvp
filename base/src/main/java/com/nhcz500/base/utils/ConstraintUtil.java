package com.nhcz500.base.utils;

import androidx.annotation.IdRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.TransitionManager;

public class ConstraintUtil
{

    /**
     * 开始修改
     * @return
     */
    public static ConstraintBegin begin(ConstraintLayout constraintLayout)
    {
        return new ConstraintBegin(constraintLayout);
    }

    /**
     * 带动画的修改
     * @return
     */
    public static ConstraintBegin beginWithAnim(ConstraintLayout constraintLayout)
    {
        TransitionManager.beginDelayedTransition(constraintLayout);
        return begin(constraintLayout);
    }


    
    public static class ConstraintBegin
    {

        ConstraintLayout constraintLayout;
        ConstraintSet applyConstraintSet;
        private ConstraintBegin(ConstraintLayout constraintLayout) {
            this.constraintLayout = constraintLayout;
            if(applyConstraintSet==null){
                applyConstraintSet=new ConstraintSet();
            }
            applyConstraintSet.clone(constraintLayout);
        }

        /**
         * 清除关系<br/>
         * 注意：这里不仅仅会清除关系，还会清除对应控件的宽高为 w:0,h:0
         * @param viewIds
         * @return
         */
        public ConstraintBegin clear(@IdRes int... viewIds)
        {
            for (int viewId : viewIds)
            {
                applyConstraintSet.clear(viewId);
            }
            return this;
        }

        /**
         * 清除某个控件的，某个关系
         * @param viewId
         * @param anchor
         * @return
         */
        public ConstraintBegin clear(int viewId,int anchor)
        {
            applyConstraintSet.clear(viewId,anchor);
            return this;
        }
        /**
         * 为某个控件设置 margin
         * @param viewId 某个控件ID
         * @param left marginLeft
         * @param top   marginTop
         * @param right marginRight
         * @param bottom marginBottom
         * @return
         */
        public ConstraintBegin setMargin(@IdRes int viewId, int left, int top, int right, int bottom)
        {
            setMarginLeft(viewId,left);
            setMarginTop(viewId,top);
            setMarginRight(viewId,right);
            setMarginBottom(viewId,bottom);
            return this;
        }

        /**
         * 为某个控件设置 marginLeft
         * @param viewId 某个控件ID
         * @param left marginLeft
         * @return
         */
        public ConstraintBegin setMarginLeft(@IdRes int viewId, int left)
        {
            applyConstraintSet.setMargin(viewId, ConstraintSet.LEFT, left);
            return this;
        }

        /**
         * 为某个控件设置 marginRight
         * @param viewId 某个控件ID
         * @param right marginRight
         * @return
         */
        public ConstraintBegin setMarginRight(@IdRes int viewId, int right)
        {
            applyConstraintSet.setMargin(viewId, ConstraintSet.RIGHT, right);
            return this;
        }

        /**
         * 为某个控件设置 marginTop
         * @param viewId 某个控件ID
         * @param top marginTop
         * @return
         */
        public ConstraintBegin setMarginTop(@IdRes int viewId, int top)
        {
            applyConstraintSet.setMargin(viewId, ConstraintSet.TOP, top);
            return this;
        }

        /**
         * 为某个控件设置marginBottom
         * @param viewId 某个控件ID
         * @param bottom marginBottom
         * @return
         */
        public ConstraintBegin setMarginBottom(@IdRes int viewId, int bottom)
        {
            applyConstraintSet.setMargin(viewId, ConstraintSet.BOTTOM, bottom);
            return this;
        }

        /**
         * 为某个控件设置关联关系 left_to_left_of
         * @param startId 
         * @param endId 
         * @return
         */
        public ConstraintBegin Left_toLeftOf(@IdRes int startId, @IdRes int endId)
        {
            applyConstraintSet.connect(startId, ConstraintSet.LEFT, endId, ConstraintSet.LEFT);
            return this;
        }

        /**
         * 为某个控件设置关联关系 left_to_right_of
         * @param startId
         * @param endId
         * @return
         */
        public ConstraintBegin Left_toRightOf(@IdRes int startId, @IdRes int endId)
        {
            applyConstraintSet.connect(startId, ConstraintSet.LEFT, endId, ConstraintSet.RIGHT);
            return this;
        }

        /**
         * 为某个控件设置关联关系 top_to_top_of
         * @param startId
         * @param endId
         * @return
         */
        public ConstraintBegin Top_toTopOf(@IdRes int startId, @IdRes int endId)
        {
            applyConstraintSet.connect(startId, ConstraintSet.TOP, endId, ConstraintSet.TOP);
            return this;
        }

        /**
         * 为某个控件设置关联关系 top_to_bottom_of
         * @param startId
         * @param endId
         * @return
         */
        public ConstraintBegin Top_toBottomOf(@IdRes int startId, @IdRes int endId)
        {
            applyConstraintSet.connect(startId, ConstraintSet.TOP, endId, ConstraintSet.BOTTOM);
            return this;
        }
        /**
         * 为某个控件设置关联关系 right_to_left_of
         * @param startId
         * @param endId
         * @return
         */
        public ConstraintBegin Right_toLeftOf(@IdRes int startId, @IdRes int endId)
        {
            applyConstraintSet.connect(startId, ConstraintSet.RIGHT, endId, ConstraintSet.LEFT);
            return this;
        }
        /**
         * 为某个控件设置关联关系 right_to_right_of
         * @param startId
         * @param endId
         * @return
         */
        public ConstraintBegin Right_toRightOf(@IdRes int startId, @IdRes int endId)
        {
            applyConstraintSet.connect(startId, ConstraintSet.RIGHT, endId, ConstraintSet.RIGHT);
            return this;
        }
        /**
         * 为某个控件设置关联关系 bottom_to_bottom_of
         * @param startId
         * @param endId
         * @return
         */
        public ConstraintBegin Bottom_toBottomOf(@IdRes int startId, @IdRes int endId)
        {
            applyConstraintSet.connect(startId, ConstraintSet.BOTTOM, endId, ConstraintSet.BOTTOM);
            return this;
        }

        /**
         * 为某个控件设置关联关系 bottom_to_top_of
         * @param startId
         * @param endId
         * @return
         */
        public ConstraintBegin Bottom_toTopOf(@IdRes int startId, @IdRes int endId)
        {
            applyConstraintSet.connect(startId, ConstraintSet.BOTTOM, endId, ConstraintSet.TOP);
            return this;
        }


        public ConstraintBegin CenterHorizontally(@IdRes int startId, @IdRes int endId)
        {
            applyConstraintSet.centerHorizontally(startId,endId);
            return this;
        }

        public ConstraintBegin CenterVertically(@IdRes int startId, @IdRes int endId)
        {
            applyConstraintSet.centerVertically(startId,endId);
            return this;
        }





        /**
         * 为某个控件设置宽度
         * @param viewId
         * @param width
         * @return
         */
        public ConstraintBegin setWidth(@IdRes int viewId, int width)
        {
            applyConstraintSet.constrainWidth(viewId, width);
            return this;
        }

        /**
         * 某个控件设置高度
         * @param viewId
         * @param height
         * @return
         */
        public ConstraintBegin setHeight(@IdRes int viewId, int height)
        {
            applyConstraintSet.constrainHeight(viewId, height);
            return this;
        }

        /**
         * 提交应用生效
         */
        public void commit()
        {
            applyConstraintSet.applyTo(constraintLayout);
        }

    }
}