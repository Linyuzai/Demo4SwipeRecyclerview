package com.linyuzai.demo4swiperecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.linyuzai.demo4swiperecyclerview.adapter.MyAdapter;
import com.linyuzai.swipe.XSwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    XSwipeRecyclerView swipeRecyclerView;
    MyAdapter adapter;
    List<String> list;
    Button button1;
    Button button2;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRecyclerView = (XSwipeRecyclerView) findViewById(R.id.swipe_recycler_view);
        button1 = (Button) findViewById(R.id.bt1);
        button2 = (Button) findViewById(R.id.bt2);
        button3 = (Button) findViewById(R.id.bt3);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        list = new ArrayList();
        for (int i = 0; i < 20; i++)
            list.add("" + i);
        adapter = new MyAdapter(list);
        swipeRecyclerView.setXAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                adapter.notifyDataRemoved(4);
                break;
            case R.id.bt2:
                adapter.notifyDataUpdated(5, "12345");
                break;
            case R.id.bt3:
                adapter.notifyDataAdded(5, "54321");
                break;
        }
    }
}
