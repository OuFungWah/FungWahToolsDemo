package com.example.fungwahtoolsdemo.animdemo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;
import com.example.fungwahtoolsdemo.animdemo.adapter.AnimFragmentAdapter;
import com.example.fungwahtoolsdemo.animdemo.frament.Tween1Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Tween2Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Tween3Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Tween4Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Tween5Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 区枫华 on 2017/9/6.
 */

public class TweenActivity extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<BaseFragment> list = new ArrayList<>();
    private AnimFragmentAdapter adapter;
    private static final String TAB_NAME[] = {"Tween透明度", "Tween旋转", "Tween大小", "Tween复合动画", "Tween位移", "Tween6", "Tween7", "Tween8", "Tween9", "Tween10"};

    @Override
    protected int setLayoutId() {
        return R.layout.tween_main_activity;
    }

    @Override
    protected void initView() {
        viewPager = findView(R.id.viewpager);
        tabLayout = findView(R.id.tab_layout);
    }

    @Override
    protected void setView() {
        addList();
        for (int i = 0; i < list.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(TAB_NAME[i]));
        }
        adapter = new AnimFragmentAdapter(list, TAB_NAME, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private void addList() {
        list.add(new Tween1Fragment());
        list.add(new Tween2Fragment());
        list.add(new Tween3Fragment());
        list.add(new Tween4Fragment());
        list.add(new Tween5Fragment());
    }

    @Override
    protected void initListener() {

    }

}
