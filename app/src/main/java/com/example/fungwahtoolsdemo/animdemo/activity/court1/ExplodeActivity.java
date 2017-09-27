package com.example.fungwahtoolsdemo.animdemo.activity.court1;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Explode;

import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by FungWah on 2017/9/21.
 */

public class ExplodeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Explode());
        }
        setContentView(getLayoutId());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.court_anim_test1_activity;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {

    }
}
