package com.example.fungwahtoolsdemo.animdemo.frament;

import android.animation.Animator;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;

import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtools.util.ToastUtil;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by FungWah on 2017/9/21.
 */

public class Lollipop1Fragment extends BaseFragment implements View.OnClickListener {

    private ImageView img;
    private FloatingActionButton fab;
    private Animator animator;
    private boolean flag = true;
    private MyAnimatorListener listener;
    private float height;
    private float width;
    private double radius;

    @Override
    protected int setLayoutId() {
        return R.layout.anim_lollipop1_fragment;
    }

    @Override
    protected void initView(View parent) {
        img = findView(R.id.img);
        fab = findView(R.id.fab);
        img.post(new Runnable() {
            @Override
            public void run() {
                height = img.getMeasuredHeight();
                width = img.getMeasuredWidth();
                radius = Math.sqrt(Math.pow(height, 2) + Math.pow(width, 2));
                Log.d("数据：", "height:" + height + " width:" + width + " radius:" + radius);
            }
        });
    }

    @Override
    protected void setView() {
    }

    @Override
    protected void initListener() {
        listener = new MyAnimatorListener();
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (animator == null || !animator.isRunning()) {
            switch (v.getId()) {
                case R.id.fab:
                    if (img.getVisibility() == View.VISIBLE) {
                        //创建圆形遮罩动画
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            animator = ViewAnimationUtils.createCircularReveal(img, (int) (width / 2), (int) (height / 2), (float) (radius / 2), 0);
                            flag = false;
                            animator.addListener(listener);
                            animator.setDuration(2000);
                            animator.start();
                        } else {
                            ToastUtil.showShort("请换个版本试试");
                        }
                    } else {
                        //创建圆形遮罩动画
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            animator = ViewAnimationUtils.createCircularReveal(img, (int) (width / 2), (int) (height / 2), 0, (float) (radius / 2));
                            flag = true;
                            animator.addListener(listener);
                            animator.setDuration(2000);
                            animator.start();
                        } else {
                            ToastUtil.showShort("请换个版本试试");
                        }
                    }
                    break;
            }
        } else {
            ToastUtil.showShort("动画运行中");
        }
    }

    class MyAnimatorListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animation) {
            if (flag) {
                img.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            Log.d("TAG", "结束");
            if (!flag) {
                img.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}
