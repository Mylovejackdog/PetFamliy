package com.example.anzhuo.petfamliy.Community;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anzhuo.petfamliy.Adapter.ChooseAdapter;
import com.example.anzhuo.petfamliy.Adapter.PublishAdapter;
import com.example.anzhuo.petfamliy.AdapterInfo.ContributeInfo;
import com.example.anzhuo.petfamliy.AdapterInfo.EventEntry;
import com.example.anzhuo.petfamliy.AdapterInfo.MyUser;
import com.example.anzhuo.petfamliy.AdapterInfo.Post;
import com.example.anzhuo.petfamliy.AdapterInfo.Recommend;
import com.example.anzhuo.petfamliy.Main.MainActivity;
import com.example.anzhuo.petfamliy.R;
import com.litao.android.lib.Utils.GridSpacingItemDecoration;
import com.litao.android.lib.entity.PhotoEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by anzhuo on 2016/11/7.
 */
public class ContributeActivity extends Activity implements ChooseAdapter.OnItmeClickListener{
    ImageView iv_return;
    TextView tv_publish;
    EditText et_content;
    RecyclerView rv_photo;
    ChooseAdapter mAdapter;
    PhotoEntry photoEntry;
    String path;
    List<PhotoEntry> list;
    Post contributeInfo;
    ImageView choose_photo;

    MyUser user= BmobUser.getCurrentUser(MyUser.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contribute_layout);
        iv_return= (ImageView) findViewById(R.id.iv_return);
        tv_publish= (TextView) findViewById(R.id.tv_publish);
        et_content= (EditText) findViewById(R.id.et_content);
        rv_photo= (RecyclerView) findViewById(R.id.rv_photo);
        choose_photo= (ImageView) findViewById(R.id.choose_photo);
        et_content.setSelection(et_content.getText().toString().length());
        EventBus.getDefault().register(this);
        list=new ArrayList<>();
        mAdapter=new ChooseAdapter(this,list);
        rv_photo.setLayoutManager(new GridLayoutManager(this,3));
        rv_photo.setAdapter(mAdapter);
        rv_photo.addItemDecoration(new GridSpacingItemDecoration(3,4,true));
        choose_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ContributeActivity.this, SdcardPhotoActivity.class));
                EventBus.getDefault().postSticky(new EventEntry(mAdapter.getData(),EventEntry.SELECTED_PHOTOS_ID));
            }
        });
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, final int i, int i1, int i2) {
                if (et_content.getText().toString()!=null&&!et_content.getText().toString().equals("")&&i>=i1){
                    tv_publish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            for (int a = 0; a < list.size(); a++) {
                                photoEntry = list.get(a);
                                path = photoEntry.getPath();
                                Log.i("RN",path+"/"+a);
                                if (path!=null){
                                    contributeInfo=new Post();
                                    String content=et_content.getText().toString();
                                    contributeInfo.setContent(content);
                                    contributeInfo.setAuthor(user);
                                    final BmobFile bmobFile=new BmobFile(new File(path));
                                    bmobFile.upload(new UploadFileListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            contributeInfo.setImage(bmobFile);
                                            contributeInfo.save(new SaveListener<String>() {
                                                @Override
                                                public void done(String s, BmobException e) {
                                                    if (e==null){
                                                        Toast.makeText(ContributeActivity.this, "发表成功", Toast.LENGTH_SHORT).show();
                                                     finish();

                                                    }else {
                                                        Toast.makeText(ContributeActivity.this,"发表失败",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    });
                                }else {
                                    Log.i("RN","请发表图片");
                                }
                            }
                        }
                    });

                }else {
                    tv_publish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(ContributeActivity.this,"请多写点内容",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
    @Override
    public void onItemClicked(int position) {
        if (position == mAdapter.getItemCount()-1) {
            startActivity(new Intent(ContributeActivity.this, SdcardPhotoActivity.class));
            EventBus.getDefault().postSticky(new EventEntry(mAdapter.getData(),EventEntry.SELECTED_PHOTOS_ID));
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(EventEntry entries){
        if (entries.id == EventEntry.RECEIVED_PHOTOS_ID) {
            mAdapter.reloadList(entries.photos);
            Log.i("RN",list.size()+"");
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photoMessageEvent(PhotoEntry entry){
        mAdapter.appendPhoto(entry);

    }


}
