package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Expertise;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Type;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

/**
 * Created by anzhuo on 2016/10/27.
 */
public class ExpertiseBaseAdapter extends BaseAdapter {
List<dog_Expertise> list;
    Context context;

   public ExpertiseBaseAdapter(Context context, List<dog_Expertise> list ){
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
            view= LayoutInflater.from(context).inflate(R.layout.expertise_item,null);
            viewHolder=new ViewHolder();
            viewHolder.expertise_iv_content= (ImageView) view.findViewById(R.id.expertise_iv_content);
            viewHolder.lv_expertise_name= (TextView) view.findViewById(R.id.lv_expertise_name);
            viewHolder.expertise_content= (TextView) view.findViewById(R.id.expertise_content);
            view.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) view.getTag();
        dog_Expertise  dog_expertise=list.get(i);
        Glide.with(context).load(dog_expertise.getImage_content()).into(viewHolder.expertise_iv_content);
        viewHolder.lv_expertise_name.setText(dog_expertise.getTitle());
        viewHolder.expertise_content.setText(dog_expertise.getHeader());
        return view;

    }

    class  ViewHolder{
         ImageView expertise_iv_content;
         TextView  lv_expertise_name;
         TextView  expertise_content;


    }
}
