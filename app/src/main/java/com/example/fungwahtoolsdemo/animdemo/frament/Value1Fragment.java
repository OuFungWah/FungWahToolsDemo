package com.example.fungwahtoolsdemo.animdemo.frament;

import android.animation.ValueAnimator;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by 区枫华 on 2017/9/7.
 */

public class Value1Fragment extends BaseFragment implements View.OnClickListener {

    private ImageView img;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
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
        return R.layout.anim_value1_fragment;
    }

    @Override
    protected void initView(View parent) {
        img = findView(R.id.img1);
        img2 = findView(R.id.img2);
        img3 = findView(R.id.img3);
        img4 = findView(R.id.img4);
        tv1 = findView(R.id.tv1);
        tv2 = findView(R.id.tv2);
        tv3 = findView(R.id.tv3);
        tv4 = findView(R.id.tv4);
        fab = findView(R.id.fab_right);
        valueAnimator1 = ValueAnimator.ofFloat(0, 360);
        valueAnimator2 = ValueAnimator.ofFloat(0, 360);
        valueAnimator3 = ValueAnimator.ofFloat(0, 360);
        valueAnimator4 = ValueAnimator.ofFloat(0, 180);
    }

    @Override
    protected void setView() {
        tv1.setText("原始");
        tv2.setText("先快后慢");
        tv3.setText("动画时间增长1秒");
        tv4.setText("旋转角度缩小180");
        valueAnimator1.setDuration(1000);
        valueAnimator2.setDuration(1000);
        valueAnimator3.setDuration(2000);
        valueAnimator4.setDuration(1000);
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
        MyOnAnimtionUpdateListener listener = new MyOnAnimtionUpdateListener();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(200.0f, 300.0f);
        //设置动画运行时间为1000ms,即一秒
        valueAnimator.setDuration(1000);
        //设置动画重复的模式为倒转动画效果重新跑一次动画
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        //设置动画重复的次数为无限次
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        //设置动画中值的变化速率为逐渐加速
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        //设置值的变化范围为300.0到200.0
        valueAnimator.setFloatValues(300.0f, 200.0f);
        //设置在1000ms(1秒)延迟后再开始
        valueAnimator.setStartDelay(1000);

        valueAnimator1.addUpdateListener(listener);
        valueAnimator2.addUpdateListener(listener);
        valueAnimator3.addUpdateListener(listener);
        valueAnimator4.addUpdateListener(listener);
    }

    @Override
    protected void initListener() {
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_right:
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

    class MyOnAnimtionUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float value1 = (float) valueAnimator1.getAnimatedValue();
//            Log.d("fragment1",""+value1);
            float value2 = (float) valueAnimator2.getAnimatedValue();
            float value3 = (float) valueAnimator3.getAnimatedValue();
            float value4 = (float) valueAnimator4.getAnimatedValue();
            img.setRotationY(value1);
            img2.setRotationY(value2);
            img3.setRotationY(value3);
            img4.setRotationY(value4);

        }
    }

}
