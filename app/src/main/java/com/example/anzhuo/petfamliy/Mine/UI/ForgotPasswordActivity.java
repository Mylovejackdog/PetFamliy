package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.anzhuo.petfamliy.Mine.Base.User;
import com.example.anzhuo.petfamliy.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 忘记密码去找回
 * Created by anzhuo on 2016/11/3.
 */

public class ForgotPasswordActivity extends Activity {
    ImageView kind_main_back;
    EditText  et_email;
    Button    bt_keep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_pwd_acivity);

        kind_main_back= (ImageView) findViewById(R.id.kind_main_back);
        et_email= (EditText) findViewById(R.id.et_email);
        bt_keep= (Button) findViewById(R.id.bt_keep);

        kind_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //找回密码
        bt_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User.resetPasswordByEmail(et_email.getText().toString(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null){
                            Toast.makeText(ForgotPasswordActivity.this,"请前往注册时的邮箱重置密码",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    }
                });
            }
        });
    }
}
