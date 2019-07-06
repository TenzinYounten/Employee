package com.example.employee.Model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Employee extends RealmObject {
    String name;
    String role;
    String place;
    String id;
    String date;
    String salary;

    public Employee() {
    }

    public Employee(RealmList<String> stringList) {
        this.name = stringList.get(0);
        this.role = stringList.get(1);
        this.place = stringList.get(2);
        this.id = stringList.get(3);
        this.date = stringList.get(4);
        this.salary = stringList.get(5).replaceAll("$","");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
