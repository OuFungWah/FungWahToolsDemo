package com.example.fungwahtoolsdemo.application;

import android.app.Application;

import com.example.fungwahtools.util.ToastUtil;
import com.example.fungwahtools.util.WindowSizeHelper;

/**
 * Created by FungWah on 2017/9/21.
 */

public class FungWahApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ToastUtil.init(this);
        WindowSizeHelper.init(this);
    }
}
