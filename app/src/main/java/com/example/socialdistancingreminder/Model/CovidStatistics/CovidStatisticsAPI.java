package com.example.socialdistancingreminder.Model.CovidStatistics;
import com.example.socialdistancingreminder.Model.CovidStatistics.CovidStatistics;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidStatisticsAPI {

    @GET("api/get-current-statistical")
    Call<CovidStatistics> getAllData();

}
