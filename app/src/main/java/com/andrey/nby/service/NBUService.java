package com.andrey.nby.service;

import com.andrey.nby.data.repositories.Currency;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NBUService {

    @GET("exchange/")
    Call<List<Currency>> getCurrencyList(@Query("json") String json);

    /**
     * @param date YYYYMMDD
     */
    @GET("exchange/")
    Call<List<Currency>> getCurrencyListByDate(@Query("json") String json,
                                               @Query("date") String date);

    /**
     * @param valcode currency by code (EUR, USD, UAH, RUB)
     */
    @GET("exchange/")
    Call<Currency> getCurrencyByValcode(@Query("json") String json,
                                         @Query("valcode") String valcode);
}
