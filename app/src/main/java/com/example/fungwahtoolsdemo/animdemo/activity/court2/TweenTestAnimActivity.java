package com.example.fungwahtoolsdemo.animdemo.activity.court2;

import android.os.Bundle;

import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by FungWah on 2017/9/22.
 */

public class TweenTestAnimActivity extends BaseActivity {

    private Bundle bundle;

    @Override
    protected int getLayoutId() {
        return R.layout.court_anim_test1_activity;
    }

    @Override
    protected void initView() {
        bundle = getIntent().getExtras();
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (bundle.get("type").equals("scale"))
            overridePendingTransition(R.anim.tween_zoom_in, R.anim.tween_zoom_out);
        if (bundle.get("type").equals("alpha"))
            overridePendingTransition(R.anim.tween_fade_in, R.anim.tween_fade_out);
        if (bundle.get("type").equals("translate"))
            overridePendingTransition(R.anim.tween_move_none, R.anim.tween_move_out);
        if (bundle.get("type").equals("rotate"))
            overridePendingTransition(R.anim.tween_reverse_rotate_in, R.anim.tween_reverse_rotate_out);
    }
}
