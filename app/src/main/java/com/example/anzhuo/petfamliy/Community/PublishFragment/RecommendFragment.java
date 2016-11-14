package com.example.anzhuo.petfamliy.Community.PublishFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anzhuo.petfamliy.Adapter.PublishAdapter;
import com.example.anzhuo.petfamliy.AdapterInfo.PublishInfo;
import com.example.anzhuo.petfamliy.AdapterInfo.Recommend;
import com.example.anzhuo.petfamliy.Community.ContributeActivity;
import com.example.anzhuo.petfamliy.Community.ItemPhotoActivity;
import com.example.anzhuo.petfamliy.Community.ItemRecommendActivity;
import com.example.anzhuo.petfamliy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by anzhuo on 2016/11/2.
 */
public class RecommendFragment extends Fragment implements PublishAdapter.OnItmeClickListener {
    RecyclerView rv_falls_photo;
    PublishAdapter publishAdapter;
    Recommend recommend;
    List<Recommend> mlist=new ArrayList<>();
    private static final int MSG=1;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG:
                    BmobQuery<Recommend> query=new BmobQuery<>();
                    query.order("-createdAt");
                    query.findObjects(new FindListener<Recommend>() {
                        @Override
                        public void done(List<Recommend> list, BmobException e) {
                            if (e==null){
                                for (int i=0;i<list.size();i++) {
                                    recommend = new Recommend();
                                    recommend.setPhoto(list.get(i).getPhoto());
                                    recommend.setContent(list.get(i).getContent());
                                    recommend.setName(list.get(i).getName());
                                    recommend.setHead(list.get(i).getHead());
                                    mlist.add(recommend);
                                    publishAdapter.notifyDataSetChanged();
                                }

                            }else {
                                return;
                            }
                        }
                    });

                    break;
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.recommend_layout,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_falls_photo= (RecyclerView) view.findViewById(R.id.rv_falls_photo);
        Bmob.initialize(getActivity(),"8456bf8d25dc1d6b2ba651eb5756ed67");
        ((SimpleItemAnimator)rv_falls_photo.getItemAnimator()).setSupportsChangeAnimations(false);
        new Thread(){
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG);
            }
        }.start();
   //     send();
        publishAdapter=new PublishAdapter(getActivity(),mlist);
        rv_falls_photo.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rv_falls_photo.setAdapter(publishAdapter);
        rv_falls_photo.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        publishAdapter.setOnClickListener(new PublishAdapter.OnItmeClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent=new Intent(getActivity(), ItemRecommendActivity.class);
                intent.putExtra("name",mlist.get(position).getName());
                intent.putExtra("photo",mlist.get(position).getPhoto());
                intent.putExtra("content",mlist.get(position).getContent());
                intent.putExtra("head",mlist.get(position).getHead());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClicked(int position) {

    }

}
