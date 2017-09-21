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
import com.example.fungwahtoolsdemo.animdemo.frament.Value1Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value2Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value3Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value4Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value5Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Value6Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 区枫华 on 2017/9/7.
 */

public class ValueAnimActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<BaseFragment> list = new ArrayList<>();
    private AnimFragmentAdapter adapter;
    private static final String TAB_NAME[] = {"ValueAnimator_Y轴旋转", "ValueAnimator_X轴旋转", "ValueAnimator_平面旋转", "ValueAnimator_实现数字动画", "ValueAnimator_xml配置", "帧动画", "Tween7", "Tween8", "Tween9", "Tween10"};

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
        list.add(new Value1Fragment());
        list.add(new Value2Fragment());
        list.add(new Value3Fragment());
        list.add(new Value4Fragment());
    }

    @Override
    protected void initListener() {

    }
}
