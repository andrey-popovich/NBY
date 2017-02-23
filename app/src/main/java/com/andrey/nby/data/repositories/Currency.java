package com.andrey.nby.data.repositories;

import io.realm.RealmObject;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@RealmClass
public class Currency extends RealmObject {

    @PrimaryKey
    @SerializedName("r030")
    private Integer r030;

    @SerializedName("txt")
    private String txt;

    @SerializedName("rate")
    private Double rate;

    @SerializedName("cc")
    private String cc;

    @SerializedName("exchangedate")
    private String exchangeDate;

    public void setR030(Integer r030) {
        this.r030 = r030;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    private boolean isFavorite;

    public Integer getR030() {
        return r030;
    }

    public String getTxt() {
        return txt;
    }

    public Double getRate() {
        return rate;
    }

    public String getCc() {
        return cc;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
