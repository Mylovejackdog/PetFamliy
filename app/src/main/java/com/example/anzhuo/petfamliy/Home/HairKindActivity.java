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
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Copulation;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Exercise;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_FoodProhibition;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Hairdressing;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Heat;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Nutrition;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/10/27.
 */
public class HairKindActivity extends Activity {
    TextView hair_title;
    ImageView hair_iv_head;
    TextView hair_tv_content;
    private  int chooseImg;
    private  int choosePosition;
    ImageView hair_iv_back;


    private  final int MA=10;
    private  final int MB=11;
    private  final int MC=12;
    private  final int MD=13;
    private  final int ME=14;
    private  final int MF=15;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MA:
                    BmobQuery <dog_Nutrition>  query=new BmobQuery<>();
                        query.findObjects(new FindListener<dog_Nutrition>() {
                            @Override
                            public void done(List<dog_Nutrition> list, BmobException e) {

                                if(e==null) {
                                    hair_title.setText(list.get(chooseImg).getTitle());
                                    Glide.with(HairKindActivity.this).load(list.get(chooseImg).getImage_head()).into(hair_iv_head);
                                    hair_tv_content.setText(list.get(chooseImg).getContent());
                                }
                            }
                        });
                    break;
                case MB:
                    BmobQuery <dog_Hairdressing>  query1=new BmobQuery<>();
                    query1.findObjects(new FindListener<dog_Hairdressing>() {
                        @Override
                        public void done(List<dog_Hairdressing> list, BmobException e) {
                            if(e==null) {
                                hair_title.setText(list.get(chooseImg).getTitle());
                                Glide.with(HairKindActivity.this).load(list.get(chooseImg).getImage_head()).into(hair_iv_head);
                                hair_tv_content.setText(list.get(chooseImg).getContent());
                            }
                        }
                    });

                    break;
                case MC:
                    BmobQuery<dog_Copulation> query2 = new BmobQuery<>();
                    query2.findObjects(new FindListener<dog_Copulation>() {
                        @Override
                        public void done(List<dog_Copulation> list, BmobException e) {
                            if(e==null) {
                                hair_title.setText(list.get(chooseImg).getTitle());
                                Glide.with(HairKindActivity.this).load(list.get(chooseImg).getImage_head()).into(hair_iv_head);
                                hair_tv_content.setText(list.get(chooseImg).getContent());
                            }
                        }
                    });
                    break;
                case MD:
                    BmobQuery<dog_Exercise> query3 = new BmobQuery<>();
                    query3.findObjects(new FindListener<dog_Exercise>() {
                        @Override
                        public void done(List<dog_Exercise> list, BmobException e) {
                            if(e==null) {
                                hair_title.setText(list.get(chooseImg).getTitle());
                                Glide.with(HairKindActivity.this).load(list.get(chooseImg).getImage_head()).into(hair_iv_head);
                                hair_tv_content.setText(list.get(chooseImg).getContent());
                            }
                        }
                    });
                    break;
                case ME:
                    BmobQuery<dog_FoodProhibition> query4 = new BmobQuery<>();
                    query4.findObjects(new FindListener<dog_FoodProhibition>() {
                        @Override
                        public void done(List<dog_FoodProhibition> list, BmobException e) {
                            if(e==null) {
                                hair_title.setText(list.get(chooseImg).getTitle());
                                Glide.with(HairKindActivity.this).load(list.get(chooseImg).getImage_head()).into(hair_iv_head);
                                hair_tv_content.setText(list.get(chooseImg).getContent());
                            }
                        }
                    });
                    break;
                case MF:
                    BmobQuery<dog_Heat> query5 = new BmobQuery<>();
                    query5.findObjects(new FindListener<dog_Heat>() {
                        @Override
                        public void done(List<dog_Heat> list, BmobException e) {
                            if(e==null) {
                                hair_title.setText(list.get(chooseImg).getTitle());
                                Glide.with(HairKindActivity.this).load(list.get(chooseImg).getImage_head()).into(hair_iv_head);
                                hair_tv_content.setText(list.get(chooseImg).getContent());
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
        setContentView(R.layout.hair_item);
        hair_title= (TextView) findViewById(R.id.hair_title);
        hair_iv_head= (ImageView) findViewById(R.id.hair_iv_head);
        hair_tv_content= (TextView) findViewById(R.id.hair_tv_content);
        hair_iv_back= (ImageView) findViewById(R.id.hair_iv_back);
        hair_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent=getIntent();
        chooseImg=intent.getExtras().getInt("choose");
        choosePosition=intent.getExtras().getInt("checkPosition");
        switch (choosePosition){
            case 0:
                handler.sendEmptyMessage(MA);
                break;
            case 1:
                handler.sendEmptyMessage(MB);
                break;
            case 2:
                handler.sendEmptyMessage(MC);
                break;
            case 3:
                handler.sendEmptyMessage(MD);
                break;
            case 4:
                handler.sendEmptyMessage(ME);
                break;
            case 5:
                handler.sendEmptyMessage(MF);
                break;

        }


    }
}
