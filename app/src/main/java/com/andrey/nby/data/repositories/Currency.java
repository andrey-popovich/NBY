package com.andrey.nby.data.repositories;

import io.realm.RealmObject;

import io.realm.annotations.RealmClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@RealmClass
public class Currency extends RealmObject {

    @SerializedName("r030")
    @Expose
    private Integer r030;

    @SerializedName("txt")
    @Expose
    private String txt;

    @SerializedName("rate")
    @Expose
    private Double rate;

    @SerializedName("cc")
    @Expose
    private String cc;

    @SerializedName("exchangedate")
    @Expose
    private String exchangeDate;

    public Integer getR030() {
        return r030;
    }

    public void setR030(Integer r030) {
        this.r030 = r030;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangedate) {
        this.exchangeDate = exchangedate;
    }
}
