package com.example.anzhuo.petfamliy.Main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.anzhuo.petfamliy.Community.CommunityFragment;
import com.example.anzhuo.petfamliy.Home.HomeFragment;
import com.example.anzhuo.petfamliy.Mine.UI.Fragment.MineFragment;

import com.example.anzhuo.petfamliy.R;

import cn.bmob.v3.Bmob;

/**
 * Created by anzhuo on 2016/10/24.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RadioGroup rg_main;
    RadioButton rb_home;
    RadioButton rb_community;
    RadioButton rb_mine;
    HomeFragment homeFragment;
    CommunityFragment communityFragment;
    MineFragment mineFragment;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Bmob.initialize(this, "8456bf8d25dc1d6b2ba651eb5756ed67");
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_community = (RadioButton) findViewById(R.id.rb_community);
        rb_mine = (RadioButton) findViewById(R.id.rb_mine);
        rb_home.setOnClickListener(this);
        rb_community.setOnClickListener(this);
        rb_mine.setOnClickListener(this);
        showFragment(0);
    }


    private void showFragment(int i) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        hideAllFrame(transaction);
        switch (i) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_main, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (communityFragment == null) {
                    communityFragment = new CommunityFragment();
                    transaction.add(R.id.fl_main, communityFragment);
                } else {
                    transaction.show(communityFragment);
                }
                break;
            case 2:
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.fl_main, mineFragment);
                } else {
                    transaction.show(mineFragment);
                }
                break;

        }
        transaction.commit();

    }

    private void hideAllFrame(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (communityFragment != null) {
            transaction.hide(communityFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                showFragment(0);
                break;
            case R.id.rb_community:
                showFragment(1);
                break;
            case R.id.rb_mine:
                showFragment(2);
                break;

        }
    }
}
