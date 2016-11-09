package com.example.anzhuo.petfamliy.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Disease;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/10/28.
 */
public class DiseaseContentActivity extends Activity {
    ImageView disease_main_back;
    ImageView disease_iv_content;
    TextView disease_summarize;
    TextView disease_pathogenesis;
    TextView disease_symptom;
    TextView disease_identify;
    TextView disease_prevent;
    TextView disease_treat;
    TextView disease_title;


    BmobQuery<dog_Disease> query;
    private  int getDiseaseChooseItem;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    query.findObjects(new FindListener<dog_Disease>() {
                        @Override
                        public void done(List<dog_Disease> list, BmobException e) {
                            if(e==null){
                                disease_summarize.setText(list.get(getDiseaseChooseItem).getSummarize());
                                disease_pathogenesis.setText(list.get(getDiseaseChooseItem).getPathogenesis());
                                disease_symptom.setText(list.get(getDiseaseChooseItem).getSymptom());
                                disease_identify.setText(list.get(getDiseaseChooseItem).getIdentify());
                                disease_prevent.setText(list.get(getDiseaseChooseItem).getPrevent());
                                disease_treat.setText(list.get(getDiseaseChooseItem).getTreat());
                                disease_title.setText(list.get(getDiseaseChooseItem).getDiseaseName());
                                Glide.with(DiseaseContentActivity.this).load(list.get(getDiseaseChooseItem).getImage_pro()).into(disease_iv_content);
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
        setContentView(R.layout.disease_content_layout);
        disease_main_back= (ImageView) findViewById(R.id.disease_main_back);
         disease_iv_content= (ImageView) findViewById(R.id.disease_iv_content);
        disease_summarize= (TextView) findViewById(R.id.disease_summarize);
        disease_pathogenesis= (TextView) findViewById(R.id.disease_pathogenesis);
        disease_symptom= (TextView) findViewById(R.id.disease_symptom);
         disease_identify= (TextView) findViewById(R.id.disease_identify);
         disease_prevent= (TextView) findViewById(R.id.disease_prevent);
         disease_treat= (TextView) findViewById(R.id.disease_treat);
        disease_title= (TextView) findViewById(R.id.disease_title);
        Intent intent=getIntent();
        getDiseaseChooseItem=intent.getExtras().getInt("diseaseChooseItem");
        query=new BmobQuery<>();
        handler.sendEmptyMessage(1);
        disease_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
