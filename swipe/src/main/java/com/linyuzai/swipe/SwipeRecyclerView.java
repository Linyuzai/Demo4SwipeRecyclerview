package com.linyuzai.swipe;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;
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

    public static abstract class SwipeAdapter extends Adapter {
        protected Set<View> swipeViewSet;

        private OnSwipeItemClickListener listener;

        public SwipeAdapter() {
            swipeViewSet = new HashSet<>();
        }

        public Set<View> getSwipeViewSet() {
            return swipeViewSet;
        }

        public OnSwipeItemClickListener getListener() {
            return listener;
        }

        public void setListener(OnSwipeItemClickListener listener) {
            this.listener = listener;
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
            }
            onBindSwipeViewHolder((SwipeViewHolder) holder, position);
        }

        public abstract SwipeViewHolder onCreateSwipeViewHolder(ViewGroup parent, int viewType);

        public abstract void onBindSwipeViewHolder(SwipeViewHolder holder, int position);

        public abstract int getSwipeLayoutId();

        public abstract int getSwipeViewId();

        /**
         * this method is standing off
         * 该方法暂时无用
         *
         * @param position
         * @return
         */
        @Deprecated
        public abstract boolean isItemSwipeSupported(int position);

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
