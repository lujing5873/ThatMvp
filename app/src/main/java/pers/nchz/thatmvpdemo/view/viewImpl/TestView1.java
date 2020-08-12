package pers.nchz.thatmvpdemo.view.viewImpl;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import pers.nchz.thatmvp.view.ThatBaseSubView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.lifedelegate.Fragment1;
import pers.nchz.thatmvpdemo.lifedelegate.Fragment2;
import pers.nchz.thatmvpdemo.lifedelegate.Fragment3;
import pers.nchz.thatmvpdemo.lifedelegate.Fragment4;
import pers.nchz.thatmvpdemo.presenter.TestPresenter;
import pers.nchz.thatmvpdemo.view.ITestView1;

public class TestView1 extends ThatBaseSubView<TestPresenter> implements ITestView1 {
    ViewPager viewPager;
    TabLayout tabLayout;
    List<Fragment> fragmentList=new ArrayList<>();
    static  final String[] test=new String[]{"热门","漫展","车展","游戏展"};
    @Override
    public void initView(Bundle savedInstanceState) {
        tabLayout=getView(R.id.tl_test1);
        viewPager=getView(R.id.vp_test1);

        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment4());

        viewPager.setAdapter(new FragmentPagerAdapter(mActivity.getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return test[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
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
        return R.layout.view_test1;
    }
}
