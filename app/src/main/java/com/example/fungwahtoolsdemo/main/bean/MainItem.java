package com.example.fungwahtoolsdemo.main.bean;

/**
 * Created by 区枫华 on 2017/9/6.
 */

public class MainItem {

    private String title;
    private Class activity;
    private int backGroundId;

    public MainItem(String title, int backGroundId, Class activity) {
        this.activity = activity;
        this.title = title;
        this.backGroundId = backGroundId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class getActivity() {
        return activity;
    }

    public void setActivity(Class activity) {
        this.activity = activity;
    }

    public int getBackGroundId() {
        return backGroundId;
    }

    public void setBackGroundId(int backGroundId) {
        this.backGroundId = backGroundId;
    }
}
