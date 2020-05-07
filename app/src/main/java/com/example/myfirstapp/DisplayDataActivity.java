package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        try {
            TextView firstNameText = (TextView) findViewById(R.id.firstNameText2);
            TextView lastNameText = (TextView) findViewById(R.id.lastNameText2);
            TextView genderText = (TextView) findViewById(R.id.genderText2);
            TextView dateOfBirthText = (TextView) findViewById(R.id.dateOfBirthText2);
            TextView emailText = (TextView) findViewById(R.id.emailText2);

            CustomerDbHelper dbHelper = new CustomerDbHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String projection[] = {"firstName", "lastName", "gender", "dateOfBirth", "email"};
            Cursor c = db.query("customer", projection, null, null, null, null, null);
            c.moveToPosition(0); //move to first row in the cursor, index 0, cursor contains the results of query
            String firstName = c.getString(0); //index 0 is first column
            String lastName = c.getString(1);
            String gender = c.getString(2);
            String dateOfBirth = c.getString(3);
            String email = c.getString(4);
            c.close();

            firstNameText.setText(firstName);
            lastNameText.setText(lastName);
            genderText.setText(gender);
            dateOfBirthText.setText(dateOfBirth);
            emailText.setText(email);
        } catch(Exception e)
        {
            System.out.println("Exception " + e);
        }
    }
}
