package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Disease;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Expertise;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

/**
 * Created by anzhuo on 2016/10/27.
 */
public class DiseaseBaseAdapter extends BaseAdapter {
    List<dog_Disease> list;
    Context context;

    public DiseaseBaseAdapter(Context context, List<dog_Disease> list) {
        this.context = context;
        this.list = list;
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
            view = LayoutInflater.from(context).inflate(R.layout.disease_item, null);
            viewHolder = new ViewHolder();
            viewHolder.disease_iv_content = (ImageView) view.findViewById(R.id.disease_iv_content);
            viewHolder.lv_disease_name = (TextView) view.findViewById(R.id.lv_disease_name);
            viewHolder.disease_content = (TextView) view.findViewById(R.id.disease_content);
            view.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) view.getTag();
        dog_Disease dog_disease = list.get(i);
        Glide.with(context).load(dog_disease.getImage_pro()).into(viewHolder.disease_iv_content);
        viewHolder.lv_disease_name.setText(dog_disease.getDiseaseName());
        viewHolder.disease_content.setText(dog_disease.getSummarize());
        return view;

    }

    class ViewHolder {
        ImageView disease_iv_content;
        TextView lv_disease_name;
        TextView disease_content;

    }
}
