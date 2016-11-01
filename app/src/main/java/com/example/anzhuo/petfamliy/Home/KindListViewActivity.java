package com.example.anzhuo.petfamliy.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.anzhuo.petfamliy.Adapter.KindBaseAdapter;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Type;
import com.example.anzhuo.petfamliy.Home.KindTextFragment.KindMainActivity;
import com.example.anzhuo.petfamliy.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/10/27.
 */
public class KindListViewActivity extends Activity {
    ListView lv_kind;
    KindBaseAdapter kindBaseAdapter;
    List<dog_Type> list;
    BmobQuery<dog_Type> query;
    ImageView kind_iv_back;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    query.findObjects(new FindListener<dog_Type>() {
                        @Override
                        public void done(List<dog_Type> mlist, BmobException e) {

                            if(e==null) {
                                list.addAll(mlist);
                                kindBaseAdapter.notifyDataSetChanged();
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
        setContentView(R.layout.kind_listview_layout);
        lv_kind = (ListView) findViewById(R.id.lv_kind);
        kind_iv_back= (ImageView) findViewById(R.id.kind_iv_back);
        kind_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list = new ArrayList<>();
        query = new BmobQuery<>();
        kindBaseAdapter = new KindBaseAdapter(KindListViewActivity.this, list);
        lv_kind.setAdapter(kindBaseAdapter);
        handler.sendEmptyMessage(0);

        lv_kind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(KindListViewActivity.this, KindMainActivity.class);
                 intent.putExtra("number",i);
                startActivity(intent);
            }
        });
    }
}
