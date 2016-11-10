package com.example.anzhuo.petfamliy.Home.KindTextFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.BmobDataInfo.dog_Type;
import com.example.anzhuo.petfamliy.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by anzhuo on 2016/10/26.
 */
public class KindMainActivity extends AppCompatActivity implements View.OnClickListener {
    RadioGroup rg_kind_main;
    RadioButton rb_one;
    RadioButton rb_two;
    RadioButton rb_three;
    RadioButton rb_four;
    CharactorFragment charactorFragment;
    FormFragment formFragment;
    FeedFragment feedFragment;
    LifeStyleFragment lifeStyleFragment;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    TextView dog_name;
    ImageView iv_head;
    TextView card;
    TextView information;
    ImageView kind_main_back;
    private  static final int MG=101;
    private  int choose;

    BmobQuery<dog_Type> query;

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
                                  dog_name.setText(list.get(choose).getName());
                                 Glide.with(KindMainActivity.this).load(list.get(choose).getImage_head()).into(iv_head);
                                 card.setText(list.get(choose).getPersonal());
                                 information.setText(list.get(choose).getIntroduction());
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
        setContentView(R.layout.kind_content_layout);
        query=new BmobQuery<dog_Type>();
        rg_kind_main = (RadioGroup) findViewById(R.id.rg_kind_main);
        rb_one = (RadioButton) findViewById(R.id.rb_one);
        rb_two = (RadioButton) findViewById(R.id.rb_two);
        rb_three = (RadioButton) findViewById(R.id.rb_three);
        rb_four = (RadioButton) findViewById(R.id.rb_four);
        dog_name= (TextView) findViewById(R.id.dog_name);
        iv_head= (ImageView) findViewById(R.id.iv_head);
        card= (TextView) findViewById(R.id.card);
        information= (TextView) findViewById(R.id.information);
        kind_main_back= (ImageView) findViewById(R.id.kind_main_back);
        Intent intent=getIntent();
        choose=intent.getExtras().getInt("number");
          handler.sendEmptyMessage(MG);
        rb_one.setOnClickListener(this);
        rb_two.setOnClickListener(this);
        rb_three.setOnClickListener(this);
        rb_four.setOnClickListener(this);
        kind_main_back.setOnClickListener(this);

        showFragment(0);
    }

    private void showFragment(int i) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        hideAllFrame(transaction);
        switch (i) {
            case 0:
                if (charactorFragment == null) {
                    charactorFragment = new CharactorFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("click",choose);
                    charactorFragment.setArguments(bundle);
                    transaction.add(R.id.fl_text_main, charactorFragment);
                } else {
                    transaction.show(charactorFragment);
                }
                break;
            case 1:
                if (formFragment == null) {
                    formFragment = new FormFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("click",choose);
                    formFragment.setArguments(bundle);
                    transaction.add(R.id.fl_text_main, formFragment);
                } else {
                    transaction.show(formFragment);
                }
                break;
            case 2:
                if (feedFragment == null) {
                    feedFragment = new FeedFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("click",choose);
                    feedFragment.setArguments(bundle);
                    transaction.add(R.id.fl_text_main, feedFragment);
                } else {
                    transaction.show(feedFragment);
                }
                break;
            case 3:
                if (lifeStyleFragment == null) {
                    lifeStyleFragment = new LifeStyleFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("click",choose);
                    lifeStyleFragment.setArguments(bundle);
                    transaction.add(R.id.fl_text_main, lifeStyleFragment);
                } else {
                    transaction.show(lifeStyleFragment);
                }
                break;

        }
        transaction.commit();

    }

    private void hideAllFrame(FragmentTransaction transaction) {
        if (charactorFragment != null) {
            transaction.hide(charactorFragment);
        }
        if (formFragment != null) {
            transaction.hide(formFragment);
        }
        if (feedFragment != null) {
            transaction.hide(feedFragment);
        }
        if (lifeStyleFragment != null) {
            transaction.hide(lifeStyleFragment);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_one:
                showFragment(0);
                break;
            case R.id.rb_two:
                showFragment(1);
                break;
            case R.id.rb_three:
                showFragment(2);
                break;
            case R.id.rb_four:
                showFragment(3);
                break;
            case R.id.kind_main_back:
                finish();
                break;

        }
    }


}
