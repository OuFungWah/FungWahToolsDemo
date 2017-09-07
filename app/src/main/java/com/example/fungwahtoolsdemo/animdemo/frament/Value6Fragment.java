package com.example.fungwahtoolsdemo.animdemo.frament;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;

import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by 区枫华 on 2017/9/7.
 */

public class Value6Fragment extends BaseFragment implements View.OnClickListener {

    private static final int SET_GONE = 0;
    private static final long ANIM_TIME = 500;

    private ImageView img;
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton leftFAB;
    private FloatingActionButton rightFAB;
    private FloatingActionButton upFAB;
    private FloatingActionButton downFAB;
    private FloatingActionButton enlargeFAB;
    private FloatingActionButton minifyFAB;
    private FloatingActionButton rotateFAB;
    private FloatingActionButton rotateXFAB;
    private FloatingActionButton rotateYFAB;
    private AnimatorSet set = new AnimatorSet();
    private boolean flag = false;

    private int transationX = 0;
    private int transationY = 0;
    private int scaleX = 1;
    private int scaleY = 1;
    private int rotate = 0;
    private int rotateX = 0;
    private int rotateY = 0;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case SET_GONE:
                    leftFAB.setVisibility(View.GONE);
                    rightFAB.setVisibility(View.GONE);
                    upFAB.setVisibility(View.GONE);
                    downFAB.setVisibility(View.GONE);
                    enlargeFAB.setVisibility(View.GONE);
                    minifyFAB.setVisibility(View.GONE);
                    rotateFAB.setVisibility(View.GONE);
                    rotateXFAB.setVisibility(View.GONE);
                    rotateYFAB.setVisibility(View.GONE);
                    break;
            }
            return false;
        }
    });
    private boolean isRunningAnim = false;

    private Thread protectThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(ANIM_TIME);
                isRunningAnim = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    @Override
    protected int setLayoutId() {
        return R.layout.anim_value4_fragment;
    }

    @Override
    protected void initView(View parent) {
        img = findView(R.id.anim_value4_img);
        floatingActionButton = findView(R.id.fab);
        leftFAB = findView(R.id.fab_left);
        rightFAB = findView(R.id.fab_right);
        upFAB = findView(R.id.fab_up);
        downFAB = findView(R.id.fab_down);
        enlargeFAB = findView(R.id.fab_enlarge);
        minifyFAB = findView(R.id.fab_minify);
        rotateFAB = findView(R.id.fab_square_turn);
        rotateXFAB = findView(R.id.fab_x_turn);
        rotateYFAB = findView(R.id.fab_y_turn);
        setAllClickable(false);
    }

    @Override
    protected void setView() {
    }

    @Override
    protected void initOnClickListener() {
        floatingActionButton.setOnClickListener(this);
        leftFAB.setOnClickListener(this);
        rightFAB.setOnClickListener(this);
        upFAB.setOnClickListener(this);
        downFAB.setOnClickListener(this);
        enlargeFAB.setOnClickListener(this);
        minifyFAB.setOnClickListener(this);
        rotateFAB.setOnClickListener(this);
        rotateXFAB.setOnClickListener(this);
        rotateYFAB.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (!isRunningAnim) {
            isRunningAnim = true;
            protectThread.start();
            switch (v.getId()) {
                case R.id.fab:
                    //按钮展开和收起动画
                    if (flag) {
                        btnHorizonDisappearAnim(leftFAB, 200);
                        btnHorizonDisappearAnim(rightFAB, 100);
                        btnHorizonDisappearAnim(upFAB, 400);
                        btnHorizonDisappearAnim(downFAB, 300);
                        btnVerticalDisappearAnim(rotateFAB, 300);
                        btnVerticalDisappearAnim(rotateXFAB, 500);
                        btnVerticalDisappearAnim(rotateYFAB, 400);
                        btnVerticalDisappearAnim(enlargeFAB, 200);
                        btnVerticalDisappearAnim(minifyFAB, 100);
                        setAllClickable(false);
                        flag = !flag;
                    } else {
                        btnHorizonAppearAnim(leftFAB, 200);
                        btnHorizonAppearAnim(rightFAB, 100);
                        btnHorizonAppearAnim(upFAB, 400);
                        btnHorizonAppearAnim(downFAB, 300);
                        btnVerticalAppearAnim(rotateFAB, 300);
                        btnVerticalAppearAnim(rotateXFAB, 500);
                        btnVerticalAppearAnim(rotateYFAB, 400);
                        btnVerticalAppearAnim(enlargeFAB, 200);
                        btnVerticalAppearAnim(minifyFAB, 100);
                        setAllClickable(true);
                        flag = !flag;
                    }
                    break;
                case R.id.fab_left:
                    ObjectAnimator.ofFloat(img, "translationX", transationX, transationX -= 100).setDuration(ANIM_TIME).start();
                    break;
                case R.id.fab_right:
                    ObjectAnimator.ofFloat(img, "translationX", transationX, transationX += 100).setDuration(ANIM_TIME).start();
                    break;
                case R.id.fab_up:
                    ObjectAnimator.ofFloat(img, "translationY", transationY, transationY -= 100).setDuration(ANIM_TIME).start();
                    break;
                case R.id.fab_enlarge:
                    set.cancel();
                    set.playTogether(ObjectAnimator.ofFloat(img, "scaleX", scaleX, scaleX += 1), ObjectAnimator.ofFloat(img, "scaleY", scaleY, scaleY += 1));
                    set.setDuration(ANIM_TIME);
                    set.start();
                    break;
                case R.id.fab_minify:
                    set.cancel();
                    set.playTogether(ObjectAnimator.ofFloat(img, "scaleX", scaleX, scaleX -= 1), ObjectAnimator.ofFloat(img, "scaleY", scaleY, scaleY -= 1));
                    set.setDuration(ANIM_TIME);
                    set.start();
                    break;
                case R.id.fab_square_turn:
                    ObjectAnimator.ofFloat(img, "rotation", rotate += 180).setDuration(ANIM_TIME).start();
                    break;
                case R.id.fab_x_turn:
                    ObjectAnimator.ofFloat(img, "rotationX", rotateX += 180).setDuration(ANIM_TIME).start();
                    break;
                case R.id.fab_y_turn:
                    ObjectAnimator.ofFloat(img, "rotationY", rotateY += 180).setDuration(ANIM_TIME).start();
                    break;
                case R.id.fab_down:
                    ObjectAnimator.ofFloat(img, "translationY", transationY, transationY += 100).setDuration(ANIM_TIME).start();
                    break;
            }
        }
    }

    protected void btnHorizonAppearAnim(View view, int distance) {
        ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(ANIM_TIME).start();
        ObjectAnimator.ofFloat(view, "translationX", distance, 0).setDuration(ANIM_TIME).start();
    }

    protected void btnVerticalAppearAnim(View view, int distance) {
        ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(ANIM_TIME).start();
        ObjectAnimator.ofFloat(view, "translationY", distance, 0).setDuration(ANIM_TIME).start();
    }

    protected void btnHorizonDisappearAnim(View view, int distance) {
        ObjectAnimator.ofFloat(view, "alpha", 1, 0).setDuration(ANIM_TIME).start();
        ObjectAnimator.ofFloat(view, "translationX", 0, distance).setDuration(ANIM_TIME).start();
    }

    protected void btnVerticalDisappearAnim(View view, int distance) {
        ObjectAnimator.ofFloat(view, "alpha", 1, 0).setDuration(ANIM_TIME).start();
        ObjectAnimator.ofFloat(view, "translationY", 0, distance).setDuration(ANIM_TIME).start();
    }

    protected void setAllClickable(final boolean flag) {
        leftFAB.setClickable(flag);
        rightFAB.setClickable(flag);
        upFAB.setClickable(flag);
        downFAB.setClickable(flag);
        enlargeFAB.setClickable(flag);
        minifyFAB.setClickable(flag);
        rotateFAB.setClickable(flag);
        rotateXFAB.setClickable(flag);
        rotateYFAB.setClickable(flag);
        if (flag) {
            leftFAB.setVisibility(View.VISIBLE);
            rightFAB.setVisibility(View.VISIBLE);
            upFAB.setVisibility(View.VISIBLE);
            downFAB.setVisibility(View.VISIBLE);
            enlargeFAB.setVisibility(View.VISIBLE);
            minifyFAB.setVisibility(View.VISIBLE);
            rotateFAB.setVisibility(View.VISIBLE);
            rotateXFAB.setVisibility(View.VISIBLE);
            rotateYFAB.setVisibility(View.VISIBLE);
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(ANIM_TIME);
                        if (!flag) {
                            handler.sendEmptyMessage(SET_GONE);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
