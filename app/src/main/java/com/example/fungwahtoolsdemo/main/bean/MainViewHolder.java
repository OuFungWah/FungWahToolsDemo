package com.example.fungwahtoolsdemo.main.bean;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fungwahtools.bean.ListViewHolder;
import com.example.fungwahtoolsdemo.R;

/**
 * Created by 区枫华 on 2017/9/6.
 */

public class MainViewHolder extends ListViewHolder {

    public TextView textView;
    public RelativeLayout relativeLayout;

    public MainViewHolder(View view) {
        textView = (TextView)view.findViewById(R.id.main_item_title_tv);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.item_background);
    }

}
