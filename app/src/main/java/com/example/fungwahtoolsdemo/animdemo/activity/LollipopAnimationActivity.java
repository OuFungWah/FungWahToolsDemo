package com.example.fungwahtoolsdemo.animdemo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;
import com.example.fungwahtoolsdemo.animdemo.adapter.AnimFragmentAdapter;
import com.example.fungwahtoolsdemo.animdemo.frament.Lollipop1Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Lollipop2Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FungWah on 2017/9/21.
 */

public class LollipopAnimationActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<BaseFragment> list = new ArrayList<>();
    private AnimFragmentAdapter adapter;
    private static final String TAB_NAME[] = {"圆形遮盖", "转场动画", "ObjectAnimator_XML使用", "ObjectAnimator_PATH", "帧动画", "ObjectAnimator", "Tween7", "Tween8", "Tween9", "Tween10"};

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
        list.add(new Lollipop1Fragment());
        list.add(new Lollipop2Fragment());
    }

    @Override
    protected void initListener() {

    }
}
