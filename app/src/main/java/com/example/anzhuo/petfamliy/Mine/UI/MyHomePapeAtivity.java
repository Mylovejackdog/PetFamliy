package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.anzhuo.petfamliy.R;

/**
 * 我的主页
 * Created by anzhuo on 2016/11/3.
 */

public class MyHomePapeAtivity extends Activity {
    ImageView king_main_back;
    SwipeRefreshLayout swipe;
    ListView lv_pape;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_home_pape_activity);

        king_main_back= (ImageView) findViewById(R.id.kind_main_back);
        swipe= (SwipeRefreshLayout) findViewById(R.id.swipe);
        lv_pape= (ListView) findViewById(R.id.lv_pape);

        king_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
