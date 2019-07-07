package com.example.employee.EmployeeCharts;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.employee.Model.Employee;
import com.example.employee.R;
import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.XAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;


import java.util.ArrayList;
import java.util.List;

public class EmployeeChartActivity extends AppCompatActivity implements EmployeeChartView {
    EmployeeChartPresenter employeeChartPresenter;
    HorizontalBarChart chart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_activity);
        employeeChartPresenter = new EmployeeChartPresenter(this);
        chart = (HorizontalBarChart) findViewById(R.id.bar_chart);
        employeeChartPresenter.getBarData(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void displayChart(List<Employee> employees) {

        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for(int i = 0; i<employees.size(); i++) {
            String label = employees.get(i).getName();
            float entry = (float)employees.get(i).getSalary();
            labels.add(label);
            entries.add(new BarEntry(entry, i));
        }

        BarDataSet barDataSet = new BarDataSet(entries, "");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(labels, barDataSet);
        chart.setData(barData);
        chart.animateY(2000);
        chart.setDescription("Employee Top Ten Salaries");

       /* BarDataSet barDataSet = new BarDataSet(entries, "Cells");
        BarData barData = new BarData(labels, barDataSet);
        chart.animateY(2000);
        chart.setDescription("Employee Top Ten Salaries");
        *//*barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.animateY(2000);
        XAxis xAxis = chart.getXAxis();

        xAxis.setValueFormatter(new XAxisValueFormatter() {
            @Override
            public String getXValue(String original, int index, ViewPortHandler viewPortHandler) {
                return labels.get(index);
            }
        });

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);*//*
        chart.setData(barData);
*/

        


    }
}
