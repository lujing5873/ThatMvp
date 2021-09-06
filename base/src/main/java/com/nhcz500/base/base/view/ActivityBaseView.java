package com.nhcz500.base.base.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nhcz500.base.R;

import pers.nchz.thatmvp.kotlin.presenter.KPresenter;

public abstract class ActivityBaseView<P extends KPresenter>  extends BaseView<P> implements IAcBaseView {
    private Fragment now;
    @Override
    public int getRootLayoutId() {
        return R.layout.activity_base;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        now=nowFragment(savedInstanceState);
        FragmentTransaction fragmentTransaction= getMActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fv_fragment,now,now.getTag());
        fragmentTransaction.commit();
    }

    protected abstract  Fragment nowFragment(Bundle savedInstanceState);
    @Override
    public void replaceFragment(Fragment newFrag) {
        now=newFrag;
        FragmentTransaction fragmentTransaction= getMActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fv_fragment,now,now.getTag());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void popFragment(int index) {

        FragmentManager fragmentManager= getMActivity().getSupportFragmentManager();
        int id=fragmentManager.getBackStackEntryAt(index).getId();
        fragmentManager.popBackStack(id,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        now=null;
    }

    @Override
    public void replaceFragmentNoBack(Fragment newFrag) {
        now=newFrag;
        FragmentTransaction fragmentTransaction= getMActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fv_fragment,now,now.getTag());
        fragmentTransaction.commit();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        now=null;
    }
}
