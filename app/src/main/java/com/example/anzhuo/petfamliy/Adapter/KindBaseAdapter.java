package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Type;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

/**
 * Created by anzhuo on 2016/10/27.
 */
public class KindBaseAdapter extends BaseAdapter {
List<dog_Type> list;
    Context context;

   public KindBaseAdapter(Context context,List<dog_Type> list ){
             this.context=context;
             this.list=list;
   }

    @Override
    public int getCount() {
        return list==null?0:list.size();
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
            ViewHolder viewHolder=null;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.kind_item,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_content= (ImageView) view.findViewById(R.id.iv_content);
            viewHolder.lv_dog_name= (TextView) view.findViewById(R.id.lv_dog_name);
            viewHolder.dog_content= (TextView) view.findViewById(R.id.dog_content);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        dog_Type  dog_type=list.get(i);
        Glide.with(context).load(dog_type.getImage_head()).into(viewHolder.iv_content);
        viewHolder.lv_dog_name.setText(dog_type.getName());
        viewHolder.dog_content.setText(dog_type.getIntroduction());
        return view;

    }

    class  ViewHolder{
         ImageView iv_content;
         TextView  lv_dog_name;
         TextView  dog_content;


    }
}
