package com.example.employee.EmployeeDetails;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.employee.Login.MainActivity;
import com.example.employee.Model.Employee;
import com.example.employee.R;
import com.example.employee.Util.ActivityUtil;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class EmployeeDetailsActivity extends AppCompatActivity implements EmployeeDetailsView, DetailsAdapter.ItemClickListener{
    EmployeeDetailsPresenter employeeDetailsPresenter;
    DetailsAdapter detailsAdapter;

    RealmResults<Employee> results;
    List<Employee> finalEmployee;
    SearchView searchView;
    RecyclerView recyclerView;
    CardView cardView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.employee_details_activity);
        super.onCreate(savedInstanceState);
        recyclerView = (RecyclerView) findViewById(R.id.employeeList);
        cardView = (CardView) findViewById(R.id.cardView);
        employeeDetailsPresenter = new EmployeeDetailsPresenter( new EmployeeDetailsService(), this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        employeeDetailsPresenter.getEmployeeDetails(this);
        super.onResume();
    }

    public void onActionClicked(View view) {
            employeeDetailsPresenter.onFabClick();
    }

    @Override
    public void onClick(View view, int position) {
        Employee employee = finalEmployee.get(position);
        new ActivityUtil(this).startDetailActivity(employee);
    }

    @Override
    public void onFabClicked() {
        new ActivityUtil(this).startGraphActivity();
    }

    @Override
    public void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                detailsAdapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    @Override
    public void displayEmployees(List<Employee> finalEmployees) {
        finalEmployee = finalEmployees;
        detailsAdapter = new DetailsAdapter(finalEmployees, getApplicationContext());
        detailsAdapter.setClickListener(this);
        recyclerView.setAdapter(detailsAdapter);
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search City ");
        employeeDetailsPresenter.search(searchView);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the com.example.weathercheck.WeatherCheck.Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
