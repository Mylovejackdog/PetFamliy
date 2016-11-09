package com.example.anzhuo.petfamliy.Mine.UI.Fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anzhuo.petfamliy.Mine.Base.User;
import com.example.anzhuo.petfamliy.Mine.UI.LoginActivity;
import com.example.anzhuo.petfamliy.Mine.UI.MineContactOfficialActivity;
import com.example.anzhuo.petfamliy.Mine.UI.MineSettingActivity;
import com.example.anzhuo.petfamliy.Mine.UI.MyHomePapeAtivity;
import com.example.anzhuo.petfamliy.Mine.UI.MyLoveActivity;
import com.example.anzhuo.petfamliy.Mine.UI.MyPrefileActivity;
import com.example.anzhuo.petfamliy.R;

import cn.bmob.v3.BmobUser;

/**
 * 我的页面
 * Created by anzhuo on 2016/11/3.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    LinearLayout ll_login;
    ImageView    iv_head;
    TextView     mine_name,mine_main,mine_love,mine_message,mine_circle,mine_setting,mine_match;
    User user;




    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.mine_layout,null);
        return view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ll_login= (LinearLayout) view.findViewById(R.id.ll_login);
        iv_head= (ImageView) view.findViewById(R.id.iv_head);
        mine_name= (TextView) view.findViewById(R.id.mine_name);
        mine_main= (TextView) view.findViewById(R.id.mine_main);
        mine_love= (TextView) view.findViewById(R.id.mine_love);
        mine_message= (TextView) view.findViewById(R.id.mine_message);
        mine_circle= (TextView) view.findViewById(R.id.mine_circle);
        mine_setting= (TextView) view.findViewById(R.id.mine_setting);
        mine_match= (TextView) view.findViewById(R.id.mine_match);

        user= BmobUser.getCurrentUser(User.class);
        if (user != null) {
            //     Log.i("LW",userInfo.getHead().getFileUrl()+"");
            if(user.getHead()!=null) {
                Glide.with(getActivity()).load(user.getHead().getFileUrl()).asBitmap().into(iv_head);
            }
            else {
                iv_head.setImageResource(R.mipmap.share_personal_default);
            }
            String name = (String) BmobUser.getObjectByKey("nickname");
            mine_name.setText(name);
        } else {
            mine_name.setText("游客");
            iv_head.setImageResource(R.mipmap.share_personal_default);
        }

        ll_login.setOnClickListener(this);
        iv_head.setOnClickListener(this);
        mine_main.setOnClickListener(this);
        mine_love.setOnClickListener(this);
        mine_message.setOnClickListener(this);
        mine_circle.setOnClickListener(this);
        mine_setting.setOnClickListener(this);
        mine_match.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_head:
               if (user==null) {
                   Intent intent = new Intent(getActivity(), LoginActivity.class);
                   startActivity(intent);
               }else {
                   Intent intent1=new Intent(getActivity(), MyPrefileActivity.class);
                   startActivity(intent1);
               }
                break;
            case R.id.mine_main:
                if (user!=null){
                    Intent intent2=new Intent(getActivity(), MyHomePapeAtivity.class);
                    startActivity(intent2);
                }else {
                    Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent3);
                }
                break;
            case R.id.mine_love:
                if (user!=null){
                    Intent intent4=new Intent(getActivity(), MyLoveActivity.class);
                    startActivity(intent4);
                }else {
                    Intent intent5 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent5);
                }
                break;
            case R.id.mine_message:
                break;
            case R.id.mine_circle:
                break;
            case R.id.mine_setting:
                Intent intent6=new Intent(getActivity(), MineSettingActivity.class);
                startActivity(intent6);
                break;
            case R.id.mine_match:
                Intent intent7=new Intent(getActivity(), MineContactOfficialActivity.class);
                startActivity(intent7);
                break;

        }

    }
}
