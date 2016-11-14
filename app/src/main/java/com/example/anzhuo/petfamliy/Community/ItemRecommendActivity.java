package com.example.anzhuo.petfamliy.Community;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.AdapterInfo.MyLoveInfo;
import com.example.anzhuo.petfamliy.R;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by anzhuo on 2016/11/10.
 */

public class ItemRecommendActivity extends Activity {
    com.facebook.drawee.view.SimpleDraweeView recommend_head;
    TextView recommend_name;
    ImageView recommend_photo;
    TextView recommend_content;
    ImageView recommend_collect;
    MyLoveInfo myLoveInfo;
    String name;
    String photo;
    String content;
    String head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_recommend_layout);
        recommend_head= (SimpleDraweeView) findViewById(R.id.recommend_head);
        recommend_name= (TextView) findViewById(R.id.recommend_name);
        recommend_photo= (ImageView) findViewById(R.id.recommend_photo);
        recommend_content= (TextView) findViewById(R.id.recommend_content);
        recommend_collect= (ImageView) findViewById(R.id.recommend_collect);
        Intent intent=getIntent();
        name=intent.getExtras().getString("name");
        photo=intent.getExtras().getString("photo");
        content=intent.getExtras().getString("content");
        head=intent.getExtras().getString("head");
        recommend_head.setImageURI(head);
        Glide.with(this).load(photo).into(recommend_photo);
        recommend_name.setText(name);
        recommend_content.setText(content);
        recommend_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myLoveInfo=new MyLoveInfo();
                myLoveInfo.setMyLoveHead(head);
                myLoveInfo.setMyLoveName(name);
                myLoveInfo.setMyLovePhoto(photo);
                myLoveInfo.setMyLoveContent(content);
                myLoveInfo.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null){
                            Toast.makeText(ItemRecommendActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(ItemRecommendActivity.this,"收藏失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
