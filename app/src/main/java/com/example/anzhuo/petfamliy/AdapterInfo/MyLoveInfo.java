package com.example.anzhuo.petfamliy.AdapterInfo;

import cn.bmob.v3.BmobObject;

/**
 * Created by anzhuo on 2016/11/10.
 */

public class MyLoveInfo extends BmobObject {
    private String myLoveHead;
    private String myLoveName;
    private String myLovePhoto;
    private String myLoveContent;
    private String time;
    public String getMyLoveHead() {
        return myLoveHead;
    }

    public void setMyLoveHead(String myLoveHead) {
        this.myLoveHead = myLoveHead;
    }

    public String getMyLoveName() {
        return myLoveName;
    }

    public void setMyLoveName(String myLoveName) {
        this.myLoveName = myLoveName;
    }

    public String getMyLovePhoto() {
        return myLovePhoto;
    }

    public void setMyLovePhoto(String myLovePhoto) {
        this.myLovePhoto = myLovePhoto;
    }

    public String getMyLoveContent() {
        return myLoveContent;
    }

    public void setMyLoveContent(String myLoveContent) {
        this.myLoveContent = myLoveContent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
