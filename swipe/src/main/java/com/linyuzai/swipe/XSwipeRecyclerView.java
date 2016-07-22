package com.linyuzai.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class XSwipeRecyclerView extends LinearLayout {

    public static final String TAG = XSwipeRecyclerView.class.getSimpleName();

    protected SwipeRecyclerView swipeRecyclerView;
    protected XSwipeAdapter adapter;
    private boolean isUpAfterOpen;

    public XSwipeRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public XSwipeRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        swipeRecyclerView = new SwipeRecyclerView(context);
        addView(swipeRecyclerView);
    }

    public void setXAdapter(XSwipeAdapter adapter) {
        this.adapter = adapter;
        swipeRecyclerView.setAdapter(this.adapter);
    }

    public XSwipeAdapter getXAdapter() {
        return adapter;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isPointToClose(ev.getX(), ev.getY())) {
                    isUpAfterOpen = true;
                    closeAll();
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isUpAfterOpen) {
                    isUpAfterOpen = false;
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isPointToClose(float x, float y) {
        Set<View> viewSet = adapter.getSwipeViewSet();
        if (viewSet.size() == 0)
            return false;
        if (viewSet.size() > 1)
            return true;
        View openSwipeView = viewSet.iterator().next();
        int[] positions = new int[2];
        openSwipeView.getLocationInWindow(positions);
        if (positions[0] == 0 && positions[1] == 0)
            return false;
        if (x < positions[0])
            return true;
        View pointSwipeView = swipeRecyclerView.findChildViewUnder(x, y).findViewById(adapter.getSwipeViewId());
        if (openSwipeView == pointSwipeView)
            return false;
        return true;
    }

    public void closeAll() {
        Iterator viewSet = adapter.getSwipeViewSet().iterator();
        while (viewSet.hasNext()) {
            View view = (View) viewSet.next();
            ((SwipeLayout) view.getParent()).close();
        }
    }

    public static abstract class XSwipeAdapter extends SwipeRecyclerView.SwipeAdapter {

    }
}
