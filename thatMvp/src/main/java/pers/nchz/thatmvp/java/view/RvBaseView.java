package pers.nchz.thatmvp.java.view;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import pers.nchz.thatmvp.java.delegate.ThatBaseActivity;

public abstract class RvBaseView<T> implements IRvBaseView<T>{
    protected View rootView;
    protected ViewGroup viewGroup;
    protected AppCompatActivity mActivity;
    protected ThatBaseActivity baseActivity;
    protected Context context;
    protected final SparseArray<View> mViews = new SparseArray<View>();
    protected int position;
    protected List<T> list;
    protected RecyclerView.Adapter adapter;
    @Override
    public IRvBaseView onCreate(ViewGroup container, List<T> list,RecyclerView.Adapter adapter) {
        int rootLayoutId = getRootLayoutId();
        if(rootLayoutId!=0){
            rootView = LayoutInflater.from(container.getContext()).inflate(rootLayoutId, container, false);
            if(rootView instanceof ViewGroup){
                viewGroup= (ViewGroup) rootView;
            }
            context=rootView.getContext();
            mActivity= (AppCompatActivity) context;
            if(mActivity instanceof ThatBaseActivity){
                baseActivity= (ThatBaseActivity) mActivity;
            }
            this.list=list;
            this.adapter=adapter;
            initView();
        }
        return this;
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    private <T extends View> T bindView(int id) {
        T view = (T) mViews.get(id);
        if (view == null) {
            view = (T) rootView.findViewById(id);
            mViews.put(id, view);
        }
        return view;
    }

    public <T extends View> T getView(int id) {
        return bindView(id);

    }

    public void setOnClickListener(View.OnClickListener listener, int...ids){
        if(ids==null){
            return;
        }
        for(int id:ids){
            getView(id).setOnClickListener(listener);
        }
    }

    @Override
    public IRvBaseView setParent(RecyclerView recyclerView) {
        return this;
    }
    @Override
    public void bind(int position){
        this.position=position;
    }

    public void setMarginTop(float dp){
        RecyclerView.LayoutParams layoutParams= (RecyclerView.LayoutParams) rootView.getLayoutParams();
        layoutParams.topMargin= dp2px(mActivity,dp);
    }

    public void setMarginBottom(float dp){
        RecyclerView.LayoutParams layoutParams= (RecyclerView.LayoutParams) rootView.getLayoutParams();
        layoutParams.bottomMargin= dp2px(mActivity,dp);
    }


    /**
     * 从activity的presenter中获取别的View
     * @param viewClass
     * @param <T>
     * @return
     */
    protected  <T extends IThatBaseView> T activityView(Class<T> viewClass) {
        return baseActivity==null?null:(T) baseActivity.activityView(viewClass);
    }

    /**
     * dp转换成px
     */
    private  int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }



    protected Intent startNewActivity(Class<? extends AppCompatActivity> classOf){
        Intent intent=new Intent(mActivity, classOf);
        return intent;
    }
}
