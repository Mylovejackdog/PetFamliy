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

import com.example.anzhuo.petfamliy.Adapter.DiseaseBaseAdapter;
import com.example.anzhuo.petfamliy.Adapter.ExpertiseBaseAdapter;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Disease;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Expertise;
import com.example.anzhuo.petfamliy.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/10/28.
 */
public class DiseaseListViewActivity extends Activity{

    ListView lv_disease;
    DiseaseBaseAdapter diseaseBaseAdapter;
    List<dog_Disease> list;
    BmobQuery<dog_Disease> query;
    ImageView disease_iv_back;
    private  int diseaseChooseItem;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    query.findObjects(new FindListener<dog_Disease>() {
                        @Override
                        public void done(List<dog_Disease> mlist, BmobException e) {

                            if(e==null) {
                                list.addAll(mlist);
                                diseaseBaseAdapter.notifyDataSetChanged();
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
        setContentView(R.layout.disease_layout);
        disease_iv_back= (ImageView) findViewById(R.id.disease_iv_back);
        disease_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lv_disease= (ListView) findViewById(R.id.lv_disease);
        list=new ArrayList<>();
        diseaseBaseAdapter=new DiseaseBaseAdapter(DiseaseListViewActivity.this,list);
        lv_disease.setAdapter(diseaseBaseAdapter);
        lv_disease.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(DiseaseListViewActivity.this,DiseaseContentActivity.class);
                diseaseChooseItem=i;
                intent.putExtra("diseaseChooseItem",diseaseChooseItem);
                startActivity(intent);
            }
        });
        query=new BmobQuery<>();
        handler.sendEmptyMessage(0);
    }
}
