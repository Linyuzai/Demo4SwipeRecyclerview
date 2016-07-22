package com.linyuzai.swipe.adapter;

import com.linyuzai.swipe.XSwipeRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public abstract class SimpleSwipeAdapter<T> extends XSwipeRecyclerView.XSwipeAdapter {

    private List<T> dataList;

    public SimpleSwipeAdapter(List<T> dataList) {
        this.dataList = dataList;
    }

    public T getData(int position) {
        return dataList.get(position);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
