package com.example.fungwahtoolsdemo.animdemo.frament;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;

import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by FungWah on 2017/9/15.
 */

public class Value8Fragment extends BaseFragment implements View.OnClickListener {

    private ImageView img;
    private FloatingActionButton floatingActionButton;
    private ObjectAnimator animator;

    @Override
    protected int setLayoutId() {
        return R.layout.anim_value3_fragment;
    }

    @Override
    protected void initView(View parent) {
        img = findView(R.id.anim_value3_view);
        floatingActionButton = findView(R.id.fab);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void setView() {
        Path path = new Path();
        path.moveTo(0.0f,0.0f);
        path.lineTo(0.0f,200.0f);
        path.lineTo(200.0f,200.0f);
        path.lineTo(200.0f,0.0f);
        path.lineTo(0.0f,0.0f);
        path.close();
        animator = ObjectAnimator.ofFloat(img,"translationX","translationY",path).setDuration(3000);
    }

    @Override
    protected void initListener() {
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        animator.start();
    }
}
