package com.example.anzhuo.petfamliy.BmobDataInfo;

import cn.bmob.v3.BmobObject;

/**
 * Created by anzhuo on 2016/10/25.
 */
public class dog_Type extends BmobObject{

    private  String name;
    private  String image_head;
    private  String personal;
    private  String introduction;
    private String character;
    private String characteristics;
    private String feedWay;
    private String lifeStyle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_head() {
        return image_head;
    }

    public void setImage_head(String image_head) {
        this.image_head = image_head;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public String getFeedWay() {
        return feedWay;
    }

    public void setFeedWay(String feedWay) {
        this.feedWay = feedWay;
    }

    public String getLifeStyle() {
        return lifeStyle;
    }

    public void setLifeStyle(String lifeStyle) {
        this.lifeStyle = lifeStyle;
    }
}
