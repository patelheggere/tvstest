package com.patelheggere.tvstest.login;

import android.content.Context;

import com.android.volley.Request;
import com.patelheggere.tvstest.network.volley.VolleyLib;

import org.json.JSONObject;

import java.util.HashMap;

import static com.patelheggere.tvstest.constants.StringConstants.BASE_URL;
import static com.patelheggere.tvstest.constants.StringConstants.GET_EMP_DATA;
import static com.patelheggere.tvstest.constants.StringConstants.PASSWORD;
import static com.patelheggere.tvstest.constants.StringConstants.USERNAME;

public class LoginPresenter {
    LoginView mLoginView;
    Context mContext;

    public LoginPresenter(Context context, LoginView mLoginView) {
        this.mLoginView = mLoginView;
        this.mContext = context;
    }
    public void validateCredentials(String userName, String password){
        mLoginView.showProgressBar();
        HashMap<String, String> inputValues = new HashMap<>();
        inputValues.put(USERNAME, userName);
        inputValues.put(PASSWORD, password);
        VolleyLib.jsonObjectRequest(mContext, Request.Method.POST, BASE_URL + GET_EMP_DATA, inputValues, "", new VolleyLib.NResultListener<JSONObject>() {
            @Override
            public void onResult(int resultCode, JSONObject tResult) {
                mLoginView.hideProgressBar();
                mLoginView.loginSuccess();
            }

            @Override
            public void onFailure(int mError) {
                mLoginView.hideProgressBar();
                mLoginView.invalidCredentials();
            }
        });
    }
}
