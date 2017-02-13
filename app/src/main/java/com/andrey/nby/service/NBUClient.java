package com.andrey.nby.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NBUClient {

    private static final String ENDPOINT = "https://bank.gov.ua/NBUStatService/v1/statdirectory/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NBUService getNBUService() {
        return getRetrofitInstance().create(NBUService.class);
    }
}
