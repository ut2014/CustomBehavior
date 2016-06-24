package com.it5.custombehavior.refresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.it5.custombehavior.R;
import com.it5.custombehavior.refresh.adapter.BaseAdapter;
import com.it5.custombehavior.refresh.adapter.TextAdapter;
import com.it5.custombehavior.refresh.view.PullBaseView;
import com.it5.custombehavior.refresh.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Activity1 extends AppCompatActivity implements BaseAdapter.OnItemClickListener, BaseAdapter.OnItemLongClickListener,
        PullBaseView.OnRefreshListener {

    private PullRecyclerView recyclerView;
    List<Object> mDatas;
    TextAdapter textAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (PullRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textAdapter=new TextAdapter(this,mDatas);
        textAdapter.setOnItemClickListener(this);
        textAdapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(textAdapter);
    }

    private void initData() {
        mDatas=new ArrayList<>();
        for (int i=0;i<30;i++){
            mDatas.add("TEXT"+i);
        }
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onItemLongClick(int position) {

    }

    @Override
    public void onHeaderRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas.add(0,"TEXT Header");
                textAdapter.notifyDataSetChanged();
                recyclerView.onHeaderRefreshComplete();
            }
        }, 1000);
    }

    @Override
    public void onFooterRefresh(PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mDatas.add("TEXT Footer");
                textAdapter.notifyDataSetChanged();
                recyclerView.onFooterRefreshComplete();
            }
        }, 1000);
    }

}
