package com.example.mypc.patientapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        t1 = (TextView) findViewById(R.id.textView3);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        String n = b.getString("name_key");
        String p = b.getString("phone_key");
        String date = b.getString("date_key");
        String time = b.getString("time_key");
        int num = b.getInt("num_key");
        int t = b.getInt("token_key");

        t1.setText("Token no. = "+t+"\nHi!  "+n+"\nYour Phone no. = "+p+"\nNo. of Patients = "+num+"\nDate of Appointment= "+date+"\nTime of Appointment= "+time);

    }
}
