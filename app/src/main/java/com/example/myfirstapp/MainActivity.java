package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submit (View view) {
        Intent intent = new Intent(this, DisplayDataActivity.class);

        EditText firstNameText = (EditText) findViewById(R.id.firstNameText);
        EditText lastNameText = (EditText) findViewById(R.id.lastNameText);
        RadioGroup genderRadioGroup = (RadioGroup) findViewById(R.id.gender);
        DatePicker dateOfBirthPicker = (DatePicker) findViewById(R.id.dateOfBirth);
        EditText emailText = (EditText) findViewById(R.id.emailText);
        EditText passwordText = (EditText) findViewById(R.id.passwordText);

        String firstName = firstNameText.getText().toString();
        String lastName = lastNameText.getText().toString();
        String gender = "";
        if (genderRadioGroup.getCheckedRadioButtonId() == R.id.radioButtonMale) gender = "Male";
        else gender = "Female";

        Integer dateOfBirthDay = dateOfBirthPicker.getDayOfMonth();
        //add one to month because getMonth returns a zero based value and calendar is one based
        Integer dateOfBirthMonth = dateOfBirthPicker.getMonth() + 1;
        Integer dateOfBirthYear = dateOfBirthPicker.getYear();
        String dateOfBirth;

        if (dateOfBirthMonth < 10 && dateOfBirthDay < 10) { //Both month and day need a leading zero
            dateOfBirth = "0" + dateOfBirthMonth.toString() + "/0" + dateOfBirthDay.toString() + "/" +
                    dateOfBirthYear.toString();
        } else if (dateOfBirthMonth < 10) { //only the month needs a leading zero
            dateOfBirth = "0" + dateOfBirthMonth.toString() + "/" + dateOfBirthDay.toString() + "/" +
                    dateOfBirthYear.toString();
        } else if (dateOfBirthDay < 10) { //only day of month needs a leading zero
            dateOfBirth = dateOfBirthMonth.toString() + "/0" + dateOfBirthDay.toString() + "/" +
                    dateOfBirthYear.toString();
        } else { //no leading zeros need to be prepended
            dateOfBirth = dateOfBirthMonth.toString() + "/" + dateOfBirthDay.toString() + "/" +
                    dateOfBirthYear.toString();
        }

//        StringBuilder dateOfBirthDayStr = new StringBuilder();
//        StringBuilder dateOfBirthMonthStr = new StringBuilder();
//        StringBuilder dateOfBirthYearStr = new StringBuilder();
//
//        if (dateOfBirthDay < 10) dateOfBirthDayStr.append("0" + dateOfBirthDay);
//        else dateOfBirthDayStr.append(dateOfBirthDay.toString());
//
//        if (dateOfBirthMonth < 10) dateOfBirthMonthStr.append("0" + dateOfBirthMonth);
//        else dateOfBirthMonthStr.append(dateOfBirthMonth.toString());
//
//        StringBuilder dateOfBirthStr = new StringBuilder();
//        dateOfBirthStr.append(dateOfBirthMonthStr.toString() + dateOfBirthDayStr.toString() + dateOfBirthYearStr.toString());

        String emailAddress = emailText.getText().toString();
        String password = passwordText.getText().toString();

        CustomerDbHelper dbHelper = new CustomerDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete("customer",null,null);
        System.out.println("number of rows deleted in main activity " + rows);
        ContentValues values = new ContentValues();
        values.put("email", emailAddress);
        values.put("password", password);
        values.put("firstName", firstName);
        values.put("lastName", lastName);
        values.put("gender", gender);
        values.put("dateOfBirth", dateOfBirth);

        long row = db.insert("customer", null, values);
        //Start the DisplayDataActivity
        startActivity(intent);
    }
}
