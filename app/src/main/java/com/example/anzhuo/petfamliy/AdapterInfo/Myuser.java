package com.example.anzhuo.petfamliy.AdapterInfo;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by anzhuo on 2016/11/9.
 */
public class MyUser extends BmobUser{
    private BmobFile img_head;
    private String nickName;

    public BmobFile getImg_head() {
        return img_head;
    }

    public void setImg_head(BmobFile img_head) {
        this.img_head = img_head;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


}