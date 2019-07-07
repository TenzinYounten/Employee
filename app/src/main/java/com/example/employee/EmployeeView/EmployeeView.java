package com.example.employee.EmployeeView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

interface EmployeeView {
    void onFabClicked(View view);

    void setName(TextView name);

    void setPlace(TextView place);

    void setRole(TextView role);

    void setSalary(TextView salary);

    void setDate(TextView date);

    void setImageView(ImageView imageView, String uri);
}
