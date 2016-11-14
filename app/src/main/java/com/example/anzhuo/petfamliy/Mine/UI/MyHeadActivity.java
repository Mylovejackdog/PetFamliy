package com.example.anzhuo.petfamliy.Mine.UI;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.Mine.Base.ZipImage;
import com.example.anzhuo.petfamliy.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import static com.example.anzhuo.petfamliy.R.id.kind_main_back;

/**
 * 设置头像页面
 * Created by anzhuo on 2016/11/4.
 */

public class MyHeadActivity extends Activity implements View.OnClickListener {
    private static final int PICTURE_FROM_CAMERA = 0X32;
    private static final int PICTURE_FROM_GALLERY = 0X34;

    private static final String IMAGE_FILE_NAME = "header.jpg";

    Bitmap bitmap;
    Uri uri;
    File file;//存储拍摄图片的文件

    ImageView kind_main_back,iv_head,iv_photo,iv_camera;
    TextView bt_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_head_layout);

        kind_main_back= (ImageView) findViewById(R.id.iv_back);
        iv_head= (ImageView) findViewById(R.id.iv_head);
        iv_photo= (ImageView) findViewById(R.id.btn_selectimage);
        iv_camera= (ImageView) findViewById(R.id.iv_xiangji);
        bt_save= (TextView) findViewById(R.id.bt_save);

        kind_main_back.setOnClickListener(this);
        iv_photo.setOnClickListener(this);
        iv_camera.setOnClickListener(this);
        bt_save.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                Intent intent=new Intent(MyHeadActivity.this,RedactPrefileActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_selectimage:
                gotosystempic(v);
                break;
            case R.id.iv_xiangji:
                gototakephoto(v);
                break;
            case R.id.bt_save:
                uploadHead();
                break;
        }

    }
    private void gotosystempic(View view){
        //设置启动相册的Action
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //设置类型
        intent.setType("image/*");
        //启动相册，这里使用有返回结果的启动
        startActivityForResult(intent, PICTURE_FROM_GALLERY);
    }
    private void gototakephoto(View view){
        //启动相机的Action
        Intent intent=new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        //文件的保存位置
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
                System.currentTimeMillis() + ".jpg");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //设置图片拍摄后保存的位置
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        //启动相机，这里使用有返回结果的启动
        startActivityForResult(intent, PICTURE_FROM_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case PICTURE_FROM_CAMERA:
                    //这里对图片进行了压缩，因为有些手机拍摄的照片过大，无法显示到ImageView中，所以我们将图片近行了压缩然后在进行显示
                    ZipImage.zipImage(Uri.fromFile(file).getPath());
                    //将图片设置到ImageView中，这里使用setImageURI（）方法进行设置。
                    iv_head.setImageURI(Uri.fromFile(file));
                    break;
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
                        iv_head.setImageURI(uri);
                  //      iv_head.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
    public void uploadHead() {
        final BmobFile bmobFile = new BmobFile(file);
        bmobFile.upload(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Log.i("lw",bmobFile+"");
                    Log.i("lw","123456");
                    MyUser user=new MyUser();
                    user.setImg_head(bmobFile);
                    MyUser user1= BmobUser.getCurrentUser(MyUser.class);
                    user.update(user1.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                Log.i("lw","123");
                                Toast.makeText(MyHeadActivity.this,"更改成功",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(MyHeadActivity.this,RedactPrefileActivity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                Log.i("lw",e+"++++++++++++++++++++++++");

                            }
                        }
                    });
                }
            }
        });

    }

}
