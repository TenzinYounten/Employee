package com.example.employee.EmployeeDetails;

import android.support.v7.widget.SearchView;

import com.example.employee.Model.Employee;

import java.util.List;

interface EmployeeDetailsView {
    void onFabClicked();

    void search(SearchView searchView);

    void displayEmployees(List<Employee> finalEmployees);
}
