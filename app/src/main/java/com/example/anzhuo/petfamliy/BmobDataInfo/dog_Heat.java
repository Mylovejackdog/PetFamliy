package com.example.anzhuo.petfamliy.BmobDataInfo;

import cn.bmob.v3.BmobObject;

/**
 * Created by anzhuo on 2016/10/27.
 */
public class dog_Heat extends BmobObject {
    private String title;
    private String content;
    private String HeatType;
    private String image_head;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getImage_head() {
        return image_head;
    }

    public void setImage_head(String image_head) {
        this.image_head = image_head;
    }

    public String getHeatType() {
        return HeatType;
    }

    public void setHeatType(String heatType) {
        HeatType = heatType;
    }
}
