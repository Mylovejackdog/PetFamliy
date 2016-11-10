package com.example.anzhuo.petfamliy.Mine.Base;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by anzhuo on 2016/11/3.
 */

public class User extends BmobUser {
    private String nickname;
    private String number;
    private String age;
    private BmobFile img_head;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public BmobFile getImg_head() {
        return img_head;
    }

    public void setImg_head(BmobFile img_head) {
        this.img_head = img_head;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
