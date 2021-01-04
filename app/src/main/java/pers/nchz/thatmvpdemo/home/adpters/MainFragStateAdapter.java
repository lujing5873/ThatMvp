package pers.nchz.thatmvpdemo.home.adpters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class MainFragStateAdapter extends FragmentStateAdapter {
    private List<Fragment> list;
    public MainFragStateAdapter(@NonNull FragmentActivity fragmentActivity,List<Fragment> list) {
        super(fragmentActivity);
        this.list=list;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
