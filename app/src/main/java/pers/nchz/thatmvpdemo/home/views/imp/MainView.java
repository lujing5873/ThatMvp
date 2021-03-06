package pers.nchz.thatmvpdemo.home.views.imp;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pers.nchz.thatmvp.view.IThatBaseView;
import pers.nchz.thatmvp.view.ThatBaseView;
import pers.nchz.thatmvpdemo.R;
import pers.nchz.thatmvpdemo.home.fragments.HomeFrag;
import pers.nchz.thatmvpdemo.home.fragments.MessageFrag;
import pers.nchz.thatmvpdemo.home.fragments.MineFrag;
import pers.nchz.thatmvpdemo.home.presenters.MainPresenter;
import pers.nchz.thatmvpdemo.home.views.IHeadView;
import pers.nchz.thatmvpdemo.home.views.IMainView;

public class MainView extends ThatBaseView<MainPresenter> implements IMainView  {
    private BottomNavigationView navigationView;

    private HomeFrag homeFrag;
    private MessageFrag messageFrag;
    private MineFrag mineFrag;
    private int lastFrag;

    @Override
    public void initView(Bundle savedInstanceState) {
        addSubView(new StatusView(),savedInstanceState,0);
        navigationView=getView(R.id.nav_main);
        navigationView.setItemIconTintList(null);//关闭着色
        //每次都会重建 所以选择别的实现方式
//        NavController navController = Navigation.findNavController(mActivity, R.id.nav_host_fragment);
//        NavigationUI.setupWithNavController(navigationView, navController);
        homeFrag=new HomeFrag();
        messageFrag=new MessageFrag();
        mineFrag=new MineFrag();
        initFrag();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_main:
                        setFrag(0);
                        lastFrag=0;
                        break;
                    case R.id.nav_message:
                        setFrag(1);
                        lastFrag=1;
                        break;
                    case R.id.nav_mine:
                        setFrag(2);
                        lastFrag=2;
                        break;
                }
                return true;
            }
        });


    }

    private void initFrag(){
        System.out.println("initFrag");
        FragmentTransaction fragmentTransaction= mActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.nav_host_fragment,homeFrag,"homeFrag");
//        fragmentTransaction.add(R.id.nav_host_fragment,messageFrag,"messageFrag");
//        fragmentTransaction.add(R.id.nav_host_fragment,mineFrag,"mineFrag");
//        fragmentTransaction.hide(messageFrag);
//        fragmentTransaction.hide(mineFrag);
        fragmentTransaction.commit();

    }
    private void setFrag(int i){
        FragmentTransaction fragmentTransaction= mActivity.getSupportFragmentManager().beginTransaction();
        switch (lastFrag){
            case 0:
                fragmentTransaction.hide(homeFrag);
                break;
            case 1:
                fragmentTransaction.hide(messageFrag);
                break;
            case 2:
                fragmentTransaction.hide(mineFrag);
                break;
        }
        switch (i){
            case 0:
                fragmentTransaction.show(homeFrag);
                break;
            case 1:
                fragmentTransaction.show(messageFrag);
                break;
            case 2:
                fragmentTransaction.show(mineFrag);
                break;
        }
        fragmentTransaction.commit();
    }
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public Class<? extends IThatBaseView> getInterface() {
        return IMainView.class;
    }
}
