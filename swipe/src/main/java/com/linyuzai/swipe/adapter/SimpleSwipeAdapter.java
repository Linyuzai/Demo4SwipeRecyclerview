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

    public void notifyDataRemoved(int position) {
        if (position >= dataList.size())
            throw new ArrayIndexOutOfBoundsException(position);
        closeAll();
        dataList.remove(position);
        notifyItemRemoved(position);
        if (position != dataList.size())      // 这个判断的意义就是如果移除的是最后一个，就不用管它了
            notifyItemRangeChanged(position, dataList.size() - position);
        swipeViewSet.clear();
    }

    /**
     * not test
     *
     * @param position
     * @param object
     */
    public void notifyDataUpdated(int position, T object) {
        if (position >= dataList.size())
            throw new ArrayIndexOutOfBoundsException(position);
        dataList.remove(position);
        dataList.add(position, object);
        notifyItemChanged(position);
    }

    /**
     * not test
     *
     * @param position
     * @param object
     */
    public void notifyDataAdded(int position, T object) {
        if (position >= dataList.size())
            throw new ArrayIndexOutOfBoundsException(position);
        dataList.add(position, object);
        notifyItemInserted(position);
    }
}
