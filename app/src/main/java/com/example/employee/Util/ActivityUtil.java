package com.example.employee.Util;

import android.content.Context;
import android.content.Intent;

import com.example.employee.EmployeeDetails.EmployeeDetailsActivity;


public class ActivityUtil {
    private Context context;


    public ActivityUtil (Context context){
        this.context = context;

    }
    public void startMainActivity() {
        context.startActivity(new Intent(context, EmployeeDetailsActivity.class));
    }
}
