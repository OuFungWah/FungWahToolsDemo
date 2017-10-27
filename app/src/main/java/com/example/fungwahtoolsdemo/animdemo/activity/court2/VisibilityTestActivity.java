package com.example.fungwahtoolsdemo.animdemo.activity.court2;

import android.widget.RelativeLayout;

import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by FungWah on 2017/10/10.
 */

public class VisibilityTestActivity extends BaseActivity {

    private RelativeLayout top_rl;
    private RelativeLayout bottom_rl;

    @Override
    protected int getLayoutId() {
        return R.layout.anim_self1_activity;
    }

    @Override
    protected void initView() {
        top_rl = findView(R.id.top_rl);
        bottom_rl = findView(R.id.bottom_rl);
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {

    }
}
