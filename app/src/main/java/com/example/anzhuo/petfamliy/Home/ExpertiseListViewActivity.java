package com.example.anzhuo.petfamliy.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.anzhuo.petfamliy.Adapter.ExpertiseBaseAdapter;
import com.example.anzhuo.petfamliy.Adapter.KindBaseAdapter;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Expertise;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Type;
import com.example.anzhuo.petfamliy.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/10/28.
 */
public class ExpertiseListViewActivity  extends Activity{

    ListView lv_expertise;
    ExpertiseBaseAdapter expertiseBaseAdapter;
    List<dog_Expertise> list;
    BmobQuery<dog_Expertise> query;
    ImageView expertise_iv_back;
    private  int expertiseChooseItem;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    query.order("-createdAt");
                    query.findObjects(new FindListener<dog_Expertise>() {
                        @Override
                        public void done(List<dog_Expertise> mlist, BmobException e) {

                            if(e==null) {
                                list.addAll(mlist);
                                expertiseBaseAdapter.notifyDataSetChanged();
                            }
                        }
                    });

                    break;


            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expertise_layout);
        expertise_iv_back= (ImageView) findViewById(R.id.expertise_iv_back);
        expertise_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lv_expertise= (ListView) findViewById(R.id.lv_expertise);
        list=new ArrayList<>();
        expertiseBaseAdapter=new ExpertiseBaseAdapter(ExpertiseListViewActivity.this,list);
        lv_expertise.setAdapter(expertiseBaseAdapter);
        lv_expertise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ExpertiseListViewActivity.this,ExpertiseContentActivity.class);
                expertiseChooseItem=i;
                intent.putExtra("expertiseChooseItem",expertiseChooseItem);
                startActivity(intent);
            }
        });
        query=new BmobQuery<>();
        handler.sendEmptyMessage(0);
    }
}
