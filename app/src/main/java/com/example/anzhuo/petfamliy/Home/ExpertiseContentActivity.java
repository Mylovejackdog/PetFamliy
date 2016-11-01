package com.example.anzhuo.petfamliy.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Expertise;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/10/28.
 */
public class ExpertiseContentActivity extends Activity {
    ImageView expertise_main_back;
    TextView expertise_title;
    TextView expertise_header;
    ImageView expertise_content;
    TextView expertise_footer;
    TextView expertise_main;
    BmobQuery<dog_Expertise> query;
    private  int getExpertiseChooseItem;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    query.order("-createdAt");
                    query.findObjects(new FindListener<dog_Expertise>() {
                        @Override
                        public void done(List<dog_Expertise> list, BmobException e) {
                            if(e==null){
                                expertise_title.setText(list.get(getExpertiseChooseItem).getTitle());
                                expertise_header.setText(list.get(getExpertiseChooseItem).getHeader());
                                Glide.with(ExpertiseContentActivity.this).load(list.get(getExpertiseChooseItem).getImage_content()).into(expertise_content);
                                expertise_main.setText(list.get(getExpertiseChooseItem).getMain());
                                expertise_footer.setText(list.get(getExpertiseChooseItem).getFooter());
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
        setContentView(R.layout.expertise_content_layout);
        expertise_main_back= (ImageView) findViewById(R.id.expertise_main_back);
        expertise_title= (TextView) findViewById(R.id.expertise_title);
        expertise_header= (TextView) findViewById(R.id.expertise_header);
        expertise_content= (ImageView) findViewById(R.id.expertise_content);
        expertise_main= (TextView) findViewById(R.id.expertise_main);
        expertise_footer= (TextView) findViewById(R.id.expertise_footer);
        Intent intent=getIntent();
        getExpertiseChooseItem=intent.getExtras().getInt("expertiseChooseItem");
        query=new BmobQuery<>();
        handler.sendEmptyMessage(1);
        expertise_main_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
