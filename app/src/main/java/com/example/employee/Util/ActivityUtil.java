package com.example.employee.Util;

import android.content.Context;
import android.content.Intent;

import com.example.employee.EmployeeCharts.EmployeeChartActivity;
import com.example.employee.EmployeeDetails.EmployeeDetailsActivity;
import com.example.employee.EmployeeView.EmployeeViewActivity;
import com.example.employee.Model.Employee;


public class ActivityUtil {
    private Context context;


    public ActivityUtil (Context context){
        this.context = context;

    }
    public void startMainActivity() {
        context.startActivity(new Intent(context, EmployeeDetailsActivity.class));
    }

    public void startDetailActivity(Employee employee) {
        Intent intent = new Intent(context, EmployeeViewActivity.class);
        intent.putExtra("employee", employee.getName());
        context.startActivity(intent);
    }

    public void startGraphActivity() {
        context.startActivity(new Intent(context, EmployeeChartActivity.class));

    }

    public void startCameraActivity() {


    }
}
