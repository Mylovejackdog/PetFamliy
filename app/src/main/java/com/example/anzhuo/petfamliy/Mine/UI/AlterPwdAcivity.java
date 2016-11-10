package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.Mine.Base.User;
import com.example.anzhuo.petfamliy.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 修改密码
 * Created by anzhuo on 2016/11/4.
 */

public class AlterPwdAcivity extends Activity {
    ImageView kind_main_back;
    com.facebook.drawee.view.SimpleDraweeView iv_head;
    EditText  old_pwd,new_pwd;
    Button   bt_keep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alter_pwd_layout);

        kind_main_back= (ImageView) findViewById(R.id.kind_main_back);
        iv_head= (com.facebook.drawee.view.SimpleDraweeView) findViewById(R.id.iv_head);
        old_pwd= (EditText) findViewById(R.id.old_pwd);
        new_pwd= (EditText) findViewById(R.id.new_pwd);
        bt_keep= (Button) findViewById(R.id.bt_keep);

        MyUser user=BmobUser.getCurrentUser(MyUser.class);
        if(user!=null){
            iv_head.setImageURI(user.getImg_head().getUrl());
        }else {
            iv_head.setImageResource(R.mipmap.share_personal_default);
        }
        //返回我的页面
       kind_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //修改密码
        bt_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.updateCurrentUserPassword(old_pwd.getText().toString(), new_pwd.getText().toString(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null){
                            Toast.makeText(AlterPwdAcivity.this,"修改成功"+"新密码："+new_pwd.getText().toString(),Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(AlterPwdAcivity.this,MineSettingActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });


    }
}
