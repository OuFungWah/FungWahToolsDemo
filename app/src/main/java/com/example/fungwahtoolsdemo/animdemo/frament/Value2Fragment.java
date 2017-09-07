package com.example.fungwahtoolsdemo.animdemo.frament;

import android.animation.ValueAnimator;
import android.support.design.widget.FloatingActionButton;
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

public class Value2Fragment extends BaseFragment implements View.OnClickListener, ValueAnimator.AnimatorUpdateListener {

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

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float value1 = (float) valueAnimator1.getAnimatedValue();
        float value2 = (float) valueAnimator2.getAnimatedValue();
        float value3 = (float) valueAnimator3.getAnimatedValue();
        float value4 = (float) valueAnimator4.getAnimatedValue();
        img.setRotationX(value1);
        img2.setRotationX(value2);
        img3.setRotationX(value3);
        img4.setRotationX(value4);

    }
}
