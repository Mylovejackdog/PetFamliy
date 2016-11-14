package com.example.anzhuo.petfamliy.Community;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anzhuo.petfamliy.Adapter.PhotoAdapter;
import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.AdapterInfo.PhotoInfo;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.Community.PostCommentActivity;
import com.example.anzhuo.petfamliy.Fresh.Ptr_refresh;
import com.example.anzhuo.petfamliy.Home.KindListViewActivity;
import com.example.anzhuo.petfamliy.Mine.UI.LoginActivity;
import com.example.anzhuo.petfamliy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by anzhuo on 2016/11/2.
 */
public class CommunityFragment extends Fragment {
    ListView lv_contribute;
    List<Post> list;
    PhotoAdapter photoAdapter;
    private static final int MSG = 2;
    Ptr_refresh refresh;
    ImageView community_write;
    in.srain.cube.views.ptr.PtrFrameLayout iv_ptr;

    MyUser user= MyUser.getCurrentUser(MyUser.class);
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG:
                    BmobQuery<Post> query = new BmobQuery<>();
                    query.order("-createdAt");
                    query.include("author");
                    query.findObjects(new FindListener<Post>() {
                        @Override
                        public void done(List<Post> klist, BmobException e) {
                            list.clear();
                            list.addAll(klist);
                            photoAdapter.notifyDataSetChanged();

                        }
                    });


                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contribute_photo_layout, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_contribute = (ListView) view.findViewById(R.id.lv_contribute);
        iv_ptr= (PtrFrameLayout) view.findViewById(R.id.iv_ptrFrame);
        community_write= (ImageView) view.findViewById(R.id.community_write);

        refresh=new Ptr_refresh(getActivity());
        list = new ArrayList<>();
        new Thread() {
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG);
            }
        }.start();
        photoAdapter = new PhotoAdapter(list, getActivity());
        lv_contribute.setAdapter(photoAdapter);
//        lv_contribute.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (user!=null) {
//                    Intent intent = new Intent(getActivity(), PostCommentActivity.class);
//                    intent.putExtra("data", list.get(i));
//                    startActivity(intent);
//                }else {
//                    Intent intent = new Intent(getActivity(), LoginActivity.class);
//                    startActivity(intent);
//                }
//            }
//        });
        community_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null){
                    Intent intent=new Intent(getActivity(),ContributeActivity.class);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        iv_ptr.setHeaderView(refresh);
        iv_ptr.addPtrUIHandler(refresh);
        iv_ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                iv_ptr.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        BmobQuery<Post> query = new BmobQuery<>();
                        query.order("-createdAt");
                        query.include("author");
                        query.findObjects(new FindListener<Post>() {
                            @Override
                            public void done(List<Post> klist, BmobException e) {
                                list.clear();
                                list.addAll(klist);
                                photoAdapter.notifyDataSetChanged();
                                iv_ptr.refreshComplete();
                            }
                        });

                    }
                }, 2000);
            }
        });

    }
}
