package com.linyuzai.swipe;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/22 0022.
 */
public class SwipeRecyclerView extends RecyclerView {
    public static final String TAG = SwipeRecyclerView.class.getSimpleName();

    public SwipeRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter instanceof SwipeAdapter) {
            ((SwipeAdapter) adapter).swipeRecyclerView = this;
            Log.e(TAG, ((SwipeAdapter) adapter).swipeRecyclerView + "");
            super.setAdapter(adapter);
        } else
            throw new IllegalArgumentException("you need use SwipeRecyclerView.SwipeAdapter or its subclass instead of RecyclerView.Adapter");
    }

    @Override
    public SwipeAdapter getAdapter() {
        return (SwipeAdapter) super.getAdapter();
    }

    public static abstract class SwipeAdapter extends Adapter {
        protected SwipeRecyclerView swipeRecyclerView;

        protected Set<View> swipeViewSet;

        private OnSwipeItemClickListener listener;

        public SwipeAdapter() {
            swipeViewSet = new HashSet<>();
        }

        public Set<View> getSwipeViewSet() {
            return swipeViewSet;
        }

        public OnSwipeItemClickListener getOnSwipeItemClickListener() {
            return listener;
        }

        public void setOnSwipeItemClickListener(OnSwipeItemClickListener listener) {
            this.listener = listener;
        }

        public void closeAll() {
            Iterator viewSet = swipeViewSet.iterator();
            while (viewSet.hasNext()) {
                View view = (View) viewSet.next();
                ((SwipeLayout) view.getParent()).close();
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return onCreateSwipeViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            ((SwipeViewHolder) holder).position = position;
            if (((SwipeViewHolder) holder).swipeLayout != null) {
                ((SwipeViewHolder) holder).swipeLayout.close(false);
                ((SwipeViewHolder) holder).swipeLayout.setSwipeEnabled(isSwipeEnable(position));
            }
            onBindSwipeViewHolder((SwipeViewHolder) holder, position);
        }

        public abstract SwipeViewHolder onCreateSwipeViewHolder(ViewGroup parent, int viewType);

        public abstract void onBindSwipeViewHolder(SwipeViewHolder holder, int position);

        public abstract int getSwipeLayoutId();

        public abstract int getSwipeViewId();

        /**
         * @param position
         * @return
         */
        public abstract boolean isSwipeEnable(int position);

        public class SwipeViewHolder extends ViewHolder implements OnClickListener, SwipeLayout.SwipeListener {
            public int position;
            public SwipeLayout swipeLayout;

            public SwipeViewHolder(View itemView) {
                super(itemView);
                swipeLayout = (SwipeLayout) itemView.findViewById(getSwipeLayoutId());
                if (swipeLayout != null) {
                    swipeLayout.addDrag(SwipeLayout.DragEdge.Right, getSwipeViewId());
                    swipeLayout.addSwipeListener(this);
                    swipeLayout.getSurfaceView().setOnClickListener(this);
                }
            }

            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.onSwipeItemClick(position);
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                swipeViewSet.add(layout.findViewById(getSwipeViewId()));
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {
                swipeViewSet.remove(layout.findViewById(getSwipeViewId()));
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        }
    }

    public interface OnSwipeItemClickListener {
        void onSwipeItemClick(int position);
    }
}
