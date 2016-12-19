package com.jwenfeng.sliderecyclerview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jwenfeng.library.pulltorefresh.BaseRefreshListener;
import com.jwenfeng.library.pulltorefresh.PullToRefreshLayout;
import com.jwenfeng.sliderecyclerview.library.SlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshLayout pullToRefreshLayout;
    private SlideRecyclerView recyclerView;
    private List<String> list;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.pull_to_refresh);
        recyclerView = (SlideRecyclerView) findViewById(R.id.recycler_view);
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("SlideRecyclerView"+i);
        }
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        recyclerView.setMode(SlideRecyclerView.MOD_BOTH);

        pullToRefreshLayout.setRefreshListener(new BaseRefreshListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshLayout.finishRefresh();
                    }
                },2000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshLayout.finishLoadMore();
                    }
                },2000);
            }
        });

    }
}
