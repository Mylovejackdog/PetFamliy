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
import com.example.anzhuo.petfamliy.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * 编辑资料页面
 * Created by anzhuo on 2016/11/4.
 */

public class RedactPrefileActivity extends Activity {
    ImageView kind_main_back, iv_head;
    EditText et_name, et_age, et_number;
    Button bt_keep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redact_profile_layout);

        kind_main_back = (ImageView) findViewById(R.id.kind_main_back);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        et_age = (EditText) findViewById(R.id.et_age);
        et_name = (EditText) findViewById(R.id.et_name);
        et_number = (EditText) findViewById(R.id.et_number);
        bt_keep = (Button) findViewById(R.id.bt_keep);


        kind_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RedactPrefileActivity.this, MyPrefileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(RedactPrefileActivity.this, MyHeadActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        //修改资料
        bt_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser user = new MyUser();
                user.setNickName(et_name.getText().toString());
                MyUser user1 = BmobUser.getCurrentUser(MyUser.class);
                user.update(user1.getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(RedactPrefileActivity.this, "更新成功", Toast.LENGTH_SHORT);
                            Intent intent = new Intent(RedactPrefileActivity.this, MyPrefileActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });


    }
}
