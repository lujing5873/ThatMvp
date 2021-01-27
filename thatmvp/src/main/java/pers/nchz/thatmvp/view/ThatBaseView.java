package pers.nchz.thatmvp.view;


import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import pers.nchz.thatmvp.R;
import pers.nchz.thatmvp.delegate.ThatBaseActivity;
import pers.nchz.thatmvp.presenter.ThatBasePresenter;

/**
 *
 */

public abstract class ThatBaseView <P extends ThatBasePresenter> extends ViewGroup implements IThatBaseView {


    protected Context context;
    protected AppCompatActivity mActivity;
    protected ThatBaseActivity baseActivity;
    protected P presenter;
    protected View rootView;
    protected ViewGroup rootViewGroup;
    protected final SparseArray<View> mViews = new SparseArray<View>();

    public ThatBaseView(Context context,int type){
        super(context);
    }
    public ThatBaseView(Context context) {
        this(context,null);
    }

    public ThatBaseView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ThatBaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public ThatBaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
//        if(baseActivity!=null){
//            return;
//        }
        baseActivity= (ThatBaseActivity) context;
        onCreate(LayoutInflater.from(context),null,null);
        setPresenter(baseActivity.getPresenter());
        presenter.addView(this.getInterface(),this);
        initView(null);
        this.context=context;
        addView(rootView);
    }

    @Override
    public void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();
        if(rootLayoutId!=0){
            rootView = inflater.inflate(rootLayoutId, container, false);
            if(rootView instanceof ViewGroup){
                rootViewGroup= (ViewGroup) rootView;
            }else{
                rootViewGroup=null;
            }
            context=rootView.getContext();
            mActivity= (AppCompatActivity) context;
        }
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

    public void addSubView(IThatBaseView view,Bundle savedInstanceState){
        if(rootViewGroup==null){
            return;
        }
        view.onCreate(LayoutInflater.from(context),rootViewGroup,savedInstanceState);
        if(view.getRootView()!=null){
            rootViewGroup.addView(view.getRootView());
            presenter.addView(view.getInterface(),view);
            view.setPresenter(presenter);
            view.initView(savedInstanceState);
        }
    }

    public void addSubView(IThatBaseView view,Bundle savedInstanceState,int index,int id){
        rootViewGroup=rootView.findViewById(id);
        if(rootViewGroup==null){
            return;
        }
        view.onCreate(LayoutInflater.from(context),rootViewGroup,savedInstanceState);
        if(view.getRootView()!=null){
            rootViewGroup.addView(view.getRootView());
            presenter.addView(view.getInterface(),view);
            view.setPresenter(presenter);
            view.initView(savedInstanceState);
        }
    }

    public void addSubView(IThatBaseView view,Bundle savedInstanceState,int index) {
        if(rootViewGroup==null){
            return;
        }
        view.onCreate(LayoutInflater.from(context), rootViewGroup, savedInstanceState);
        if (view.getRootView() != null) {
            rootViewGroup.addView(view.getRootView(), index);
            presenter.addView(view.getInterface(),view);
            view.setPresenter(presenter);
            view.initView(savedInstanceState);
        }
    }

    @Override
    public <PR extends ThatBasePresenter> void setPresenter(PR presenter) {
        this.presenter= (P) presenter;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        rootView.measure(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(rootView.getMeasuredWidth(),rootView.getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        rootView.layout(l, t, r, b);
    }
}

