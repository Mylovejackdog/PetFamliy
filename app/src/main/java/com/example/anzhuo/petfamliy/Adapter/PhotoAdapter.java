package com.example.anzhuo.petfamliy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.AdapterInfo.Comment;
import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.Community.PostCommentActivity;
import com.example.anzhuo.petfamliy.Home.ShowBigPicActivity;
import com.example.anzhuo.petfamliy.Mine.UI.LoginActivity;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

import static android.R.id.list;

/**
 * Created by anzhuo on 2016/11/9.
 */
public class PhotoAdapter extends BaseAdapter {
    List<Post> mlist;
    Context mcontext;
    Post tiezi;
    boolean isClick;
    MyUser user= BmobUser.getCurrentUser(MyUser.class);

    public PhotoAdapter(List<Post> list, Context context) {
        this.mcontext = context;
        this.mlist = list;
    }

    @Override
    public int getCount() {
        return mlist == null ? 0 : mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
         ViewHolder viewHolder ;
        if (view == null) {
            view = LayoutInflater.from(mcontext).inflate(R.layout.new_photo_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_new_head = (com.facebook.drawee.view.SimpleDraweeView) view.findViewById(R.id.iv_new_head);
            viewHolder.iv_new_photo = (ImageView) view.findViewById(R.id.iv_new_photo);
            viewHolder.tv_new_name = (TextView) view.findViewById(R.id.tv_new_name);
            viewHolder.tv_new_content = (TextView) view.findViewById(R.id.tv_new_content);
            viewHolder.post_time= (TextView) view.findViewById(R.id.post_time);
            viewHolder.post_comment= (ImageView) view.findViewById(R.id.post_comment);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        tiezi = mlist.get(i);
        Log.i("info",tiezi.getAuthor().getImg_head().getUrl());
        viewHolder.iv_new_head.setImageURI(tiezi.getAuthor().getImg_head().getUrl());
        viewHolder.tv_new_name.setText(tiezi.getAuthor().getNickName());
        Glide.with(mcontext).load(tiezi.getImage().getUrl()).into(viewHolder.iv_new_photo);
        viewHolder.tv_new_content.setText(tiezi.getContent());
         viewHolder.post_time.setText(tiezi.getCreatedAt());
        viewHolder.post_comment.setImageResource(R.mipmap.comment);
        viewHolder.post_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user!=null) {
                    Intent intent = new Intent(mcontext, PostCommentActivity.class);
                    intent.putExtra("data",mlist.get(i));
                    mcontext.startActivity(intent);
                }else {
                    Intent intent = new Intent(mcontext, LoginActivity.class);
                    mcontext.startActivity(intent);
                }
            }
        });


        viewHolder.iv_new_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user!=null) {
                    Intent intent = new Intent(mcontext, ShowBigPicActivity.class);
                    intent.putExtra("photosrc",mlist.get(i).getImage().getUrl());
                    mcontext.startActivity(intent);
                }else {
                    Intent intent = new Intent(mcontext, LoginActivity.class);
                    mcontext.startActivity(intent);
                }
            }
        });

//        BmobQuery<Comment> query = new BmobQuery<>();
//        Post post = new Post();
//        post.setObjectId(tiezi.getObjectId());
//        Log.i("hehe",tiezi.getObjectId());
//        query.addWhereEqualTo("post", new BmobPointer(post));
//        query.count(Comment.class, new CountListener() {
//            @Override
//            public void done(Integer integer, BmobException e) {
//                if (e == null) {
//                    viewHolder.tv_comment_num.setText(integer+"");
//                    Log.i("hehe",integer+"");
//                }else {
//                    Log.i("hehe",e+"");
//                }
//            }
//        });

//        viewHolder.tv_praise_num.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(!isClick&&user!=null){
//                    isClick=true;
//                    viewHolder.iv_good.setImageResource(R.drawable.good);
//                    BmobQuery<MyUser> query = new BmobQuery<MyUser>();
//                    Post post1=new Post();
//                    post1.setObjectId(tiezi.getObjectId());
//                    //likes是Post表中的字段，用来存储所有喜欢该帖子的用户
//                    query.addWhereRelatedTo("likes", new BmobPointer(post1));
//                    query.findObjects(new FindListener<MyUser>() {
//
//                        @Override
//                        public void done(List<MyUser> object,BmobException e) {
//                            if(e==null){
//                                viewHolder.tv_praise_num.setText(object.size()+1);
//                            }else{
//                                Log.i("bmob","失败："+e.getMessage());
//                            }
//                        }
//
//                    });
//                    BmobRelation relation=new BmobRelation();
//                    relation.add(post1);
//                    post1.setLikes(relation);
//                    post1.update(new UpdateListener() {
//                        @Override
//                        public void done(BmobException e) {
//                            if(e==null){
//                                Log.i("info","添加喜欢成功");
//                            }
//                        }
//                    });
//                }else {
//                    isClick=false;
//                    viewHolder.iv_good.setImageResource(R.drawable.page_icon_unlike);
//                    Post post = new Post();
//                    post.setObjectId(tiezi.getObjectId());
//                    BmobRelation relation = new BmobRelation();
//                    relation.remove(user);
//                    post.setLikes(relation);
//                    post.update(new UpdateListener() {
//                        @Override
//                        public void done(BmobException e) {
//                            if(e==null){
//                                Log.i("bmob","关联关系删除成功");
//                            }else{
//                                Log.i("bmob","失败："+e.getMessage());
//                            }
//                        }
//
//                    });
//                    BmobQuery<MyUser> query = new BmobQuery<MyUser>();
//                    Post post1=new Post();
//                    post1.setObjectId(tiezi.getObjectId());
//                    //likes是Post表中的字段，用来存储所有喜欢该帖子的用户
//                    query.addWhereRelatedTo("likes", new BmobPointer(post1));
//                    query.findObjects(new FindListener<MyUser>() {
//
//                        @Override
//                        public void done(List<MyUser> object,BmobException e) {
//                            if(e==null){
//                                viewHolder.tv_praise_num.setText(object.size()-1);
//                            }else{
//                                Log.i("bmob","失败："+e.getMessage());
//                            }
//                        }
//
//                    });
//                }
//            }
//        });
        return view;
    }

    class ViewHolder {
        com.facebook.drawee.view.SimpleDraweeView iv_new_head;
        ImageView iv_new_photo;
        TextView tv_new_name;
        TextView tv_new_content;
        TextView post_time;
        ImageView post_comment;
    }
}
