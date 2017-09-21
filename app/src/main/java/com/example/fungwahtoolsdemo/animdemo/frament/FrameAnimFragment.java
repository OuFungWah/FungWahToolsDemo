package com.example.fungwahtoolsdemo.animdemo.frament;

import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by FungWah on 2017/9/16.
 */

public class FrameAnimFragment extends BaseFragment {

    private ImageView imageView;

    @Override
    protected int setLayoutId() {
        return R.layout.frame_anim_fragment;
    }

    @Override
    protected void initView(View parent) {
        imageView = findView(R.id.frame_img);
    }

    @Override
    protected void setView() {
        imageView.setImageResource(R.drawable.running_anim);
        AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getDrawable();
        animationDrawable.start();
    }

    @Override
    protected void initListener() {

    }
}
