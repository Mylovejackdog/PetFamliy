package com.example.anzhuo.petfamliy.Community.PublishFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.anzhuo.petfamliy.Adapter.PhotoAdapter;
import com.example.anzhuo.petfamliy.AdapterInfo.PhotoInfo;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/**
 * Created by anzhuo on 2016/11/2.
 */
public class PhotoFragment extends Fragment {
    ListView lv_contribute;
    List<Post> list;
    PhotoAdapter photoAdapter;
    private static final int MSG=2;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MSG:
                    BmobQuery<Post> query=new BmobQuery<>();
                    query.order("-createdAt");
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
        View view=inflater.inflate(R.layout.contribute_photo_layout,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bmob.initialize(getActivity(),"8456bf8d25dc1d6b2ba651eb5756ed67");
        lv_contribute= (ListView) view.findViewById(R.id.lv_contribute);
        list=new ArrayList<>();
        new Thread(){
            @Override
            public void run() {
                handler.sendEmptyMessage(MSG);
            }
        }.start();
        photoAdapter=new PhotoAdapter(list,getActivity());
        lv_contribute.setAdapter(photoAdapter);
    }
}
