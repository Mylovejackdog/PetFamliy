package com.example.anzhuo.petfamliy.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Toast;

import com.example.anzhuo.petfamliy.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by anzhuo on 2016/11/10.
 */

public class FirstActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);
        if (checkNetworkInfo()){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
        }else {
            Toast.makeText(this, "网络异常", Toast.LENGTH_LONG).show();
            TimerTask timerTask=new TimerTask() {
                @Override
                public void run() {
                    onDestroy();
                }
            };
            Timer timer=new Timer();
            timer.schedule(timerTask,3000);



        }

    }
    public boolean checkNetworkInfo() {
        ConnectivityManager conMan = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
            return true;
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
            return true;
        return false;
    }

    public void onDestroy() {
        finish();
        super.onDestroy();
    }

}
