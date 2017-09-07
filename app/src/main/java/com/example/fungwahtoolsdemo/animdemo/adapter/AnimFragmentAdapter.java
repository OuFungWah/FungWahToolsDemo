package com.example.fungwahtoolsdemo.animdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.fungwahtools.fragment.BaseFragment;

import java.util.List;

/**
 * Created by 区枫华 on 2017/9/6.
 */

public class AnimFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> list;
    private String title[];

    public AnimFragmentAdapter(List<BaseFragment> list,String title[], FragmentManager fm) {
        super(fm);
        this.list = list;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
