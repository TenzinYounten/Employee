package com.example.employee.EmployeeView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employee.BuildConfig;
import com.example.employee.Model.Employee;
import com.example.employee.R;
import com.example.employee.Util.ActivityUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeeViewActivity extends AppCompatActivity implements EmployeeView {
    EmployeeViewPresenter employeeViewPresenter;
    Employee employee;
    String employeeName;
    protected static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 0;
    Uri file;
    Uri imageUri;

    @BindView(R.id.nameTxt)
    TextView name;
    @BindView(R.id.placeTxt)
    TextView place;
    @BindView(R.id.salaryTxt)
    TextView salary;
    @BindView(R.id.dateTxt)
    TextView date;
    @BindView(R.id.roleTxt)
    TextView role;
    @BindView(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_view_activity);
        ButterKnife.bind(this);
        employeeViewPresenter = new EmployeeViewPresenter(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        employeeName = intent.getStringExtra("employee");
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    @Override
    protected void onResume() {
        employee = employeeViewPresenter.getEmployeeData(this, employeeName);
        employeeViewPresenter.setName(name);
        employeeViewPresenter.setPlace(place);
        employeeViewPresenter.setRole(role);
        employeeViewPresenter.setSalary(salary);
        employeeViewPresenter.setDate(date);

        super.onResume();
    }

    public void onActionClicked(View view) {
        employeeViewPresenter.onFabClicked(view);
    }

    @Override
    public void onFabClicked(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        file = Uri.fromFile(getOutputMediaFile());
        file = FileProvider.getUriForFile(EmployeeViewActivity.this, BuildConfig.APPLICATION_ID + ".provider",getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Employee");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }
        return new File(mediaStorageDir.getPath() + File.separator +
                employee.getName() + ".jpg");
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT);
            }

        }

    }

    @Override
    public void setName(TextView name) {
        name.setText(employee.getName());
    }

    @Override
    public void setPlace(TextView place) {
        place.setText(employee.getPlace());
    }

    @Override
    public void setRole(TextView role) {
        role.setText(employee.getRole());
    }

    @Override
    public void setSalary(TextView salary) {
        salary.setText(""+employee.getSalary());
    }

    @Override
    public void setDate(TextView date) {
        date.setText(employee.getDate());
    }
}
