package com.example.fungwahtoolsdemo.animdemo.activity.court1;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Slide;
import android.widget.TextView;

import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by FungWah on 2017/9/21.
 */

public class SlideActivity extends BaseActivity {

    private TextView tv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Slide());
        }
        setContentView(getLayoutId());
        initView();
        setView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.court_anim_test1_activity;
    }

    @Override
    protected void initView() {
        tv = findView(R.id.tv);
    }

    @Override
    protected void setView() {
        tv.setText("SlideActivity");
    }

    @Override
    protected void initListener() {

    }
}
