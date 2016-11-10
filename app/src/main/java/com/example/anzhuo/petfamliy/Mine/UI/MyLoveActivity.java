package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.anzhuo.petfamliy.R;

/**
 * 我喜欢的
 * Created by anzhuo on 2016/11/4.
 */

public class MyLoveActivity extends Activity {
    ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_love_layout);
        iv_back= (ImageView) findViewById(R.id.kind_main_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
