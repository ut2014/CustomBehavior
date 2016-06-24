package com.it5.custombehavior.refresh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.it5.custombehavior.R;
import com.it5.custombehavior.refresh.bean.InfoBean;

import java.util.List;

/**
 * Created by IT5 on 2016/6/24.
 */
public class InfoAdapter extends BaseAdapter<InfoAdapter.MyViewHolder>{

    public InfoAdapter(Context context, List<Object> listDatas, OnViewClickListener onViewClickListener) {
        super(context, listDatas, onViewClickListener);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mInflater.inflate(R.layout.item_info,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        InfoBean infoBean= (InfoBean) listDatas.get(position);
        holder.tv.setText(infoBean.getText());
        holder.ivZ.setOnClickListener(
                //赞 viewtype=1代表赞点击事件
                new ViewClickListener(onViewClickListener,position,1)
        );
        holder.ivPl.setOnClickListener(
                //评论 viewtype=2代表评论点击事件
                new ViewClickListener(onViewClickListener,position,2)
        );
    }

    @Override
    public int getItemCount() {
        return listDatas.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView tv;//内容
        private final ImageView ivZ;//赞
        private final ImageView ivPl;//评论

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
            ivZ = (ImageView) itemView.findViewById(R.id.iv_z);
            ivPl = (ImageView) itemView.findViewById(R.id.iv_pl);
        }
    }

    /**
     * view 的占击事件
     */
    class ViewClickListener implements View.OnClickListener{

        OnViewClickListener onViewClickListener;
        int position;
        int viewtype;

        public ViewClickListener(OnViewClickListener onViewClickListener, int position, int viewtype) {
            this.onViewClickListener = onViewClickListener;
            this.position = position;
            this.viewtype = viewtype;
        }

        @Override
        public void onClick(View v) {
            onViewClickListener.onViewClick(position,viewtype);
        }
    }
}
