package com.example.employee.EmployeeDetails;

import android.support.v7.widget.SearchView;

import com.example.employee.Model.Employee;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

class EmployeeDetailsPresenter {
    EmployeeDetailsService employeeDetailsService;
    EmployeeDetailsView employeeDetailsView;

    public EmployeeDetailsPresenter(EmployeeDetailsService employeeDetailsService, EmployeeDetailsView employeeDetailsView) {
        this.employeeDetailsService = employeeDetailsService;
        this.employeeDetailsView = employeeDetailsView;
    }

    public void onFabClick() {
        employeeDetailsView.onFabClicked();
    }

    public void search(SearchView searchView) {
        employeeDetailsView.search(searchView);
    }

    public void getEmployeeDetails(EmployeeDetailsActivity employeeDetailsActivity) {
        RealmResults<Employee> results;
        List<Employee> finalEmployees;
        Realm.init(employeeDetailsActivity);
        Realm realm = Realm.getDefaultInstance();
        results = realm.where(Employee.class).findAllAsync();
        results.load();
        finalEmployees = new ArrayList<Employee>();
        for(Employee employee : results) {
            Employee tempEmployee = new Employee();
            tempEmployee.setSalary(employee.getSalary());
            tempEmployee.setRole(employee.getRole());
            tempEmployee.setPlace(employee.getRole());
            tempEmployee.setName(employee.getName());
            tempEmployee.setId(employee.getId());
            tempEmployee.setDate(employee.getDate());
            finalEmployees.add(tempEmployee);
        }
        employeeDetailsView.displayEmployees(finalEmployees);
    }
}
