package com.linyuzai.demo4swiperecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linyuzai.demo4swiperecyclerview.R;
import com.linyuzai.swipe.adapter.SimpleSwipeAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class MyAdapter extends SimpleSwipeAdapter<String> {

    public MyAdapter(List<String> dataList) {
        super(dataList);
    }

    @Override
    public SwipeViewHolder onCreateSwipeViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ll_swipe, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindSwipeViewHolder(SwipeViewHolder holder, int position) {
        ((MyViewHolder) holder).textView.setText(getData(position));
    }

    @Override
    public int getSwipeLayoutId() {
        return R.id.swipe_layout;
    }

    @Override
    public int getSwipeViewId() {
        return R.id.swipe_view;
    }

    @Override
    public boolean isSwipeEnable(int position) {
        if (position == 1)
            return false;
        return true;
    }

    class MyViewHolder extends SwipeViewHolder {

        TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_view);
        }
    }
}
