package com.example.anzhuo.petfamliy.Community;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.anzhuo.petfamliy.AdapterInfo.EventEntry;
import com.example.anzhuo.petfamliy.R;
import com.litao.android.lib.BaseGalleryActivity;
import com.litao.android.lib.Configuration;
import com.litao.android.lib.entity.PhotoEntry;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Created by anzhuo on 2016/11/7.
 */
public class SdcardPhotoActivity extends BaseGalleryActivity implements View.OnClickListener {
    private TextView mTextViewOpenAlbum;
    private TextView mTextViewSelectedCount;
    private TextView mTextViewSend;
    private List<PhotoEntry> mSelectedPhotos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdcard_photo_layout);
        EventBus.getDefault().register(this);
        setTitle("Photos");
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/
        initView();
        attachFragment(R.id.gallery_root);

    }
    private void initView(){
        mTextViewOpenAlbum = (TextView) findViewById(R.id.album);
        mTextViewSelectedCount = (TextView) findViewById(R.id.selected_count);
        mTextViewSend = (TextView) findViewById(R.id.send_photos);
        mTextViewOpenAlbum.setOnClickListener(this);
        mTextViewSelectedCount.setOnClickListener(this);
        mTextViewSend.setOnClickListener(this);
    }
    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    @Override
    public Configuration getConfiguration() {
        Configuration cfg=new Configuration.Builder()
                .hasCamera(true)
                .hasShade(true)
                .hasPreview(true)
                .setSpaceSize(4)
                .setPhotoMaxWidth(120)
                .setCheckBoxColor(0xFF3F51B5)
                .setDialogHeight(Configuration.DIALOG_HALF)
                .setDialogMode(Configuration.DIALOG_GRID)
                .setMaximum(1)
                .setTip(null)
                .setAblumsTitle(null)
                .build();
        return cfg;
    }

    @Override
    public List<PhotoEntry> getSelectPhotos() {
        return mSelectedPhotos;
    }

    @Override
    public void onSelectedCountChanged(int i) {
        mTextViewSelectedCount.setVisibility(i>0?View.VISIBLE:View.INVISIBLE);
        mTextViewSelectedCount.setText(String.valueOf(i));
    }

    @Override
    public void onAlbumChanged(String s) {
//        getSupportActionBar().setSubtitle(s);
    }

    @Override
    public void onTakePhoto(PhotoEntry photoEntry) {
        EventBus.getDefault().post(photoEntry);
        finish();
    }

    @Override
    public void onChoosePhotos(List<PhotoEntry> list) {
        EventBus.getDefault().post(new EventEntry(list,EventEntry.RECEIVED_PHOTOS_ID));
        finish();
    }

    @Override
    public void onPhotoClick(PhotoEntry photoEntry) {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.album:
                openAlbum();
                break;
            case R.id.send_photos:
                sendPhotos();
                break;
        }
    }
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(EventEntry entry){
        if (entry.id == EventEntry.SELECTED_PHOTOS_ID) {
            mSelectedPhotos = entry.photos;
        }
    }
    public List<PhotoEntry> getmSelectedPhotos() {
        return mSelectedPhotos;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
