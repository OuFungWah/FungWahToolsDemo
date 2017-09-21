package com.example.fungwahtoolsdemo.animdemo.frament;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by 区枫华 on 2017/9/6.
 */

public class Tween4Fragment extends BaseFragment implements View.OnClickListener {

    private ImageView imageView;
    private FloatingActionButton floatingActionButton;
    private Animation hyperspaceJumpAnim;

    @Override
    protected int setLayoutId() {
        return R.layout.anim_tween1_fragment;
    }

    @Override
    protected void initView(View parent) {
        imageView = findView(R.id.tween_img);
        floatingActionButton = findView(R.id.fab);
        hyperspaceJumpAnim = AnimationUtils.loadAnimation(getContext(), R.anim.tween_hyperspace_jump);
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                imageView.startAnimation(hyperspaceJumpAnim);
                break;
        }
    }
}
