package com.example.employee.EmployeeCharts;

import com.example.employee.Model.Employee;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

class EmployeeChartPresenter {
   EmployeeChartView employeeChartView;

    public EmployeeChartPresenter(EmployeeChartView employeeChartView) {
        this.employeeChartView = employeeChartView;
    }

    public void getBarData(EmployeeChartActivity employeeChartActivity) {
      /*  RealmResults<Employee> results;
        List<Employee> finalEmployees;
        Realm.init(employeeChartActivity);
        Realm realm = Realm.getDefaultInstance();
        results = realm.where(Employee.class).findAllAsync();
        results.load();*/
        List<Employee> employees = new ArrayList<Employee>();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Employee>results =
                realm.where(Employee.class)
                        .findAll().sort("salary", Sort.DESCENDING);

        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setDate(results.get(i).getDate());
            employee.setId(results.get(i).getId());
            employee.setName(results.get(i).getName());
            employee.setPlace(results.get(i).getPlace());
            employee.setRole(results.get(i).getRole());
            employee.setSalary(results.get(i).getSalary());
            employees.add(employee);
        }
        employeeChartView.displayChart(employees);
    }
}
