package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anzhuo.petfamliy.AdapterInfo.EncyclopediaInfo;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

/**
 * Created by anzhuo on 2016/10/27.
 */
public class EncyclopediaBaseAdapter extends BaseAdapter {
    List<EncyclopediaInfo> list;
    Context context;
    EncyclopediaInfo encyclopediaInfo;

    public EncyclopediaBaseAdapter(Context context, List<EncyclopediaInfo> list) {
        this.context = context;
        this.list = list;
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
        ViewHodler viewHodler=null;
        if(view==null){
            view= LayoutInflater.from(context).inflate(R.layout.encyclopedia_listview_item,null);
            viewHodler=new ViewHodler();
            viewHodler.encyclopedia_iv_content= (ImageView) view.findViewById(R.id.encyclopedia_iv_content);
            viewHodler.encyclopedia_title= (TextView) view.findViewById(R.id.encyclopedia_title);
            view.setTag(viewHodler);

        }
        viewHodler= (ViewHodler) view.getTag();
        encyclopediaInfo=list.get(i);
        viewHodler.encyclopedia_iv_content.setImageResource(encyclopediaInfo.getEncyclopedia_iv_content());
        viewHodler.encyclopedia_title.setText(encyclopediaInfo.getEncyclopedia_title());
        return view;
    }
    class ViewHodler{
        ImageView encyclopedia_iv_content;
        TextView encyclopedia_title;

    }

}

