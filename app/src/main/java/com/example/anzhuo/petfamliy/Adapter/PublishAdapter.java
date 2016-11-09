package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.AdapterInfo.PublishInfo;
import com.example.anzhuo.petfamliy.AdapterInfo.Recommend;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

/**
 * Created by anzhuo on 2016/11/7.
 */
public class PublishAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mContext;
    Recommend recommend;
    List<Recommend> mList;
    ViewHolder viewHolder;
    public PublishAdapter(Context context,List<Recommend> list){
        this.mContext=context;
        this.mList=list;
    }
    private OnItmeClickListener mlistener;
    public void setOnClickListener(OnItmeClickListener mlistener){
        this.mlistener=mlistener;
    }
    public interface OnItmeClickListener{
        void onItemClicked(int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        viewHolder=new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.publish_item_layout
                ,parent,false),viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        recommend=mList.get(position);
        Uri uri=Uri.parse(recommend.getPhoto());
        Glide.with(mContext).load(uri).placeholder(R.drawable.aop).into(((ViewHolder)holder).iv_photo);
     //   ((ViewHolder)holder).iv_head.setImageResource(recommend.getHead());
      //  ((ViewHolder)holder).iv_photo.setImageResource(recommend.getPhoto());
        ((ViewHolder)holder).tv_content.setText(recommend.getContent());
        ((ViewHolder)holder).tv_name.setText(recommend.getName());
    }

    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView iv_head;
        TextView tv_name;
        ImageView iv_photo;
        TextView tv_content;
        int position;

        public ViewHolder(View itemView, final int viewType) {
            super(itemView);
            this.position = viewType;
            iv_head = (ImageView) itemView.findViewById(R.id.iv_head);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            iv_photo = (ImageView) itemView.findViewById(R.id.iv_photo);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            iv_photo.setOnClickListener(this);
            itemView.setTag(viewHolder);

        }

        @Override
        public void onClick(View view) {
            mlistener.onItemClicked(position);
        }
    }
}
