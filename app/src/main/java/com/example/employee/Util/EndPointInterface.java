package com.example.employee.Util;

import com.example.employee.Model.LoginModel;
import com.example.employee.Model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface EndPointInterface {

    @POST("gettabledata.php")
    Call<LoginResponse> doLogin(@Body LoginModel loginData);


}
