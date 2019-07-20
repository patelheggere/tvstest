package com.patelheggere.tvstest.main;

import android.content.Context;

import com.android.volley.Request;
import com.patelheggere.tvstest.model.EmployeeModel;
import com.patelheggere.tvstest.network.volley.VolleyLib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import static com.patelheggere.tvstest.constants.StringConstants.BASE_URL;
import static com.patelheggere.tvstest.constants.StringConstants.GET_EMP_DATA;
import static com.patelheggere.tvstest.constants.StringConstants.PASSWORD;
import static com.patelheggere.tvstest.constants.StringConstants.USERNAME;

public class ListPresenter {
    private static final String TAG = "ListPresenter";
    private ListingView mListingView;
    private List<EmployeeModel> mEmployeeModelList;
    private Context mContext;

    public ListPresenter(Context mContext, ListingView mListingView) {
        this.mListingView = mListingView;
        this.mContext = mContext;
    }
    public void getEmployeeDetails(String userName, String password){
        mListingView.showProgressBar();
        HashMap<String, String> inputValues = new HashMap<>();
        inputValues.put(USERNAME, userName);
        inputValues.put(PASSWORD, password);
        VolleyLib.jsonObjectRequest(mContext, Request.Method.POST, BASE_URL+GET_EMP_DATA, inputValues, "", new VolleyLib.NResultListener<JSONObject>() {
            @Override
            public void onResult(int resultCode, JSONObject tResult)
            {
                mEmployeeModelList = new ArrayList<>();
                Iterator<String> keysItr = tResult.keys();
                while (keysItr.hasNext()) {
                    String key = keysItr.next();
                    Object value = null;
                    try {
                        value = tResult.get(key);
                        JSONObject jsonObject = new JSONObject(value.toString());
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        for (int i = 0; i<jsonArray.length(); i++)
                        {
                            JSONArray innerArray = jsonArray.getJSONArray(i);
                            EmployeeModel employeeModel = new EmployeeModel();
                            for (int j = 0; j<innerArray.length(); j++)
                            {
                                if(j==0)
                                    employeeModel.setmName(innerArray.get(j).toString());
                                else if(j==1)
                                    employeeModel.setmDesignation(innerArray.get(j).toString());
                                else if(j==2)
                                    employeeModel.setmPlace(innerArray.get(j).toString());
                                else if(j==3)
                                    employeeModel.setmEmpId(innerArray.get(j).toString());
                                else if(j==4)
                                    employeeModel.setmDOJ(innerArray.get(j).toString());
                                else if(j==5)
                                    employeeModel.setmSalary(innerArray.get(j).toString());
                            }
                            mEmployeeModelList.add(employeeModel);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mListingView.hideProgressBar();
                mListingView.updateEmployeeDetails(mEmployeeModelList);
                mListingView.getOriginalData(mEmployeeModelList);
            }

            @Override
            public void onFailure(int mError) {
                mListingView.APIFailureMessage();
            }
        });

    }
}
