package com.example.fungwahtoolsdemo.animdemo.bean;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.animation.BaseInterpolator;

/**
 * Created by 区枫华 on 2017/9/7.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
public class MyInterpolator extends BaseInterpolator {
    @Override
    public float getInterpolation(float input) {
        return input;
    }
}
