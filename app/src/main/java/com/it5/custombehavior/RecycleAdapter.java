package com.it5.custombehavior;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IT5 on 2016/6/22.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewItem>{

    private List<String> lists;

    public RecycleAdapter() {
        this.lists = new ArrayList<>();
        for (int i=0;i<30;i++){
            lists.add("item"+i);
        }
    }

    @Override
    public ViewItem onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewItem(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.test_list_item,null));
    }

    @Override
    public void onBindViewHolder(ViewItem holder, int position) {
        holder.textView.setText(lists.get(position));
        holder.textView.setTextSize(20);
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewItem extends RecyclerView.ViewHolder{
        TextView textView;
        public ViewItem(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
