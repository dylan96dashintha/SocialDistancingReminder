package com.example.socialdistancingreminder.Model.CovidStatistics;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidStat {
    private static String BASE_URL = "https://www.hpb.health.gov.lk/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
