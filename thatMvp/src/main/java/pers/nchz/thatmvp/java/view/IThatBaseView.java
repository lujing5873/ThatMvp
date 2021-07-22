package pers.nchz.thatmvp.java.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 *
 */

public interface IThatBaseView {
    int  getRootLayoutId();
    void initView(Bundle savedInstanceState);
    void lazyData(Bundle savedInstanceState);
    void onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState,Fragment fragment);
    View getRootView();
    Class<?extends IThatBaseView> getInterface();
    void onConfigurationChanged(Configuration newConfig);
    void onDetach();
    boolean onBackPressed();
    void showJustPan(boolean show);
    boolean isShowDialog();
    boolean isNeedClean();
    boolean isNeedRefresh();
    void setViewClean(boolean need);
    void setViewRefresh(boolean need);
    void onActivityResult(int requestCode, int resultCode,  Intent data);
}
