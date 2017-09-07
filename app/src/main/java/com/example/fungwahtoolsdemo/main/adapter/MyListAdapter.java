package com.example.fungwahtoolsdemo.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import com.example.fungwahtools.activity.BaseActivity;
import com.example.fungwahtools.adapter.BaseListAdapter;
import com.example.fungwahtools.bean.ListViewHolder;
import com.example.fungwahtoolsdemo.R;
import com.example.fungwahtoolsdemo.main.activity.MainActivity;
import com.example.fungwahtoolsdemo.main.bean.MainItem;
import com.example.fungwahtoolsdemo.main.bean.MainViewHolder;

import java.util.List;

/**
 * Created by 区枫华 on 2017/9/6.
 */

public class MyListAdapter extends BaseListAdapter<MainViewHolder> implements View.OnClickListener {

    public MyListAdapter(List<MainItem> list, Context context) {
        super(list, context);
    }

    @Override
    protected MainViewHolder initViewHolder(View convertView) {
        return new MainViewHolder(convertView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_list_item;
    }

    @Override
    protected void setView(MainViewHolder viewHolder, int position) {
        MainItem item = (MainItem) list.get(position);
        viewHolder.textView.setText(item.getTitle());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            viewHolder.relativeLayout.setBackground(context.getResources().getDrawable(item.getBackGroundId()));
        }
        viewHolder.textView.setTag(position);
        viewHolder.textView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MainItem item = (MainItem) list.get((int) v.getTag());
        context.startActivity(new Intent(context, item.getActivity()));
    }
}
