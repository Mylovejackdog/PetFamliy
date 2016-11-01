package com.example.anzhuo.petfamliy.Home.KindTextFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Type;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/10/26.
 */
public class FeedFragment extends Fragment {
    TextView feed;
    BmobQuery<dog_Type> query;
    int i;
    private  static final int MG=101;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MG:
                    query.findObjects(new FindListener<dog_Type>() {
                        @Override
                        public void done(List<dog_Type> list, BmobException e) {
                            if(e==null){
                                feed.setText(list.get(i).getFeedWay());
                            }
                        }
                    });
                    break;

            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.kind_feed_item,null);
        feed= (TextView) view.findViewById(R.id.feed);
        query=new BmobQuery<>();
        i = getArguments().getInt("click");
        handler.sendEmptyMessage(MG);
        return view;
    }
}
