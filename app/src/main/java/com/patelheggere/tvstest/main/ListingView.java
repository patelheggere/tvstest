package com.patelheggere.tvstest.main;

import com.patelheggere.tvstest.model.EmployeeModel;

import java.util.List;

public interface ListingView {
    void showProgressBar();
    void hideProgressBar();
    void networkErrorMessage();
    void APIFailureMessage();
    void updateEmployeeDetails(List<EmployeeModel> mEmployeeModelList);
    void getOriginalData(List<EmployeeModel> mEmployeeModelList);
}
