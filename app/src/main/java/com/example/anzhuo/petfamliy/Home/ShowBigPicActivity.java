package com.example.anzhuo.petfamliy.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.anzhuo.petfamliy.R;

/**
 * Created by anzhuo on 2016/11/12.
 */

public class ShowBigPicActivity extends Activity {

    ImageView big_pic;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imageshower_layout);
        big_pic = (ImageView) findViewById(R.id.big_pic);
        final Intent intent = getIntent();

        //  Glide.with(this).load("http://www.zbjuran.com/uploads/allimg/160809/7-160P9152QQ61.gif").asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(big_pic);
        final ScreenShowDialog dialog = new ScreenShowDialog(this);
        dialog.show();
        // 两秒后关闭后dialog
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Glide.with(ShowBigPicActivity.this).load(intent.getExtras().getString("photosrc")).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(big_pic);
                dialog.dismiss();
            }
        }, 1000 * 2);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }


}
