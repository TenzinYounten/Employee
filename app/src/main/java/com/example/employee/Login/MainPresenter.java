package com.example.employee.Login;

import android.app.ProgressDialog;
import android.view.View;

public class MainPresenter {
    private MainView mainView;
    private MainService mainService;
    public MainPresenter(MainView mainView, MainService mainService) {
        this.mainView = mainView;
        this.mainService = mainService;
    }

    public void onClick(View view) {
        mainView.doLogin(view);

    }

    public boolean validate(String user, String password) {
        String error = "";
        boolean login = true;
        if(user.isEmpty()) {
            error = "Please enter user details";
            login = false;
            mainView.showError(error);
        } else if(password.isEmpty()) {
            error = "Please enter password";
            login = false;
            mainView.showError(error);
        }
        return login;


    }

    public void login(String user, String password, ProgressDialog progressDialog, String firstLogin) {
        mainService.doLogin(user, password, this, progressDialog,firstLogin);
      /*  if(b) {
            mainView.goToMainPage();
        } else {
            mainView.showError("Login Failed");
        }*/



    }

    public void successfulLogin(ProgressDialog progressDialog) {
        mainView.goToMainPage(progressDialog);
    }

    public void unSuccessfulLogin(ProgressDialog progressDialog) {
        mainView.showError("Login Failed", progressDialog);
    }
}
