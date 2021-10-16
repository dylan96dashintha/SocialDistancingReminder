package com.example.socialdistancingreminder.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.socialdistancingreminder.Model.CovidStatistics.CovidStat;
import com.example.socialdistancingreminder.Model.CovidStatistics.CovidStatistics;
import com.example.socialdistancingreminder.Model.CovidStatistics.CovidStatisticsAPI;
import com.example.socialdistancingreminder.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CovidStatisticActivity extends AppCompatActivity {
    private static final String TAG = "CovidStatisticActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.covid_statistic);
        showResult();
    }


    protected  void showResult() {
        CovidStatisticsAPI covidStatisticsAPI = CovidStat.getRetrofitInstance().create(CovidStatisticsAPI.class);
        Call<CovidStatistics> call = covidStatisticsAPI.getAllData();

        call.enqueue(new Callback<CovidStatistics>() {
            @Override
            public void onResponse(Call<CovidStatistics> call, Response<CovidStatistics> response) {
                Log.e(TAG, "onResponse: code: "+response.code());


                CovidStatistics.data data = response.body().getData();
                //String local_active_cases = String.valueOf(data.getLocal_active_cases());
                String update_date_time = data.getUpdate_date_time();
                String local_new_case = String.valueOf(data.getLocal_new_cases());
                String local_total_cases = String.valueOf(data.getLocal_total_cases());
                String local_total_number_of_individuals_in_hospitals = String.valueOf(data.getLocal_total_number_of_individuals_in_hospitals());
                String local_deaths = String.valueOf(data.getLocal_deaths());
                String local_new_deaths = String.valueOf(data.getLocal_new_deaths());
                String local_active_cases = String.valueOf(data.getLocal_active_cases());
                Log.e(TAG, "onResponse : active cases: "+data.getLocal_active_cases());

                TextView updatedTime = (TextView) findViewById(R.id.updatedTime);
                updatedTime.setText("Updated at :: "+update_date_time);

                TextView newCase = (TextView) findViewById(R.id.row1col1);
                newCase.setText("New Covid Cases in Sri Lanka");

                TextView newCaseNum = (TextView) findViewById(R.id.row1col2);
                newCaseNum.setText(local_new_case);

                TextView activeCase = (TextView) findViewById(R.id.row2col1);
                activeCase.setText("New active Cases in Sri Lanka");

                TextView activeCaseNum = (TextView) findViewById(R.id.row2col2);
                activeCaseNum.setText(local_active_cases);

                TextView totalCase = (TextView) findViewById(R.id.row3col1);
                totalCase.setText("Total Covid Cases in Sri Lanka");

                TextView totalCaseNum = (TextView) findViewById(R.id.row3col2);
                totalCaseNum.setText(local_total_cases);

                TextView hosCase = (TextView) findViewById(R.id.row4col1);
                hosCase.setText("Covid Cases in Sri Lankan Hospitals");

                TextView hosCaseNum = (TextView) findViewById(R.id.row4col2);
                hosCaseNum.setText(local_total_number_of_individuals_in_hospitals);

                TextView newDeathCase = (TextView) findViewById(R.id.row5col1);
                newDeathCase.setText("New Covid Death count in Sri Lanka");

                TextView newDeathCaseNum = (TextView) findViewById(R.id.row5col2);
                newDeathCaseNum.setText(local_new_deaths);

                TextView deathCase = (TextView) findViewById(R.id.row6col1);
                deathCase.setText("Covid Death count in Sri Lanka");

                TextView deathCaseNum = (TextView) findViewById(R.id.row6col2);
                deathCaseNum.setText(local_deaths);



            }
            @Override
            public void onFailure(Call<CovidStatistics> call, Throwable t) {
                Log.e(TAG, "error"+t);
            }
        });
    }
}
