package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 编辑资料页面
 * Created by anzhuo on 2016/11/4.
 */

public class RedactPrefileActivity extends Activity {
    ImageView kind_main_back;
    com.facebook.drawee.view.SimpleDraweeView change_head;
    EditText et_name,et_sex,et_number;
    Button bt_keep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redact_profile_layout);

        kind_main_back= (ImageView) findViewById(R.id.kind_main_back);
        change_head= (com.facebook.drawee.view.SimpleDraweeView) findViewById(R.id.change_head);
        et_sex= (EditText) findViewById(R.id.et_sex);
        et_name= (EditText) findViewById(R.id.et_name);
        et_number= (EditText) findViewById(R.id.et_number);
        bt_keep= (Button) findViewById(R.id.bt_keep);
        MyUser user=BmobUser.getCurrentUser(MyUser.class);
        if (user != null) {
            //     Log.i("LW",userInfo.getHead().getFileUrl()+"");
            if(user.getImg_head()!=null) {
               change_head.setImageURI(user.getImg_head().getUrl());
                et_name.setText(user.getNickName());
                et_sex.setText(user.getSex());
                et_number.setText(user.getMobilePhoneNumber());
            }
            else {
                change_head.setImageResource(R.mipmap.share_personal_default);
                et_name.setText("没有昵称");
                et_sex.setText("还没有设置性别");
                et_number.setText("还没有设置电话号码");
            }

        } else {
            change_head.setImageResource(R.mipmap.share_personal_default);

        }


        kind_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        change_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(RedactPrefileActivity.this,MyHeadActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        //修改资料
        bt_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser user=new MyUser();
                user.setNickName(et_name.getText().toString());
                user.setSex(et_sex.getText().toString());
                user.setMobilePhoneNumber(et_number.getText().toString());
                MyUser user1= BmobUser.getCurrentUser(MyUser.class);
                user.update(user1.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null){
                            Toast.makeText(RedactPrefileActivity.this,"更新成功",Toast.LENGTH_SHORT);
                            finish();
                        }else {
                            Log.i("lw",e+"");
                        }
                    }
                });
            }
        });


    }
}
