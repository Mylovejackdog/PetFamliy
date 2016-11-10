package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.Mine.Base.User;
import com.example.anzhuo.petfamliy.R;

import cn.bmob.v3.BmobUser;

/**
 * 我的资料页面
 * Created by anzhuo on 2016/11/4.
 */

public class MyPrefileActivity extends Activity {
    ImageView kind_main_back;
    com.facebook.drawee.view.SimpleDraweeView iv_head;
    TextView  tv_name,tv_age,tv_number;
    Button  bt_redact;
    MyUser user=BmobUser.getCurrentUser(MyUser.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_activity);

        kind_main_back= (ImageView) findViewById(R.id.kind_main_back);
        iv_head= (com.facebook.drawee.view.SimpleDraweeView) findViewById(R.id.iv_head);
        tv_age= (TextView) findViewById(R.id.tv_age);
        tv_name= (TextView) findViewById(R.id.tv_name);
        tv_number= (TextView) findViewById(R.id.tv_number);
        bt_redact= (Button) findViewById(R.id.bt_redact);
        if (user != null) {
            //     Log.i("LW",userInfo.getHead().getFileUrl()+"");
            if(user.getImg_head()!=null) {
                iv_head.setImageURI(user.getImg_head().getUrl());
            }
            else {
                iv_head.setImageResource(R.mipmap.share_personal_default);
            }
            String name = (String) BmobUser.getObjectByKey("nickname");
            String age= (String) BmobUser.getObjectByKey("age");
            String number= (String) BmobUser.getObjectByKey("number");
            tv_number.setText(number);
            tv_age.setText(age);
            tv_name.setText(name);

        } else {
            tv_name.setText("游客");
            tv_age.setText(null);
            tv_number.setText(null);
            iv_head.setImageResource(R.mipmap.share_personal_default);

        }

        kind_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_redact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyPrefileActivity.this,RedactPrefileActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
