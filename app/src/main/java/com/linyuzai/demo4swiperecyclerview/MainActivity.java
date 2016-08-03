package com.linyuzai.demo4swiperecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.linyuzai.demo4swiperecyclerview.adapter.MyAdapter;
import com.linyuzai.swipe.SwipeRecyclerView;
import com.linyuzai.swipe.XSwipeRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SwipeRecyclerView.OnSwipeItemClickListener {

    XSwipeRecyclerView swipeRecyclerView;
    MyAdapter adapter;
    List<String> list;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRecyclerView = (XSwipeRecyclerView) findViewById(R.id.swipe_recycler_view);
        button1 = (Button) findViewById(R.id.bt1);
        button2 = (Button) findViewById(R.id.bt2);
        button3 = (Button) findViewById(R.id.bt3);
        button4 = (Button) findViewById(R.id.bt4);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        list = new ArrayList();
        for (int i = 0; i < 20; i++)
            list.add("" + i);
        adapter = new MyAdapter(list);
        adapter.setOnSwipeItemClickListener(this);
        swipeRecyclerView.setXAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt1:
                adapter.notifyDataRemoved(1);
                break;
            case R.id.bt2:
                adapter.notifyDataUpdated(2, "12345");
                break;
            case R.id.bt3:
                adapter.notifyDataAdded(0, "54321",true);
                break;
            case R.id.bt4:
                adapter.notifyDataAdded("55555", true);
                break;
        }
    }

    @Override
    public void onSwipeItemClick(int position) {
        Toast.makeText(MainActivity.this, "click:" + position, Toast.LENGTH_SHORT).show();
    }
}
