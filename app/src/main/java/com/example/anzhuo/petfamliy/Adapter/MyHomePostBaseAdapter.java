package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

/**
 * Created by anzhuo on 2016/11/9.
 */

public class MyHomePostBaseAdapter  extends BaseAdapter{
    List<Post> list;
    Context context;

    public  MyHomePostBaseAdapter(Context context,List<Post> list){
        this.context=context;
        this.list=list;
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.mine_home_post_item,null);
            viewHolder=new ViewHolder();
            viewHolder.post_title= (TextView) view.findViewById(R.id.post_title);
            viewHolder.post_content= (ImageView) view.findViewById(R.id.post_content);
            view.setTag(viewHolder);
        }
         viewHolder= (ViewHolder) view.getTag();
         Post tiezi=list.get(i);
         viewHolder.post_title.setText(tiezi.getContent());
        Glide.with(context).load(tiezi.getImage().getUrl()).into(viewHolder.post_content);
        return view;
    }

    class  ViewHolder{
        TextView post_title;
        ImageView post_content;

    }
}
