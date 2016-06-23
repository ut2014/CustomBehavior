package com.it5.custombehavior.header;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.it5.custombehavior.R;

import java.util.ArrayList;

/**
 * Created by IT5 on 2016/6/23.
 */
public class MyHeadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    public static final int TYPE_HEADER=0;
    public static final int TYPE_NORMAL=1;

    private ArrayList<String> mdatas=new ArrayList<>();

    private View mheaderview;
    private  OnItemClickListener mlistener;


    public void setheaderview(View mheaderview) {
        this.mheaderview = mheaderview;
        notifyItemChanged(0);
    }

    public View getheaderview() {
        return mheaderview;
    }

    public void adddatas(ArrayList<String> datas) {
        mdatas.addAll(datas);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener li) {
        mlistener = li;
    }

    interface OnItemClickListener{
        void onItemClick(int position,String data);
    }

    @Override
    public int getItemViewType(int position) {
        if (mheaderview==null)return TYPE_NORMAL;
        if (position==0)return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mheaderview!=null&& viewType==TYPE_HEADER)return new ViewHolder(mheaderview);
        View layout= LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item,parent,false);
        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==TYPE_HEADER)return;
        final int pos=getRealPosition(holder);
        final String data=mdatas.get(pos);
        if (holder instanceof ViewHolder){
            ((ViewHolder)holder).text.setText(data);
            if (mlistener==null)return;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mlistener.onItemClick(pos,data);
                }
            });
        }
    }

    public int getRealPosition(RecyclerView.ViewHolder holder){
        int position=holder.getLayoutPosition();
        return mheaderview==null?position:position-1;
    }
    @Override
    public int getItemCount() {
        return mheaderview==null?mdatas.size():mdatas.size()+1;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if(manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if(lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(holder.getLayoutPosition() == 0);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            if(itemView == mheaderview) return;
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
