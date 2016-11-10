package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anzhuo.petfamliy.Main.MainActivity;
import com.example.anzhuo.petfamliy.Mine.Base.User;
import com.example.anzhuo.petfamliy.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 登入页面
 * Created by anzhuo on 2016/11/3.
 */

public class LoginActivity extends Activity {
    ImageView kind_main_back;
    EditText et_user,et_pwd;
    TextView new_user_register,forget_key,mine_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        kind_main_back= (ImageView) findViewById(R.id.kind_main_back);
        et_user= (EditText) findViewById(R.id.et_user);
        et_pwd= (EditText) findViewById(R.id.et_pwd);
        mine_login= (TextView) findViewById(R.id.mine_login);
        new_user_register= (TextView) findViewById(R.id.new_user_register);
        forget_key= (TextView) findViewById(R.id.forget_key);

        kind_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mine_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                user.setUsername(et_user.getText().toString());
                user.setPassword(et_pwd.getText().toString());
                user.login(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e==null){
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            Intent intent1=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent1);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this,"登录失败，请重新登录",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        new_user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent4);
                finish();
            }
        });
        forget_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5=new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent5);
                finish();
            }
        });

    }

}
