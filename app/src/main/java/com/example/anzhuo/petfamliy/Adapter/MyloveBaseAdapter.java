package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.AdapterInfo.Comment;
import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/11/10.
 */

public class MyloveBaseAdapter extends BaseAdapter {
    List<Pair<Comment, Post>> list;
    Context context;
    MyUser user = BmobUser.getCurrentUser(MyUser.class);

    public MyloveBaseAdapter(Context context, List<Pair<Comment, Post>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.mine_love_post_item, null);
            viewHolder = new ViewHolder();
            viewHolder.post_title = (TextView) view.findViewById(R.id.post_title);
            viewHolder.post_content = (ImageView) view.findViewById(R.id.post_content);
            viewHolder.comment_content = (TextView) view.findViewById(R.id.comment_content);
            viewHolder.post_user_name = (TextView) view.findViewById(R.id.post_user_name);
            viewHolder.myself_name = (TextView) view.findViewById(R.id.myself_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        final Pair<Comment, Post> tiezi = list.get(i);
        viewHolder.post_title.setText(tiezi.second.getContent());
        Glide.with(context).load(tiezi.second.getImage().getUrl()).into(viewHolder.post_content);
        viewHolder.comment_content.setText(tiezi.first.getContent());
        viewHolder.post_user_name.setText(tiezi.second.getAuthor().getNickName()+"  :");
        viewHolder.myself_name.setText(user.getNickName()+"  :");
        return view;
    }

    class ViewHolder {
        TextView post_title;
        ImageView post_content;
        TextView comment_content;
        TextView post_user_name;
        TextView myself_name;

    }
}
