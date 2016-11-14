package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.AdapterInfo.MyLoveInfo;
import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by anzhuo on 2016/11/10.
 */

public class MyLovePostBaseAdapter extends BaseAdapter {
    Context context;
    List<MyLoveInfo>  list;

    public MyLovePostBaseAdapter(Context context,List<MyLoveInfo> list){
        this.context=context;
        this.list=list;
    }


    @Override
    public int getCount() {
        return list==null?0:list.size() ;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (view==null){
            view= LayoutInflater.from(context).inflate(R.layout.my_love_item,null);
            viewHolder=new ViewHolder();
            viewHolder.user_head= (SimpleDraweeView) view.findViewById(R.id.user_head);
            viewHolder.nickname= (TextView) view.findViewById(R.id.user_name);
            viewHolder.time= (TextView) view.findViewById(R.id.user_time);
            viewHolder.content= (TextView) view.findViewById(R.id.user_content);
            viewHolder.user_image_content= (ImageView) view.findViewById(R.id.user_image_content);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        MyLoveInfo love=list.get(i);
        viewHolder.user_head.setImageURI(love.getMyLoveHead());
        viewHolder.nickname.setText(love.getMyLoveName());
        viewHolder.time.setText(love.getCreatedAt());
        viewHolder.content.setText(love.getMyLoveContent());
        Glide.with(context).load(love.getMyLovePhoto()).into(viewHolder.user_image_content);

        return view;
    }

    class ViewHolder{
        com.facebook.drawee.view.SimpleDraweeView user_head;
        TextView  nickname;
        TextView  time;
        TextView  content;
        ImageView user_image_content;
    }
}
