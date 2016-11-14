package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * 注册页面
 * Created by anzhuo on 2016/11/3.
 */

public class RegisterActivity extends Activity implements View.OnClickListener{
    private static final int PICTURE_FROM_GALLERY = 0X34;
    Bitmap bitmap;
    Uri uri;
    File file;//存储拍摄图片的文件

    ImageView kind_main_back,iv_head,iv_write;
    EditText  et_user,et_pwd,et_email,confirm_password,et_name;
    TextView  mine_protocol,mine_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        kind_main_back= (ImageView) findViewById(R.id.kind_main_back);
        iv_head= (ImageView) findViewById(R.id.iv_head);
        et_user= (EditText) findViewById(R.id.et_user);
        et_pwd= (EditText) findViewById(R.id.et_pwd);
        et_email= (EditText) findViewById(R.id.et_email);
        et_name= (EditText) findViewById(R.id.et_name);
        iv_write= (ImageView) findViewById(R.id.iv_write);
        confirm_password= (EditText) findViewById(R.id.confirm_password);
        mine_protocol= (TextView) findViewById(R.id.mine_protocol);
        mine_register= (TextView) findViewById(R.id.mine_register);

        kind_main_back.setOnClickListener(this);
        iv_write.setOnClickListener(this);
        mine_protocol.setOnClickListener(this);
        mine_register.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kind_main_back:
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.iv_write:
                gotosystempic(v);
                break;
            case R.id.mine_register:
               if (et_pwd.getText().toString().equals(confirm_password.getText().toString())){
                   uploadHead(v);
               }else {
                   Toast.makeText(RegisterActivity.this,"两次输入密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
               }
                break;
            case R.id.mine_protocol:
                Intent intent2=new Intent(RegisterActivity.this,UserAgreementActivity.class);
                startActivity(intent2);
                finish();
                break;
        }

    }
    //调用相册图片
    private void gotosystempic(View view){
        //设置启动相册的Action
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //设置类型
        intent.setType("image/*");
        //启动相册，这里使用有返回结果的启动
        startActivityForResult(intent, PICTURE_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case PICTURE_FROM_GALLERY:
                    //通过返回的data数据，获取图片的路径信息，但是这个路径是Uri的。
                    uri =data.getData();
                    ContentResolver contentResolver=this.getContentResolver();
                    try {
                        bitmap= BitmapFactory.decodeStream(contentResolver.openInputStream(uri));
                        //把bitmap写成file文件
                        file = new File("/sdcard/myFolder");
                        if (!file.exists())
                            file.mkdir();
                        file = new File("/sdcard/temp.jpg".trim());
                        String fileName = file.getName();
                        String mName = fileName.substring(0, fileName.lastIndexOf("."));
                        String sName = fileName.substring(fileName.lastIndexOf("."));
                        // /sdcard/myFolder/temp_cropped.jpg
                        String newFilePath = "/sdcard/myFolder" + "/" + mName + "_cropped" + sName;
                        file = new File(newFilePath);
                        try {
                            file.createNewFile();
                            FileOutputStream fos = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, fos);
                            fos.flush();
                            fos.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        //  System.out.println("the bmp toString:"+bitmap);
                        Log.i("LW", "哈哈：" + bitmap);
                        iv_head.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    //注册
    public void uploadHead(View view) {
        final BmobFile bmobFile = new BmobFile(file);
        bmobFile.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Log.i("lw","12");
                    MyUser userInfo = new MyUser();
                    userInfo.setUsername(et_user.getText().toString());
                    userInfo.setPassword(et_pwd.getText().toString());
                    userInfo.setEmail(et_email.getText().toString());
                    userInfo.setNickName(et_name.getText().toString());
                    userInfo.setImg_head(bmobFile);
                    userInfo.signUp(new SaveListener<MyUser>() {
                        @Override
                        public void done(MyUser userInfo, BmobException e) {
                            if (e==null){
                                Log.i("lw", "12345");
                                Toast.makeText(RegisterActivity.this,"注册成功："+"账号："+et_user.getText().toString(),Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Log.i("lw","1234");
                                Toast.makeText(RegisterActivity.this, "注册失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else {
                    Log.i("lw","123");
                    Toast.makeText(RegisterActivity.this, "注册失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }



}
