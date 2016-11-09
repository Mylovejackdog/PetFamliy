package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.R;
import com.litao.android.lib.entity.PhotoEntry;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anzhuo on 2016/11/7.
 */
public class ChooseAdapter extends RecyclerView.Adapter<ChooseAdapter.ViewHolder> {
    private List<PhotoEntry> list=new ArrayList<PhotoEntry>();
    private Context mContext;
    private LayoutInflater mInflater;
    private OnItmeClickListener mlistener;
    public interface OnItmeClickListener{
        void onItemClicked(int position);
    }
    public ChooseAdapter(Context mContext,List<PhotoEntry> list) {
        this.mContext = mContext;
        this.list=list;
        mlistener = (OnItmeClickListener) mContext;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        list.add(createAddEntry());
    }
    public void reloadList(List<PhotoEntry> data) {
        if (data != null) {
            list.clear();
            list.addAll(data);
            list.add(createAddEntry());
        } else {
            list.clear();
        }
        notifyDataSetChanged();

    }
    public void appendList(List<PhotoEntry> data) {
        if (data != null) {
            list.addAll(list.size()-1,data);
        } else {
            list.clear();
        }
        notifyDataSetChanged();

    }
    public void appendPhoto(PhotoEntry entry) {
        if (entry != null) {
            list.add(list.size()-1,entry);
        }
        notifyDataSetChanged();
    }
    public List<PhotoEntry> getData(){
        return list.subList(0,list.size()-1);
    }
    public PhotoEntry getEntry(int position) {
        return list.get(position);
    }
    private PhotoEntry createAddEntry(){
        return new PhotoEntry();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder=new ViewHolder(mInflater.inflate(R.layout.item_selected_photo,parent,false),viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (position == list.size() - 1) {
            holder.mImageView.setImageResource(R.drawable.add);
        } else {
            final PhotoEntry entry = list.get(position);
            Glide.with(mContext)
                    .load(new File(entry.getPath()))
                    .centerCrop()
                    .placeholder(com.litao.android.lib.R.mipmap.default_image)
                    .into(holder.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView mImageView;
        private int position;
        public ViewHolder(View itemView,final int viewType) {
            super(itemView);
            this.position=viewType;
            mImageView = (ImageView) itemView.findViewById(R.id.image);
            mImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mlistener.onItemClicked(position);
        }
    }
}
