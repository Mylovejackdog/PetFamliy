package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.anzhuo.petfamliy.Adapter.MyloveBaseAdapter;
import com.example.anzhuo.petfamliy.AdapterInfo.Comment;
import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.Community.PostCommentActivity;
import com.example.anzhuo.petfamliy.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * 我喜欢的
 * Created by anzhuo on 2016/11/4.
 */

public class MyLoveActivity extends Activity {
    ImageView iv_back;
    List<Pair<Comment,Post>> list;
    MyloveBaseAdapter myloveBaseAdapter;
    MyUser user= BmobUser.getCurrentUser(MyUser.class);
    ListView lv_mylove;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    BmobQuery<Comment> query = new BmobQuery<>();
                    query.order("-createdAt");
                    query.include("post,post.author");
                    query.addWhereEqualTo("user", new BmobPointer(user));
                    query.findObjects(new FindListener<Comment>() {
                        @Override
                        public void done(List<Comment> mlist, BmobException e) {
                            list.clear();
                            for(Comment comment: mlist){
                                list.add(new Pair<Comment,Post>(comment,comment.getPost()));
                     //           Log.i("comment",comment.getContent()+"  "+comment.getPost().getContent()+"  "+comment.getPost().getAuthor().getNickName());
                            }
                            myloveBaseAdapter.notifyDataSetChanged();
                        }
                    });
                    break;


            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_love_layout);
        iv_back= (ImageView) findViewById(R.id.kind_main_back);
        lv_mylove= (ListView) findViewById(R.id.lv_mylove);
        list=new ArrayList<>();
       myloveBaseAdapter=new MyloveBaseAdapter(this,list);
        lv_mylove.setAdapter(myloveBaseAdapter);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
lv_mylove.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(MyLoveActivity.this,PostCommentActivity.class);
        intent.putExtra("data", list.get(i).second);
    //    Log.i("info", list.get(i).second+"");
        startActivity(intent);
    }
});
        handler.sendEmptyMessage(0);

    }
}
