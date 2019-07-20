package com.patelheggere.tvstest.network.retrofit;



import com.patelheggere.tvstest.model.Root;
import com.patelheggere.tvstest.model.SignInRequestModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */


    @POST("reporting/vrm/api/test_new/int/gettabledata.php ")
    Call<Root> getData(@Body SignInRequestModel body);



}
