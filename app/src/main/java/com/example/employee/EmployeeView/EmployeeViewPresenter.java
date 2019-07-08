package com.example.employee.EmployeeView;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.employee.Model.Employee;

import io.realm.Realm;

public class EmployeeViewPresenter {
    EmployeeView employeeView;

    public EmployeeViewPresenter(EmployeeView employeeView) {
        this.employeeView = employeeView;
    }

    public void onFabClicked(View view) {
        employeeView.onFabClicked(view);

    }

    public Employee getEmployeeData(EmployeeViewActivity employeeViewActivity, String employeeName) {
        Realm realm = null;
        boolean state = true;
        Employee employee = null;
        Realm.init(employeeViewActivity);
        try {
            realm = Realm.getDefaultInstance();
            employee = realm.where(Employee.class).equalTo("name", employeeName).findFirst();
            if (employee != null) {
                Employee employee1 = new Employee();
                employee1.setName(employee.getName());
                employee1.setPlace(employee.getPlace());
                employee1.setRole(employee.getRole());
                employee1.setDate(employee.getDate());
                employee1.setSalary(employee.getSalary());
                employee1.setUri(employee.getUri());
                return employee1;
            }
        } catch (Exception e) {
            state = false;
            Log.e("Exception", "" + e.toString());
        }
        return employee;

    }

    public void setName(TextView name) {
        employeeView.setName(name);
    }

    public void setPlace(TextView place) {
        employeeView.setPlace(place);
    }

    public void setRole(TextView role) {
        employeeView.setRole(role);
    }

    public void setSalary(TextView salary) {
        employeeView.setSalary(salary);
    }

    public void setDate(TextView date) {
        employeeView.setDate(date);
    }

    public void saveImageFile(Uri file, String employeeName, EmployeeViewActivity employeeViewActivity) {
        Realm realm = null;
        final Employee employee;
        Employee employee1;
//        Realm.init(employeeViewActivity);

        realm = Realm.getDefaultInstance();

        try {
            realm.beginTransaction();
            employee = realm.where(Employee.class).equalTo("name", employeeName).findFirst();
            employee.setUri(file.getPath());
            realm.commitTransaction();


           /* employee = realm.where(Employee.class).equalTo("name", employeeName).findFirst();
            realm.executeTransaction(new Realm.Transaction() {
                @Override    public void execute(Realm realm) {
                    try {
                        employee.setUri(file.getPath());
                        // the listObject has contain updated data in the List
                        realm.copyToRealmOrUpdate(employee);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });*/
            employee1 = realm.where(Employee.class).equalTo("name", employeeName).findFirst();
            employee1 = realm.where(Employee.class).equalTo("name", employeeName).findFirst();

        } catch (Exception e) {

            Log.e("Exception", "" + e.toString());
        }
    }

    public void setImage(ImageView imageView, String uri) {
        employeeView.setImageView(imageView, uri);
    }
}
