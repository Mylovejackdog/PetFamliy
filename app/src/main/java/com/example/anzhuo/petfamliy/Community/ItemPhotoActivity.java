package com.example.anzhuo.petfamliy.Community;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.Adapter.CommentBaseAdapter;
import com.example.anzhuo.petfamliy.AdapterInfo.Comment;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/11/9.
 */
public class ItemPhotoActivity extends Activity {
    ImageView iv_myHead;
    TextView tv_myName;
    ImageView iv_myPhoto;
    TextView tv_myFrom;
    ListView lv_content;
    TextView bt_send;
    EditText et_writing;

    List<Comment> list;
    CommentBaseAdapter commentBaseAdapter;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case  0:
                    break;
                case  1:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_photo_layout);
        iv_myHead= (ImageView) findViewById(R.id.iv_myHead);
        tv_myName= (TextView) findViewById(R.id.tv_myName);
        iv_myPhoto= (ImageView) findViewById(R.id.iv_myPhotos);
        tv_myFrom= (TextView) findViewById(R.id.tv_myFrom);
        lv_content= (ListView) findViewById(R.id.lv_content);
        bt_send= (TextView) findViewById(R.id.bt_send);
        et_writing= (EditText) findViewById(R.id.et_writing);


        list=new ArrayList<>();
        commentBaseAdapter=new CommentBaseAdapter(this,list);
        lv_content.setAdapter(commentBaseAdapter);
        handler.sendEmptyMessage(0);

        Intent intent=getIntent();
        String name=intent.getExtras().getString("name");
        String photo=intent.getExtras().getString("photo");
        String content=intent.getExtras().getString("content");
        Glide.with(this).load(photo).into(iv_myPhoto);
        tv_myFrom.setText(content);
        tv_myName.setText(name);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content=et_writing.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    handler.sendEmptyMessage(1);
                }else{
                    Toast.makeText(ItemPhotoActivity.this,"评论有误",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



}
