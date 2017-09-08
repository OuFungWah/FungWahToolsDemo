package com.example.fungwahtoolsdemo.animdemo.frament;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;

import java.text.DecimalFormat;

/**
 * Created by 区枫华 on 2017/9/7.
 */

public class Value4Fragment extends BaseFragment implements View.OnClickListener, ValueAnimator.AnimatorUpdateListener {

    private TextView changeTv1;
    private TextView changeTv2;
    private TextView changeTv3;
    private TextView changeTv4;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private FloatingActionButton fab;
    private ValueAnimator valueAnimator1;
    private ValueAnimator valueAnimator2;
    private ValueAnimator valueAnimator3;
    private ValueAnimator valueAnimator4;
    private boolean flag = true;

    @Override
    protected int setLayoutId() {
        return R.layout.anim_value2_fragment;
    }

    @Override
    protected void initView(View parent) {
        changeTv1 = findView(R.id.anim_value2_tv1);
        changeTv2 = findView(R.id.anim_value2_tv2);
        changeTv3 = findView(R.id.anim_value2_tv3);
        changeTv4 = findView(R.id.anim_value2_tv4);
        tv1 = findView(R.id.tv1);
        tv2 = findView(R.id.tv2);
        tv3 = findView(R.id.tv3);
        tv4 = findView(R.id.tv4);
        fab = findView(R.id.anim_value2_fab);
        valueAnimator1 = ValueAnimator.ofFloat(0, 2000);
        valueAnimator2 = ValueAnimator.ofFloat(55, 250);
        valueAnimator3 = ValueAnimator.ofFloat(0, 2000);
        valueAnimator4 = ValueAnimator.ofFloat(0, 1000);
    }

    @Override
    protected void setView() {
        tv1.setText("原始");
        tv2.setText("渐变色");
        tv3.setText("渐变色");
        tv4.setText("渐变色");
        valueAnimator1.setDuration(30000);
        valueAnimator2.setDuration(30000);
        valueAnimator3.setDuration(30000);
        valueAnimator4.setDuration(30000);
        valueAnimator1.setInterpolator(new AccelerateInterpolator());
        valueAnimator2.setInterpolator(new DecelerateInterpolator());
        valueAnimator3.setInterpolator(new AccelerateInterpolator());
        valueAnimator4.setInterpolator(new AccelerateInterpolator());
        valueAnimator1.setRepeatCount(Integer.MAX_VALUE);
        valueAnimator2.setRepeatCount(Integer.MAX_VALUE);
        valueAnimator3.setRepeatCount(Integer.MAX_VALUE);
        valueAnimator4.setRepeatCount(Integer.MAX_VALUE);
        valueAnimator1.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator2.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator3.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator4.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator1.addUpdateListener(this);
        valueAnimator2.addUpdateListener(this);
        valueAnimator3.addUpdateListener(this);
        valueAnimator4.addUpdateListener(this);
    }

    @Override
    protected void initOnClickListener() {
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.anim_value2_fab:
                if (flag) {
                    valueAnimator1.start();
                    valueAnimator2.start();
                    valueAnimator3.start();
                    valueAnimator4.start();
                    flag = !flag;
                } else {
                    valueAnimator1.cancel();
                    valueAnimator2.cancel();
                    valueAnimator3.cancel();
                    valueAnimator4.cancel();
                    flag = !flag;
                }
                break;
        }
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float value1 = (float) valueAnimator1.getAnimatedValue();
//        Log.d("fragment4",""+value1);
        float value2 = (float) valueAnimator2.getAnimatedValue();
        float value3 = (float) valueAnimator3.getAnimatedValue();
        float value4 = (float) valueAnimator4.getAnimatedValue();
        changeTv1.setText(new DecimalFormat("##0.00").format(value1));
        changeTv2.setText(new DecimalFormat("##0.00").format(value1));
        changeTv3.setText(new DecimalFormat("##0.00").format(value1));
        changeTv4.setText(new DecimalFormat("##0.00").format(value1));
        changeTv2.setTextColor(Color.argb(255, (int) value2, 255 - (int) value2, 255 - (int) value2));
        changeTv3.setTextColor(Color.argb(255, 255 - (int) value2, (int) value2, 255 - (int) value2));
        changeTv4.setTextColor(Color.argb(255, 255 - (int) value2, 255 - (int) value2, (int) value2));
    }
}