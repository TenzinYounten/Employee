package com.example.employee.Login;

import android.app.ProgressDialog;
import android.util.Log;

import com.example.employee.Model.Employee;
import com.example.employee.Model.LoginModel;
import com.example.employee.Model.LoginResponse;
import com.example.employee.Util.EndPointInterface;
import com.example.employee.weatherEndPointInterface.NetworkClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainService {
    Realm realm;

    public void doLogin(String user, String password, MainPresenter mainPresenter, ProgressDialog progressDialog) {
        Retrofit retrofit = NetworkClient.getRetrofit();

        EndPointInterface endPointInterface = retrofit.create(EndPointInterface.class);
        LoginModel loginData = new LoginModel(user, password);
        Call<LoginResponse> call = endPointInterface.doLogin(loginData);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse body = response.body();


                if (response.isSuccessful()) {
                    JSONArray jsonElements = getJson(body.getTABLEDATA());
                    saveData(jsonElements);
                    Log.e("Data", "" + body.getTABLEDATA());
                    mainPresenter.successfulLogin(progressDialog);
                } else {
                    mainPresenter.unSuccessfulLogin(progressDialog);
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("ERROR", "" + t.getMessage());
                Log.e("ERROR", "" + t.getStackTrace());
                Log.e("ERROR", "" + t.getLocalizedMessage());
                mainPresenter.unSuccessfulLogin(progressDialog);
            }
        });

    }

    private void saveData(JSONArray jsonElements) {
        realm = Realm.getDefaultInstance();

        RealmList<Employee> employees = new RealmList<Employee>();
        Employee employee = new Employee();
        for (int i = 0; i < jsonElements.length(); i++) {
            try {
                RealmList<String> stringList = new RealmList<String>();
                JSONArray jsonArray = jsonElements.getJSONArray(i);
                Log.e("inner json array", "" + jsonArray.toString());
                for (int j = 0; j < jsonArray.length(); j++) {
                    String s = jsonArray.getString(j);
                    stringList.add(s);
                }
                employee = new Employee(stringList);
                employees.add(employee);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        realm.executeTransaction(r -> r.copyToRealmOrUpdate(employees));
        realm.close();
    }

    private JSONArray getJson(String tabledata) {
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        try {
            jsonObject = new JSONObject(tabledata);
            jsonArray = jsonObject.getJSONArray("data");
            Log.e("JSON ARRAY", "" + jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
