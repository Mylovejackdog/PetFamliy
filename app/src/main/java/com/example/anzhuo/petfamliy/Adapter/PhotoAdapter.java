package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.AdapterInfo.PhotoInfo;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

/**
 * Created by anzhuo on 2016/11/9.
 */
public class PhotoAdapter extends BaseAdapter {
    List<Post> mlist;
    Context mcontext;
    public PhotoAdapter(List<Post> list,Context context){
        this.mcontext=context;
        this.mlist=list;
    }
    @Override
    public int getCount() {
        return mlist==null?0:mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null){
            view= LayoutInflater.from(mcontext).inflate(R.layout.new_photo_layout,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_new_head= (ImageView) view.findViewById(R.id.iv_new_head);
            viewHolder.iv_new_photo= (ImageView) view.findViewById(R.id.iv_new_photo);
            viewHolder.tv_new_name= (TextView) view.findViewById(R.id.tv_new_name);
            viewHolder.tv_new_time= (TextView) view.findViewById(R.id.tv_new_time);
            viewHolder.tv_new_content= (TextView) view.findViewById(R.id.tv_new_content);
            viewHolder.tv_praise_num= (TextView) view.findViewById(R.id.tv_praise_num);
            viewHolder.tv_comment_num= (TextView) view.findViewById(R.id.tv_comment_num);
            viewHolder.tv_transmit_num= (TextView) view.findViewById(R.id.tv_transmit_num);
            viewHolder.ll_praise= (LinearLayout) view.findViewById(R.id.ll_praise);
            viewHolder.ll_comment= (LinearLayout) view.findViewById(R.id.ll_comment);
            viewHolder.ll_transmit= (LinearLayout) view.findViewById(R.id.ll_transmit);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        Post post=mlist.get(i);
         Glide.with(mcontext).load(post.getAuthor().getImg_head()).into(viewHolder.iv_new_head);
         viewHolder.tv_new_name.setText(post.getAuthor().getNickName());
         viewHolder.tv_new_time.setText(post.getCreatedAt());
         Glide.with(mcontext).load(post.getImage().getUrl()).into(viewHolder.iv_new_photo);
         viewHolder.tv_new_content.setText(post.getContent());

        return view;
    }
    class ViewHolder{
        ImageView iv_new_head;
        ImageView iv_new_photo;
        TextView tv_new_name;
        TextView tv_new_time;
        TextView tv_new_content;
        TextView tv_praise_num;
        TextView tv_comment_num;
        TextView tv_transmit_num;
        LinearLayout ll_praise;
        LinearLayout ll_comment;
        LinearLayout ll_transmit;
    }
}
