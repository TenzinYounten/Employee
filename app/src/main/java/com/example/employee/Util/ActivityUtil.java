package com.example.employee.Util;

import android.content.Context;
import android.content.Intent;

import com.example.employee.EmployeeCharts.EmployeeChartActivity;
import com.example.employee.EmployeeDetails.EmployeeDetailsActivity;
import com.example.employee.EmployeeView.EmployeeViewActivity;


public class ActivityUtil {
    private Context context;


    public ActivityUtil (Context context){
        this.context = context;

    }
    public void startMainActivity() {
        context.startActivity(new Intent(context, EmployeeDetailsActivity.class));
    }

    public void startDetailActivity() {
        context.startActivity(new Intent(context, EmployeeViewActivity.class));
    }

    public void startGraphActivity() {
        context.startActivity(new Intent(context, EmployeeChartActivity.class));

    }
}
