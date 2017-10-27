package com.example.fungwahtoolsdemo.animdemo.transition;

import android.animation.Animator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by FungWah on 2017/10/10.
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MyVisibility extends Visibility {

    private View topView;
    private View bottomView;
    private Context context;

    public MyVisibility(Context context, View topView, View bottomView) {
        this.topView = topView;
        this.bottomView = bottomView;
        this.context = context;
    }

    /**
     * 创建动画
     *
     * @param sceneRoot
     * @param startValues
     * @param endValues
     * @return
     */
    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        return super.createAnimator(sceneRoot, startValues, endValues);
    }

    /**
     * 计算进入动画方法
     *
     * @param transitionValues
     */
    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
    }

    /**
     * 计算退出动画方法
     *
     * @param transitionValues
     */
    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
    }

    /**
     * 出现时调用
     *
     * @param sceneRoot
     * @param view
     * @param startValues
     * @param endValues
     * @return
     */
    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return super.onAppear(sceneRoot, view, startValues, endValues);
    }

    /**
     * 消失时调用
     *
     * @param sceneRoot
     * @param view
     * @param startValues
     * @param endValues
     * @return
     */
    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return super.onDisappear(sceneRoot, view, startValues, endValues);
    }
}
