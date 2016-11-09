package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.AdapterInfo.Comment;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

/**
 * Created by anzhuo on 2016/11/9.
 */
public class CommentBaseAdapter extends BaseAdapter {
    List<Comment> list;
    Context context;

    public CommentBaseAdapter(Context context, List<Comment> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.comment_item, null);
            viewHolder = new ViewHolder();
            viewHolder.comment_head = (ImageView) view.findViewById(R.id.comment_head);
            viewHolder.comment_name = (TextView) view.findViewById(R.id.comment_name);
            viewHolder.comment_time = (TextView) view.findViewById(R.id.comment_time);
            viewHolder.comment_content = (TextView) view.findViewById(R.id.comment_content);
            view.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) view.getTag();
        Comment comment = list.get(i);
        Glide.with(context).load(comment.getUser().getImg_head()).into(viewHolder.comment_head);
        viewHolder.comment_name.setText(comment.getUser().getNickName());
        viewHolder.comment_time.setText(comment.getCreatedAt());
        viewHolder.comment_content.setText(comment.getContent());
        return view;
    }

    class ViewHolder {
        ImageView comment_head;
        TextView comment_name;
        TextView comment_time;
        TextView comment_content;

    }

}
