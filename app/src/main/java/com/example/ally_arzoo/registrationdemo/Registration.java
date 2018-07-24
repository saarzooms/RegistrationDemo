package com.example.ally_arzoo.registrationdemo;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class Registration extends AppCompatActivity {
    EditText edtFullname, edtEmail,edtPassword, edtDOB, edtConfirmPassword, edtAddress,edtMobileNumber;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    Button btnSaveRegistration;
    Switch switchEmailSubscrib;
    CheckBox chkTerms;
    Spinner spinnerCity;
    RadioGroup rbtng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edtFullname = findViewById(R.id.edtFullname);
        edtMobileNumber = findViewById(R.id.edtMobileNumber);
        edtAddress = findViewById(R.id.edtAddress);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtEmail = findViewById(R.id.edtEmail);
        edtDOB = findViewById(R.id.edtDOB);
        btnSaveRegistration = findViewById(R.id.btnSaveRegistration);
        switchEmailSubscrib = findViewById(R.id.switchEmailSubscrib);
        chkTerms = findViewById(R.id.chkTerms);
        spinnerCity = findViewById(R.id.spinnerCity);
        rbtng = findViewById(R.id.rbtngGender);
        //spinner data set
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.city_name,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(adapter);
        //date of birth
        edtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_WEEK);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Registration.this,android.R.style.Theme_Holo_Dialog,mDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        //set date to edtDOB
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date = "";
                if(month>8){
                    date = day+"-"+(month+1)+"-"+year;
                }else{
                    date = day+"-0"+(month+1)+"-"+year;
                }
                edtDOB.setText(date);

            }
        };

        //btnSave Registration handler
        btnSaveRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String terms;
                String statusSwitch1;
                if(chkTerms.isChecked()){
                    terms = "Accepted";
                }
                else {
                    terms = "Not Accepted";
                    Toast.makeText(Registration.this, "Please accept terms", Toast.LENGTH_SHORT).show();
                }
                if (switchEmailSubscrib.isChecked())
                    statusSwitch1 = switchEmailSubscrib.getTextOn().toString();
                else
                    statusSwitch1 = switchEmailSubscrib.getTextOff().toString();
                int gender = rbtng.getCheckedRadioButtonId();
                RadioButton rbtn = findViewById(gender);
                Log.d("From save button", "Fullname: "+edtFullname.getText().toString());
                Log.d("From save button", "Email: "+edtEmail.getText().toString());
                Log.d("From save button", "Mobile: "+edtMobileNumber.getText().toString());
                Log.d("From save button", "DOB: "+edtDOB.getText().toString());
                Log.d("From save button", "Gender: "+rbtn.getText().toString());
                Log.d("From save button", "Address: "+edtAddress.getText().toString());
                Log.d("From save button", "Password: "+edtPassword.getText().toString());
                Log.d("From save button", "Confirm Password: "+edtConfirmPassword.getText().toString());
                Log.d("From save button", "isSubscribe: "+statusSwitch1);
                Log.d("From save button", "Terms: "+terms);

                Toast.makeText(Registration.this, "Printed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
