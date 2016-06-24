package com.it5.custombehavior.refresh;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.it5.custombehavior.R;
import com.it5.custombehavior.refresh.adapter.BaseAdapter;
import com.it5.custombehavior.refresh.adapter.InfoAdapter;
import com.it5.custombehavior.refresh.bean.InfoBean;
import com.it5.custombehavior.refresh.view.PullBaseView;
import com.it5.custombehavior.refresh.view.PullRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Activity2 extends AppCompatActivity implements PullBaseView.OnRefreshListener,
        BaseAdapter.OnItemClickListener,BaseAdapter.OnItemLongClickListener,
        BaseAdapter.OnViewClickListener {

    private PullRecyclerView recyclerView;
    List<Object> mDatas;
    InfoAdapter infoAdapter;

    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        activity=this;
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (PullRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        infoAdapter=new InfoAdapter(this,mDatas,this);
        infoAdapter.setOnItemClickListener(this);
        infoAdapter.setOnItemLongClickListener(this);
        recyclerView.setAdapter(infoAdapter);
    }



    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            InfoBean infoBean=new InfoBean();
            infoBean.setText("TEXT__TEXT"+i);
            mDatas.add(infoBean);
        }
    }


    @Override
    public void onHeaderRefresh(final PullBaseView view) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                InfoBean info=new InfoBean();
                info.setText("Header");
                mDatas.add(0,info);
                infoAdapter.notifyDataSetChanged();
                view.onHeaderRefreshComplete();
            }
        }, 1000);

    }

    @Override
    public void onFooterRefresh(final PullBaseView view) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InfoBean info=new InfoBean();
                info.setText("Footer");
                mDatas.add(0,info);
                infoAdapter.notifyDataSetChanged();
                view.onFooterRefreshComplete();
            }
        }, 1000);
    }

    /**
     * 子View点击事件
     *
     * @param position item position
     * @param viewtype 点击的view的类型，调用时根据不同的view传入不同的值加以区分
     */
    @Override
    public void onViewClick(int position, int viewtype) {
        switch (viewtype){
            case 1://赞
                Toast.makeText(activity,"赞-position>>" + position, Toast.LENGTH_SHORT).show();
                break;
            case 2://评论
                Toast.makeText(activity, "评论-position>>" + position, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * item点击事件
     *
     * @param position
     */
    @Override
    public void onItemClick(int position) {
        Toast.makeText(activity,"点击" + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * item长按事件
     *
     * @param position
     */
    @Override
    public void onItemLongClick(int position) {
        Toast.makeText(activity,"长按点击" + position, Toast.LENGTH_SHORT).show();
    }


}
