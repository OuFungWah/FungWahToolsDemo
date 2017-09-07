package com.example.fungwahtoolsdemo.animdemo.frament;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by 区枫华 on 2017/9/7.
 */

public class Value5Fragment extends BaseFragment implements View.OnClickListener, ValueAnimator.AnimatorUpdateListener {

    private View view;
    private FloatingActionButton floatingActionButton;
    private ValueAnimator valueAnimator;

    @Override
    protected int setLayoutId() {
        return R.layout.anim_value3_fragment;
    }

    @Override
    protected void initView(View parent) {
        view = findView(R.id.anim_value3_view);
        floatingActionButton = findView(R.id.fab);
    }

    @Override
    protected void setView() {
        valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(getContext(),R.animator.value_anim1);
        valueAnimator.addUpdateListener(this);
    }

    @Override
    protected void initOnClickListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        valueAnimator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float value = (float)animation.getAnimatedValue();
        view.setTranslationX(value);
    }
}
