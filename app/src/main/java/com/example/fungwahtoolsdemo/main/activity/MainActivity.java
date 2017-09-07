package com.example.fungwahtoolsdemo.main.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;
import com.example.fungwahtoolsdemo.animdemo.activity.TweenActivity;
import com.example.fungwahtoolsdemo.animdemo.adapter.AnimFragmentAdapter;
import com.example.fungwahtoolsdemo.animdemo.frament.Tween1Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Tween2Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Tween3Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Tween4Fragment;
import com.example.fungwahtoolsdemo.animdemo.frament.Tween5Fragment;
import com.example.fungwahtoolsdemo.main.adapter.MyListAdapter;
import com.example.fungwahtoolsdemo.main.bean.MainItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private MyListAdapter adapter;
    private List<MainItem> list = new ArrayList<>();
    private Random random = new Random();

    private static final String TITLE[] = {"Tween动画"};
    private static final Class ACTIVITIES[] = {TweenActivity.class};
    private static final int BACKGROUND_ID[] = {R.drawable.holo_blue_bg, R.drawable.holo_green_bg, R.drawable.holo_orange_bg, R.drawable.holo_purple_bg, R.drawable.holo_red_bg};

    @Override
    protected int setLayoutId() {
        return R.layout.main_activity;
    }

    @Override
    protected void initView() {
        listView = findView(R.id.listView);
        initList();
        adapter = new MyListAdapter(list, this);
        listView.setAdapter(adapter);
    }

    private void initList() {
        for (int i = 0; i < ACTIVITIES.length; i++) {
            list.add(new MainItem(TITLE[i], BACKGROUND_ID[random.nextInt(4)], ACTIVITIES[i]));
        }
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(list.get(position).getActivity());
    }
}
