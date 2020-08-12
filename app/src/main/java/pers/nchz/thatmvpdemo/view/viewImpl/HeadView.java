package pers.nchz.thatmvpdemo.view.viewImpl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhouwei.library.CustomPopWindow;

import pers.nchz.thatmvp.view.ThatBaseSubView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.presenter.MainPresenter;
import pers.nchz.thatmvpdemo.view.IHeadView;

public class HeadView extends ThatBaseSubView<MainPresenter> implements IHeadView, View.OnClickListener {
     TextView title;
     ImageView menu;
    @Override
    public void initView(Bundle savedInstanceState) {
        setOnClickListener(this, R.id.iv_back,R.id.iv_menu);
        menu=getView(R.id.iv_menu);
        title=getView(R.id.tv_head_title);
    }
    @Override
    public void initData() {
    }

    @Override
    public int getOptionsMenuId() {
        return 0;
    }

    @Override
    public int getRootLayoutId() {
        return R.layout.view_head;
    }


    @Override
    public void test() {
        System.out.println("test");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                    mActivity.finish();
                break;
            case R.id.iv_menu:
                View contentView = LayoutInflater.from(context).inflate(R.layout.head_menu,null);
                //处理popWindow 显示内容
                contentView.findViewById(R.id.tv_head_menu1).setOnClickListener(this);
                contentView.findViewById(R.id.tv_head_menu2).setOnClickListener(this);
                contentView.findViewById(R.id.tv_head_menu3).setOnClickListener(this);
                contentView.findViewById(R.id.tv_head_menu4).setOnClickListener(this);

                //创建并显示popWindow
                CustomPopWindow mCustomPopWindow= new CustomPopWindow.PopupWindowBuilder(context)
                        .setView(contentView)
                        .create()
                        .showAsDropDown(menu,0,20);

                break;
            case R.id.tv_head_menu1:
                    presenter.setMainCurrentItem(1);
                break;
            case R.id.tv_head_menu2:
                presenter.setMainCurrentItem(2);
                break;
            case R.id.tv_head_menu3:
                presenter.setMainCurrentItem(3);
                break;
            case R.id.tv_head_menu4:
                presenter.setMainCurrentItem(4);
                break;
        }
    }
}
