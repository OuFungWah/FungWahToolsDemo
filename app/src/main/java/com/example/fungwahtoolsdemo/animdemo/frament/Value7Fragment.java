package com.example.fungwahtoolsdemo.animdemo.frament;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;

import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by 区枫华 on 2017/9/10.
 */

public class Value7Fragment extends BaseFragment {

    private ImageView img;
    private FloatingActionButton floatingActionButton;

    @Override
    protected int setLayoutId() {
        return R.layout.anim_value3_fragment;
    }

    @Override
    protected void initView(View parent) {
        img = findView(R.id.anim_value3_view);
        floatingActionButton = findView(R.id.fab);
    }

    @Override
    protected void setView() {
        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(getContext(),R.animator.object_anim1);
        objectAnimator.setTarget(img);
        objectAnimator.start();
    }

    @Override
    protected void initListener() {

    }
}
