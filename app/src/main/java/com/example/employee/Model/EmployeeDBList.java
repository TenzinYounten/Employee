package com.example.employee.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

public class EmployeeDBList extends RealmObject {


    private RealmList<String> data = null;

    public RealmList<String> getData() {
        return data;
    }

    public void setData(RealmList<String> data) {
        this.data = data;
    }
}
