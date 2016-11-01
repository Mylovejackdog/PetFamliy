package com.example.anzhuo.petfamliy.BmobDataInfo;

import cn.bmob.v3.BmobObject;

/**
 * Created by anzhuo on 2016/10/26.
 */
public class dog_Disease extends BmobObject {
    private  String DiseaseName;
    private  String image_pro;
    private String summarize;
    private String prevent;
    private String pathogenesis;
    private String treat;
    private String identify;
    private String symptom;

    public String getDiseaseName() {
        return DiseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        DiseaseName = diseaseName;
    }

    public String getImage_pro() {
        return image_pro;
    }

    public void setImage_pro(String image_pro) {
        this.image_pro = image_pro;
    }

    public String getSummarize() {
        return summarize;
    }

    public void setSummarize(String summarize) {
        this.summarize = summarize;
    }

    public String getPrevent() {
        return prevent;
    }

    public void setPrevent(String prevent) {
        this.prevent = prevent;
    }

    public String getPathogenesis() {
        return pathogenesis;
    }

    public void setPathogenesis(String pathogenesis) {
        this.pathogenesis = pathogenesis;
    }

    public String getTreat() {
        return treat;
    }

    public void setTreat(String treat) {
        this.treat = treat;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }
}
