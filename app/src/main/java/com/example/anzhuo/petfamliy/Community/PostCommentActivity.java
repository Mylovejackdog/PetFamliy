package com.example.anzhuo.petfamliy.Community;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.Adapter.CommentBaseAdapter;
import com.example.anzhuo.petfamliy.AdapterInfo.Comment;
import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.Fresh.ListViewForScrollView;
import com.example.anzhuo.petfamliy.Mine.UI.MyHomePapeAtivity;
import com.example.anzhuo.petfamliy.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by anzhuo on 2016/11/9.
 */
public class PostCommentActivity extends Activity{
    com.facebook.drawee.view.SimpleDraweeView iv_myHead;
    TextView tv_myName;
    ImageView iv_myPhoto;
    TextView tv_myFrom;
    ListView lv_content;
    TextView bt_send;
    EditText et_writing;
    String content;
    MyUser bmobUser = BmobUser.getCurrentUser(MyUser.class);
    List<Comment> list;
    CommentBaseAdapter commentBaseAdapter;
    Post data;
    String postid;
    int firstItem;
    RelativeLayout rl_content;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    updateComment();
                    break;
                case 1:

                    Post post2 = new Post();
                    post2.setObjectId(data.getObjectId());
                    Comment comment = new Comment();
                    comment.setUser(bmobUser);
                    comment.setPost(post2);
                    comment.setContent(content);
                    comment.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Toast.makeText(PostCommentActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                                updateComment();
                            } else {
                                Toast.makeText(PostCommentActivity.this, "评论失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_photo_layout);
        iv_myHead = (com.facebook.drawee.view.SimpleDraweeView) findViewById(R.id.iv_myHead);
        tv_myName = (TextView) findViewById(R.id.tv_myName);
        iv_myPhoto = (ImageView) findViewById(R.id.iv_myPhotos);
        tv_myFrom = (TextView) findViewById(R.id.tv_myFrom);
        lv_content = (ListView) findViewById(R.id.lv_content);
        bt_send = (TextView) findViewById(R.id.bt_send);
        et_writing = (EditText) findViewById(R.id.et_writing);
        rl_content= (RelativeLayout) findViewById(R.id.rl_content);

        list = new ArrayList<>();
        commentBaseAdapter = new CommentBaseAdapter(this, list);
        lv_content.setAdapter(commentBaseAdapter);
        handler.sendEmptyMessage(0);
        data = (Post) getIntent().getSerializableExtra("data");
        iv_myHead.setImageURI(data.getAuthor().getImg_head().getUrl());
        tv_myName.setText(data.getAuthor().getNickName());
        Glide.with(PostCommentActivity.this).load(data.getImage().getUrl()).into(iv_myPhoto);
        tv_myFrom.setText(data.getContent());
        lv_content.setVerticalScrollBarEnabled(false);
        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = et_writing.getText().toString().trim();
                if (!TextUtils.isEmpty(content)) {
                    handler.sendEmptyMessage(1);
                } else {
                    Toast.makeText(PostCommentActivity.this, "评论有误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lv_content.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                AlertDialog.Builder builder=new AlertDialog.Builder(PostCommentActivity.this);
                builder.setTitle("删除动态");
                builder.setMessage("是否删除此条评论");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface,  int which) {
                        dialogInterface.dismiss();
                        Comment comment = new Comment();
                        comment.setObjectId(list.get(i).getObjectId());
                        if (bmobUser.getObjectId().equals(list.get(i).getUser().getObjectId())) {

                            comment.delete(new UpdateListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(PostCommentActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                        handler.sendEmptyMessage(0);
                                        Log.i("info", "删除成功");
                                    } else {
                                        Log.i("info", e + "");
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(PostCommentActivity.this, "你没有权限", Toast.LENGTH_SHORT).show();
                        }

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

    protected void updateComment() {
        BmobQuery<Comment> query = new BmobQuery<>();
        query.order("-createdAt");
        Post post = new Post();
        post.setObjectId(data.getObjectId());
        query.addWhereEqualTo("post", new BmobPointer(post));
        query.include("user,post.author");
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> mlist, BmobException e) {
                if (e == null) {
                    list.clear();
                    list.addAll(mlist);
                    commentBaseAdapter.notifyDataSetChanged();
                }
            }
        });
    }


}
