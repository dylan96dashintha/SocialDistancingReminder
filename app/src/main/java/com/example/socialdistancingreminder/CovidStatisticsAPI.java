package com.example.socialdistancingreminder;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidStatisticsAPI {

    @GET("api/get-current-statistical")
    Call<CovidStatistics> getAllData();

}
