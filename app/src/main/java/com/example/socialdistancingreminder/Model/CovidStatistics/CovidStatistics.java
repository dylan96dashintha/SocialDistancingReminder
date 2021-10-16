package com.example.socialdistancingreminder.Model.CovidStatistics;

public class CovidStatistics {

    data data;

    public CovidStatistics.data getData() {
        return data;
    }

    public void setData(CovidStatistics.data data) {
        this.data = data;
    }

    public class data {
        String update_date_time;
        int local_new_cases;
        int local_total_cases;
        int local_total_number_of_individuals_in_hospitals;
        int local_deaths;
        int local_new_deaths;
        int local_active_cases;

        public String getUpdate_date_time() {
            return update_date_time;
        }

        public void setUpdate_date_time(String update_date_time) {
            this.update_date_time = update_date_time;
        }

        public int getLocal_new_cases() {
            return local_new_cases;
        }

        public void setLocal_new_cases(int local_new_cases) {
            this.local_new_cases = local_new_cases;
        }

        public int getLocal_total_cases() {
            return local_total_cases;
        }

        public void setLocal_total_cases(int local_total_cases) {
            this.local_total_cases = local_total_cases;
        }

        public int getLocal_total_number_of_individuals_in_hospitals() {
            return local_total_number_of_individuals_in_hospitals;
        }

        public void setLocal_total_number_of_individuals_in_hospitals(int local_total_number_of_individuals_in_hospitals) {
            this.local_total_number_of_individuals_in_hospitals = local_total_number_of_individuals_in_hospitals;
        }

        public int getLocal_deaths() {
            return local_deaths;
        }

        public void setLocal_deaths(int local_deaths) {
            this.local_deaths = local_deaths;
        }

        public int getLocal_new_deaths() {
            return local_new_deaths;
        }

        public void setLocal_new_deaths(int local_new_deaths) {
            this.local_new_deaths = local_new_deaths;
        }

        public int getLocal_active_cases() {
            return local_active_cases;
        }

        public void setLocal_active_cases(int local_active_cases) {
            this.local_active_cases = local_active_cases;
        }
    }



}
