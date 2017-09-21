package com.example.fungwahtoolsdemo.animdemo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;
import com.example.fungwahtoolsdemo.animdemo.adapter.AnimFragmentAdapter;
import com.example.fungwahtoolsdemo.animdemo.frament.FrameAnimFragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value1Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value2Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value3Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value4Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value5Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value6Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value7Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value8Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DiegoBrce on 2017/9/8.
 */

public class ObjectAnimatorActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<BaseFragment> list = new ArrayList<>();
    private AnimFragmentAdapter adapter;
    private static final String TAB_NAME[] = {"ObjectAnimator单独使用", "ObjectAnimator综合使用", "ObjectAnimator_XML使用", "ObjectAnimator_PATH", "帧动画", "ObjectAnimator", "Tween7", "Tween8", "Tween9", "Tween10"};

    @Override
    protected int getLayoutId() {
        return R.layout.anim_main_activity;
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
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    private void addList() {
        list.add(new Value5Fragment());
        list.add(new Value6Fragment());
        list.add(new Value7Fragment());
        list.add(new Value8Fragment());
        list.add(new FrameAnimFragment());
    }

    @Override
    protected void initListener() {

    }
}