package com.example.employee.Login;

import android.app.ProgressDialog;
import android.view.View;

public interface MainView {
    void doLogin(View view);

    void showError(String error, ProgressDialog progressDialog);
    void showError(String error);

    void goToMainPage(ProgressDialog progressDialog);

}
