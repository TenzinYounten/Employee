package com.example.employee.Login;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.employee.R;
import com.example.employee.Util.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.user) EditText userText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.error) TextView errorText;
    MainPresenter mainPresenter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenter(this, new MainService());
    }

    public void onClick(View view) {
        mainPresenter.onClick(view);
    }

    @Override
    public void doLogin(View view) {
        String user = userText.getText().toString();
        String password = _passwordText.getText().toString();
        if(mainPresenter.validate(user, password)) {
            progressDialog = new ProgressDialog(MainActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Authenticating...");
            progressDialog.show();
            mainPresenter.login(user,password,progressDialog);
        }

    }

    @Override
    public void showError(String error, ProgressDialog progressDialog) {
        progressDialog.dismiss();
        errorText.setText(""+error);

    }

    @Override
    public void showError(String error) {
        errorText.setText(""+error);
    }

    @Override
    public void goToMainPage(ProgressDialog progressDialog) {
        progressDialog.dismiss();
        new ActivityUtil(this).startMainActivity();
    }
}
