package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anzhuo.petfamliy.Mine.Base.User;
import com.example.anzhuo.petfamliy.R;

import cn.bmob.v3.BmobUser;

/**
 * 设置页面
 * Created by anzhuo on 2016/11/4.
 */

public class MineSettingActivity extends Activity {
    ImageView kind_main_back,iv_head;
    TextView  alter_pwd;
    Button exit_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_setting_layout);

        iv_head= (ImageView) findViewById(R.id.iv_head);
        alter_pwd= (TextView) findViewById(R.id.alter_pwd);
        kind_main_back= (ImageView) findViewById(R.id.kind_main_back);
        exit_login= (Button) findViewById(R.id.exit_login);

        kind_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        alter_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MineSettingActivity.this,AlterPwdAcivity.class);
                startActivity(intent);
                finish();
            }
        });
        //退出bmob登入
        exit_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User.logOut();

            }
        });

    }
}
