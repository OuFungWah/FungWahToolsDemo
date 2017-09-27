package com.example.fungwahtoolsdemo.animdemo.frament;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtools.util.ToastUtil;
import com.example.fungwahtoolsdemo.R;
import com.example.fungwahtoolsdemo.animdemo.activity.court1.ExplodeActivity;
import com.example.fungwahtoolsdemo.animdemo.activity.court1.FadeActivity;
import com.example.fungwahtoolsdemo.animdemo.activity.court1.ShareActivity;
import com.example.fungwahtoolsdemo.animdemo.activity.court1.SlideActivity;

/**
 * Created by FungWah on 2017/9/21.
 */

public class Lollipop2Fragment extends BaseFragment implements View.OnClickListener {

    private Button explode_btn;
    private Button slide_btn;
    private Button fade_btn;
    private Button share_btn;
    private ImageView img;
    private TextView tv;
    private View view;

    @Override
    protected int setLayoutId() {
        return R.layout.anim_lollipop2_fragment;
    }

    @Override
    protected void initView(View parent) {
        explode_btn = findView(R.id.explode_btn);
        slide_btn = findView(R.id.slide_btn);
        fade_btn = findView(R.id.fade_btn);
        share_btn = findView(R.id.share_btn);
        img = findView(R.id.img);
        tv = findView(R.id.tv);
        view = findView(R.id.view);
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        explode_btn.setOnClickListener(this);
        slide_btn.setOnClickListener(this);
        fade_btn.setOnClickListener(this);
        share_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.explode_btn:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(new Intent(getContext(), ExplodeActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }else{
                    ToastUtil.showShort("请换一个机型再试");
                }
                break;
            case R.id.fade_btn:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(getActivity());
                    startActivity(new Intent(getContext(), FadeActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }else{
                    ToastUtil.showShort("请换一个机型再试");
                }
                break;
            case R.id.share_btn:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(new Intent(getContext(), ShareActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity(),Pair.create((View)img,"img"),Pair.create((View)tv,"tv"),Pair.create(view,"view")).toBundle());
                }else{
                    ToastUtil.showShort("请换一个机型再试");
                }
                break;
            case R.id.slide_btn:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(new Intent(getContext(), SlideActivity.class), ActivityOptions.makeSceneTransitionAnimation(getActivity()).toBundle());
                }else{
                    ToastUtil.showShort("请换一个机型再试");
                }
                break;
        }
    }
}
