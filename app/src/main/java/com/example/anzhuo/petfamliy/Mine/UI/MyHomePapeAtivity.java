package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.anzhuo.petfamliy.Adapter.MyHomePostBaseAdapter;
import com.example.anzhuo.petfamliy.AdapterInfo.Comment;
import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.Community.PostCommentActivity;
import com.example.anzhuo.petfamliy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * 我的主页
 * Created by anzhuo on 2016/11/3.
 */

public class MyHomePapeAtivity extends Activity {
    ImageView king_main_back;

    ListView lv_pape;
    List<Post> list;
    MyHomePostBaseAdapter myHomePostBaseAdapter;
    MyUser user = BmobUser.getCurrentUser(MyUser.class);

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    BmobQuery<Post> query = new BmobQuery<>();
                    query.order("-createdAt");
                    query.include("author");
                    query.addWhereEqualTo("author", new BmobPointer(user));
                    query.findObjects(new FindListener<Post>() {
                        @Override
                        public void done(List<Post> mlist, BmobException e) {
                            list.clear();
                            list.addAll(mlist);
                            myHomePostBaseAdapter.notifyDataSetChanged();
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_home_pape_activity);

        king_main_back = (ImageView) findViewById(R.id.kind_main_back);
        lv_pape = (ListView) findViewById(R.id.lv_pape);
        list = new ArrayList<>();
        myHomePostBaseAdapter = new MyHomePostBaseAdapter(this, list);
        lv_pape.setAdapter(myHomePostBaseAdapter);
        king_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        handler.sendEmptyMessage(0);

        lv_pape.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MyHomePapeAtivity.this, PostCommentActivity.class);
                intent.putExtra("data", list.get(i));
                Log.i("info", list.get(i).getAuthor().getNickName() + "");
                startActivity(intent);
            }
        });

        lv_pape.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                 AlertDialog.Builder builder=new AlertDialog.Builder(MyHomePapeAtivity.this);
                builder.setTitle("删除动态");
                builder.setMessage("是否删除此条动态");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,  int which) {
                        dialogInterface.dismiss();
                         Post post=new Post();
                         post.setObjectId(list.get(i).getObjectId());
                         post.delete(new UpdateListener() {
                             @Override
                             public void done(BmobException e) {
                                 if(e==null){
                                     Toast.makeText(MyHomePapeAtivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                    handler.sendEmptyMessage(0);
                                     Log.i("info","删除成功");
                                 }else {
                                     Log.i("info",e+"");
                                 }
                             }
                         });
                       BmobQuery<Comment> query=new BmobQuery<Comment>();
                        query.include("post");
                        query.addWhereEqualTo("post",new BmobPointer(post));
                        query.findObjects(new FindListener<Comment>() {
                            @Override
                            public void done(List<Comment> alist, BmobException e) {
                                Comment comment=new Comment();
                               for (Comment comment1:alist ){
                                   comment.setObjectId(comment1.getObjectId());
                                   comment.delete(new UpdateListener() {
                                       @Override
                                       public void done(BmobException e) {
                                           if(e==null){
                                               Log.i("comment","删除相关评论成功");
                                           }
                                       }
                                   });
                               }
                            }
                        });


                    }
                });
                builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.create().show();
                return true;
            }
        });
    }
}
