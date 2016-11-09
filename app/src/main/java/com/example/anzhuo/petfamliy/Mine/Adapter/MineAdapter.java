package com.example.anzhuo.petfamliy.Mine.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.Adapter.DiseaseBaseAdapter;
import com.example.anzhuo.petfamliy.Mine.AdapterInfo.MineInfo;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

/**
 * Created by anzhuo on 2016/11/9.
 */

public class MineAdapter extends BaseAdapter {
    List<MineInfo> mlist;
    Context mcontext;

    public MineAdapter(Context context,List<MineInfo> list){
        this.mcontext=context;
        this.mlist=list;
    }

    @Override
    public int getCount() {
        return mlist==null?0:mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.my_home_pape_item,null);
            viewHolder=new ViewHolder();
            viewHolder.community_user_head= (ImageView) convertView.findViewById(R.id.community_user_head);
            viewHolder.community_user_name= (TextView) convertView.findViewById(R.id.community_user_name);
            viewHolder.community_user_time= (TextView) convertView.findViewById(R.id.community_user_time);
            viewHolder.community_user_content= (TextView) convertView.findViewById(R.id.community_user_content);
            viewHolder.community_user_image_content= (ImageView) convertView.findViewById(R.id.community_user_image_content);
            viewHolder.iv_gond= (ImageView) convertView.findViewById(R.id.iv_good);
            viewHolder.tv_good= (TextView) convertView.findViewById(R.id.tv_good);
            viewHolder.iv_bad= (ImageView) convertView.findViewById(R.id.iv_bad);
            viewHolder.tv_bad= (TextView) convertView.findViewById(R.id.tv_bad);
            viewHolder.iv_comment= (ImageView) convertView.findViewById(R.id.iv_comment);
            viewHolder.tv_comment= (TextView) convertView.findViewById(R.id.tv_comment);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        MineInfo mineInfo=mlist.get(position);
        Glide.with(mcontext).load(mineInfo.getImage().getUrl()).into(viewHolder.community_user_head);
        viewHolder.community_user_name.setText(mineInfo.getTitle());
        viewHolder.community_user_time.setText(mineInfo.getTime());
        viewHolder.community_user_content.setText(mineInfo.getContent());
        Glide.with(mcontext).load(mineInfo.getImage().getUrl()).into(viewHolder.community_user_image_content);

        return null;
    }
    class ViewHolder{
        ImageView community_user_head;//用户头像
        TextView  community_user_name;//用户昵称
        TextView  community_user_time;//发布时间
        TextView  community_user_content;//发布内容
        ImageView community_user_image_content;//发布图片
        ImageView iv_gond;
        TextView  tv_good;
        ImageView iv_bad;
        TextView  tv_bad;
        ImageView iv_comment;
        TextView  tv_comment;
    }
}
