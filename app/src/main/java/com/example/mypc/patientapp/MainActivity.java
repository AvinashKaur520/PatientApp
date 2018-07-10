package com.example.mypc.patientapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.Serializable;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    TextView t1,t2,t3;
    RadioButton r1,r2;
    Button b1,b2,b3;
    String name,phone,date1,time1;
    int num=0,token=0;

    AlertDialog.Builder ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        t1 = (TextView) findViewById(R.id.textView);
        t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView4);
        r1 = (RadioButton) findViewById(R.id.radioButton);
        r2 = (RadioButton) findViewById(R.id.radioButton2);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);

        t3.setText(Integer.toString(num));

        e3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar cal = Calendar.getInstance();

                new DatePickerDialog(MainActivity.this,dateListener,cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        e4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Calendar cal = Calendar.getInstance();
                new TimePickerDialog(MainActivity.this,time,cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),true).show();
            }
        });

        b3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    name = e1.getText().toString();
                    phone = e2.getText().toString();
                    date1 = e3.getText().toString();
                    time1 = e4.getText().toString();

                if(b3.isClickable())
                {
                    if(token==0 || token<=30)
                    {
                        token = token + 1;
                    }
                }

                ab = new AlertDialog.Builder(MainActivity.this);
                ab.setTitle("Conformation");
                ab.setMessage("Are You Sure You Want An Appointment ?");
                ab.setIcon(R.mipmap.warning);
                ab.setCancelable(false);

                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                        if(r1.isChecked())
                        {
                            String s1 = r1.getText().toString();
                            s1 = "Mr. "+name;
                            intent.putExtra("name_key",s1);
                        }
                        else
                        {
                            String s2 = r2.getText().toString();
                            s2 = "Miss "+name;
                            intent.putExtra("name_key",s2);
                        }

                        intent.putExtra("phone_key",phone);
                        intent.putExtra("num_key",num);
                        intent.putExtra("token_key",token);
                        intent.putExtra("date_key",date1);
                        intent.putExtra("time_key",time1);
                        startActivity(intent);
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                    }
                });
                ab.show();
            }
        });

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    if(b1.isClickable())
                    {
                        if(num==0 || num<=2)
                        {
                            num=num+1;
                            t3.setText(Integer.toString(num));
                        }
                        if(num>2)
                        {
                            ab = new AlertDialog.Builder(MainActivity.this);
                            ab.setTitle("Alert");
                            ab.setMessage("Sorry , number of patients should not be more than 3");
                            ab.setIcon(R.mipmap.alert);
                            ab.setCancelable(false);

                            ab.setPositiveButton("OK", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    b1.setEnabled(false);
                                }
                            });
                            ab.show();
                        }

                    }
            }
        });

        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (b2.isClickable())
                {
                    if(num>0)
                    {
                        num=num-1;
                        t3.setText(Integer.toString(num));
                        b1.setEnabled(true);
                    }

                }
            }
        });
    }


    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener()
    {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
        {
            e3.setText(dayOfMonth+"/"+(month+1)+"/"+year);
        }
    };

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            e4.setText(hourOfDay+" : "+minute);
        }
    };


}
