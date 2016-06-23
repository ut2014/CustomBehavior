package com.it5.custombehavior.header;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.it5.custombehavior.R;

import java.util.ArrayList;

public class HeaderActivity extends Activity  {

    private RecyclerView recyclerview;

    private LinearLayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    private MyHeadAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header_recycleview);

        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        gridLayoutManager=new GridLayoutManager(this,2);
        /**
         * 设置了一个SpanSizeLookup，这个类是一个抽象类，而且仅有一个抽象方法getSpanSize，
         * 这个方法的返回值决定了我们每个position上的item占据的单元格个数，而我们这段代码综合上面
         * 为GridLayoutManager设置的每行的个数来解释的话，
         就是当前位置是header的位置，那么该item占据2个单元格，正常情况下占据1个单元格。
         */
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return madapter.getItemViewType(position) == madapter.TYPE_HEADER
                        ? gridLayoutManager.getSpanCount() : 1;
            }
        });
//        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setLayoutManager(gridLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        madapter=new MyHeadAdapter();
        madapter.adddatas(generateData());
        setHeader(recyclerview);
        madapter.setOnItemClickListener(new MyHeadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String data) {
                Toast.makeText(HeaderActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerview.setAdapter(madapter);
    }

    private void setHeader(RecyclerView recyclerview) {
        View header= LayoutInflater.from(this).inflate(R.layout.header_view,recyclerview,false);
        madapter.setheaderview(header);
    }

    private ArrayList<String> generateData() {
        ArrayList<String> ds=new ArrayList<>();
        for (int i=0;i<20;i++){
            ds.add("数据"+i);
        }
        return ds;
    }


}
