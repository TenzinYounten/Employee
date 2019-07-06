package com.example.employee.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class EmployeeDB extends RealmObject {
    @SerializedName("data")
    @Expose
    private RealmList<EmployeeDBList> data = null;

    public RealmList<EmployeeDBList> getData() {
        return data;
    }

    public void setData(RealmList<EmployeeDBList> data) {
        this.data = data;
    }
}
