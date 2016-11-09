package com.example.anzhuo.petfamliy.AdapterInfo;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by anzhuo on 2016/11/7.
 */
public class Recommend extends BmobObject{
    private String head;
    private String photo;
    private String content;
    private String name;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
