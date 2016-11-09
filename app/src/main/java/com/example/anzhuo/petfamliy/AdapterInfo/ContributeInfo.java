package com.example.anzhuo.petfamliy.AdapterInfo;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by anzhuo on 2016/11/8.
 */
public class ContributeInfo extends BmobObject {
    private String content;
    private BmobFile bmobFile;
    private String time;

    public ContributeInfo() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BmobFile getBmobFile() {
        return bmobFile;
    }

    public void setBmobFile(BmobFile bmobFile) {
        this.bmobFile = bmobFile;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
