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
public class NutritionActivity extends Activity implements View.OnClickListener {
    ImageView nutrition_iv_one;
    ImageView nutrition_iv_two;
    ImageView nutrition_iv_three;
    ImageView nutrition_iv_four;

    TextView nutrition_one;
    TextView nutrition_two;
    TextView nutrition_three;
    TextView nutrition_four;

    ImageView kind_iv_back;

    private int checkPosition;
    private static final int MGA = 111;
    private static final int MGB = 112;
    private static final int MGC = 113;
    private static final int MGD = 114;
    private static final int MGE = 115;
    private static final int MGF = 116;

    private int choose_img;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MGA:
                    BmobQuery<dog_Nutrition> query = new BmobQuery<>();
                    query.findObjects(new FindListener<dog_Nutrition>() {
                        @Override
                        public void done(List<dog_Nutrition> list, BmobException e) {
                            if (e == null) {
                                nutrition_one.setText(list.get(0).getTitle());
                                Glide.with(NutritionActivity.this).load(list.get(0).getImage_head()).into(nutrition_iv_one);

                                nutrition_two.setText(list.get(1).getTitle());
                                Glide.with(NutritionActivity.this).load(list.get(1).getImage_head()).into(nutrition_iv_two);

                                nutrition_three.setText(list.get(2).getTitle());
                                Glide.with(NutritionActivity.this).load(list.get(2).getImage_head()).into(nutrition_iv_three);

                                nutrition_four.setText(list.get(3).getTitle());
                                Glide.with(NutritionActivity.this).load(list.get(3).getImage_head()).into(nutrition_iv_four);
                            }
                        }
                    });
                    break;
                case MGB:
                    BmobQuery<dog_Hairdressing> query1 = new BmobQuery<>();
                       query1.findObjects(new FindListener<dog_Hairdressing>() {
                           @Override
                           public void done(List<dog_Hairdressing> list, BmobException e) {
                               if (e==null){
                                   nutrition_one.setText(list.get(0).getTitle());
                                   Glide.with(NutritionActivity.this).load(list.get(0).getImage_head()).into(nutrition_iv_one);

                                   nutrition_two.setText(list.get(1).getTitle());
                                   Glide.with(NutritionActivity.this).load(list.get(1).getImage_head()).into(nutrition_iv_two);

                                   nutrition_three.setText(list.get(2).getTitle());
                                   Glide.with(NutritionActivity.this).load(list.get(2).getImage_head()).into(nutrition_iv_three);

                                   nutrition_four.setText(list.get(3).getTitle());
                                   Glide.with(NutritionActivity.this).load(list.get(3).getImage_head()).into(nutrition_iv_four);
                               }
                           }
                       });

                    break;
                case MGC:
                    BmobQuery<dog_Copulation> query2 = new BmobQuery<>();
                      query2.findObjects(new FindListener<dog_Copulation>() {
                          @Override
                          public void done(List<dog_Copulation> list, BmobException e) {
                              if (e==null){
                                  nutrition_one.setText(list.get(0).getTitle());
                                  Glide.with(NutritionActivity.this).load(list.get(0).getImage_head()).into(nutrition_iv_one);

                                  nutrition_two.setText(list.get(1).getTitle());
                                  Glide.with(NutritionActivity.this).load(list.get(1).getImage_head()).into(nutrition_iv_two);

                                  nutrition_three.setText(list.get(2).getTitle());
                                  Glide.with(NutritionActivity.this).load(list.get(2).getImage_head()).into(nutrition_iv_three);

                                  nutrition_four.setText(list.get(3).getTitle());
                                  Glide.with(NutritionActivity.this).load(list.get(3).getImage_head()).into(nutrition_iv_four);
                              }

                          }
                      });
                    break;
                case MGD:
                    BmobQuery<dog_Exercise> query3 = new BmobQuery<>();
                      query3.findObjects(new FindListener<dog_Exercise>() {
                          @Override
                          public void done(List<dog_Exercise> list, BmobException e) {
                              if (e==null){
                                  nutrition_one.setText(list.get(0).getTitle());
                                  Glide.with(NutritionActivity.this).load(list.get(0).getImage_head()).into(nutrition_iv_one);

                                  nutrition_two.setText(list.get(1).getTitle());
                                  Glide.with(NutritionActivity.this).load(list.get(1).getImage_head()).into(nutrition_iv_two);

                                  nutrition_three.setText(list.get(2).getTitle());
                                  Glide.with(NutritionActivity.this).load(list.get(2).getImage_head()).into(nutrition_iv_three);

                                  nutrition_four.setText(list.get(3).getTitle());
                                  Glide.with(NutritionActivity.this).load(list.get(3).getImage_head()).into(nutrition_iv_four);
                              }

                          }
                      });
                    break;
                case MGE:
                    BmobQuery<dog_FoodProhibition> query4 = new BmobQuery<>();
                       query4.findObjects(new FindListener<dog_FoodProhibition>() {
                           @Override
                           public void done(List<dog_FoodProhibition> list, BmobException e) {
                               if (e==null){
                                   nutrition_one.setText(list.get(0).getTitle());
                                   Glide.with(NutritionActivity.this).load(list.get(0).getImage_head()).into(nutrition_iv_one);

                                   nutrition_two.setText(list.get(1).getTitle());
                                   Glide.with(NutritionActivity.this).load(list.get(1).getImage_head()).into(nutrition_iv_two);

                                   nutrition_three.setText(list.get(2).getTitle());
                                   Glide.with(NutritionActivity.this).load(list.get(2).getImage_head()).into(nutrition_iv_three);

                                   nutrition_four.setText(list.get(3).getTitle());
                                   Glide.with(NutritionActivity.this).load(list.get(3).getImage_head()).into(nutrition_iv_four);
                               }

                           }
                       });
                    break;
                case MGF:
                    BmobQuery<dog_Heat> query5 = new BmobQuery<>();
                    query5.findObjects(new FindListener<dog_Heat>() {
                        @Override
                        public void done(List<dog_Heat> list, BmobException e) {
                            if (e==null){
                                nutrition_one.setText(list.get(0).getTitle());
                                Glide.with(NutritionActivity.this).load(list.get(0).getImage_head()).into(nutrition_iv_one);

                                nutrition_two.setText(list.get(1).getTitle());
                                Glide.with(NutritionActivity.this).load(list.get(1).getImage_head()).into(nutrition_iv_two);

                                nutrition_three.setText(list.get(2).getTitle());
                                Glide.with(NutritionActivity.this).load(list.get(2).getImage_head()).into(nutrition_iv_three);

                                nutrition_four.setText(list.get(3).getTitle());
                                Glide.with(NutritionActivity.this).load(list.get(3).getImage_head()).into(nutrition_iv_four);
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
        setContentView(R.layout.nutrition_layout);
        kind_iv_back= (ImageView) findViewById(R.id.kind_iv_back);
        kind_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        checkPosition = intent.getExtras().getInt("itemposition");

        switch (checkPosition) {
            case 0:
                handler.sendEmptyMessage(MGA);
                break;
            case 1:
                handler.sendEmptyMessage(MGB);
                break;
            case 2:
                handler.sendEmptyMessage(MGC);
                break;
            case 3:
                handler.sendEmptyMessage(MGD);
                break;
            case 4:
                handler.sendEmptyMessage(MGE);
                break;
            case 5:
                handler.sendEmptyMessage(MGF);
                break;

        }

        nutrition_one = (TextView) findViewById(R.id.nutriton_one);
        nutrition_two = (TextView) findViewById(R.id.nutriton_two);
        nutrition_three = (TextView) findViewById(R.id.nutriton_three);
        nutrition_four = (TextView) findViewById(R.id.nutriton_four);

        nutrition_iv_one = (ImageView) findViewById(R.id.nutriton_iv_one);
        nutrition_iv_two = (ImageView) findViewById(R.id.nutriton_iv_two);
        nutrition_iv_three = (ImageView) findViewById(R.id.nutriton_iv_three);
        nutrition_iv_four = (ImageView) findViewById(R.id.nutriton_iv_four);

        nutrition_iv_one .setOnClickListener(this);
        nutrition_iv_two .setOnClickListener(this);
        nutrition_iv_three.setOnClickListener(this);
        nutrition_iv_four .setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nutriton_iv_one:
                choose_img=0;
                Intent intent=new Intent(NutritionActivity.this,HairKindActivity.class);
                intent.putExtra("choose",choose_img);
                intent.putExtra("checkPosition",checkPosition);
                startActivity(intent);
                break;
            case R.id.nutriton_iv_two:
                choose_img=1;
                Intent intent1=new Intent(NutritionActivity.this,HairKindActivity.class);
                intent1.putExtra("choose",choose_img);
                intent1.putExtra("checkPosition",checkPosition);
                startActivity(intent1);
                break;
            case R.id.nutriton_iv_three:
                choose_img=2;
                Intent intent2=new Intent(NutritionActivity.this,HairKindActivity.class);
                intent2.putExtra("choose",choose_img);
                intent2.putExtra("checkPosition",checkPosition);
                startActivity(intent2);
                break;
            case R.id.nutriton_iv_four:
                choose_img=3;
                Intent intent3=new Intent(NutritionActivity.this,HairKindActivity.class);
                intent3.putExtra("choose",choose_img);
                intent3.putExtra("checkPosition",checkPosition);
                startActivity(intent3);
                break;
            case  R.id.kind_iv_back:
                finish();
                break;
        }
    }
}
