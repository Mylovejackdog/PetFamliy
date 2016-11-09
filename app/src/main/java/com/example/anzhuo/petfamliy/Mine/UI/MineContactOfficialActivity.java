package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.anzhuo.petfamliy.R;

/**
 *客服页面
 * Created by anzhuo on 2016/11/7.
 */

public class MineContactOfficialActivity extends Activity {

    ImageView kind_mine_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_contact_official_layout);

        kind_mine_back= (ImageView) findViewById(R.id.kind_main_back);

        kind_mine_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
