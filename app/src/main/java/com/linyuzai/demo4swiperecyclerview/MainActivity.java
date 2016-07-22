package com.linyuzai.demo4swiperecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.linyuzai.demo4swiperecyclerview.adapter.MyAdapter;
import com.linyuzai.swipe.XSwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    XSwipeRecyclerView swipeRecyclerView;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRecyclerView = (XSwipeRecyclerView) findViewById(R.id.swipe_recycler_view);
        list = new ArrayList();
        for (int i = 0; i < 20; i++)
            list.add("" + i);
        swipeRecyclerView.setXAdapter(new MyAdapter(list));
    }
}
