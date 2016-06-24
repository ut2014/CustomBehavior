package com.it5.custombehavior.refresh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.it5.custombehavior.R;

import java.util.List;

/**
 * Created by IT5 on 2016/6/24.
 */
public class TextAdapter extends BaseAdapter<TextAdapter.MyViewHolder>{

    public TextAdapter(Context context, List<Object> listDatas) {
        super(context, listDatas);
    }

    public TextAdapter(Context context, List<Object> listDatas, OnViewClickListener onViewClickListener) {
        super(context, listDatas, onViewClickListener);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String text= (String) listDatas.get(position);
        holder.tv.setText(text);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    @Override
    public int getItemCount() {
        return listDatas.size();
    }
}
