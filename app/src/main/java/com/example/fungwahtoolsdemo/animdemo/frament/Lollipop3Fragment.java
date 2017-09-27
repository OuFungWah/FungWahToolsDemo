package com.example.fungwahtoolsdemo.animdemo.frament;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fungwahtools.fragment.BaseFragment;
import com.example.fungwahtoolsdemo.R;
import com.example.fungwahtoolsdemo.animdemo.activity.court2.TweenTestAnimActivity;

/**
 * Created by FungWah on 2017/9/21.
 */

public class Lollipop3Fragment extends BaseFragment implements View.OnClickListener {

    private Button scale_btn;
    private Button alpha_btn;
    private Button translate_btn;
    private Button rotate_btn;

    @Override
    protected int setLayoutId() {
        return R.layout.anim_lollipop3_fragment;
    }

    @Override
    protected void initView(View parent) {
        scale_btn = findView(R.id.scale_btn);
        alpha_btn = findView(R.id.alpha_btn);
        translate_btn = findView(R.id.translate_btn);
        rotate_btn = findView(R.id.rotate_btn);
    }

    @Override
    protected void setView() {

    }

    @Override
    protected void initListener() {
        scale_btn.setOnClickListener(this);
        alpha_btn.setOnClickListener(this);
        translate_btn.setOnClickListener(this);
        rotate_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), TweenTestAnimActivity.class);
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.scale_btn: {
                bundle.putString("type", "scale");
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.tween_zoom_in, R.anim.tween_zoom_out);
            }
            break;
            case R.id.alpha_btn: {
                bundle.putString("type", "alpha");
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.tween_fade_in, R.anim.tween_fade_out);
            }
            break;
            case R.id.translate_btn: {
                bundle.putString("type", "translate");
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.tween_move_in, R.anim.tween_move_none);
            }
            break;
            case R.id.rotate_btn: {
                bundle.putString("type", "rotate");
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.tween_rotate_in, R.anim.tween_rotate_out);
            }
            break;
        }
    }
}
