package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class weekdaysActivity extends AppCompatActivity implements View.OnClickListener {
    private Button monday;
    private  Button tuesday;
    private Button Wednesday;
    private  Button thursday;
    private  Button friday;
    private  Button saturday;
    private  Button sunday;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekdays);

        // Buttons
        Button backBtn = findViewById(R.id.backBtn);
        monday = findViewById(R.id.mondayBtn);
        tuesday = findViewById(R.id.tuesdayBtn);
        Wednesday = findViewById(R.id.wednesdayBtn);
        thursday = findViewById(R.id.thursdayBtn);
        friday = findViewById(R.id.fridayBtn);
        saturday = findViewById(R.id.saturdayBtn);
        sunday = findViewById(R.id.sundayBtn);

        backBtn.setOnClickListener(this);
        monday.setOnClickListener(this);
        tuesday.setOnClickListener(this);
        Wednesday.setOnClickListener(this);
        thursday.setOnClickListener(this);
        friday.setOnClickListener(this);
        saturday.setOnClickListener(this);
        sunday.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backBtn:
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                break;
            case R.id.mondayBtn:
                String data = monday.getText().toString();

                Intent mondayInfo = new Intent(getApplicationContext(), dailyInfoActivity.class);
                mondayInfo.putExtra("Monday", data);
                startActivity(mondayInfo);
                break;
            case R.id.tuesdayBtn:
                String data1 = tuesday.getText().toString();
                Intent tuesdayInfo = new Intent(getApplicationContext(), dailyInfoActivity.class);
                tuesdayInfo.putExtra("Monday", data1);
                startActivity(tuesdayInfo);
                break;

            case R.id.wednesdayBtn:
                String data2 = Wednesday.getText().toString();

                Intent wednesdayInfo = new Intent(getApplicationContext(), dailyInfoActivity.class);
                wednesdayInfo.putExtra("Wednesday",data2);
                startActivity(wednesdayInfo);
                break;
            case R.id.thursdayBtn:
                String data3 = thursday.getText().toString();
                Intent thursdayInfo = new Intent(getApplicationContext(), dailyInfoActivity.class);
                thursdayInfo.putExtra("Thursday",data3);
                startActivity(thursdayInfo);
                break;

            case R.id.fridayBtn:
                String data4 = friday.getText().toString();
                Intent fridayInfo = new Intent(getApplicationContext(),dailyInfoActivity.class);
                fridayInfo.putExtra("Friday",data4);
                startActivity(fridayInfo);
                break;

            case R.id.saturdayBtn:
                String data5 = saturday.getText().toString();
                Intent saturdayInfo = new Intent(getApplicationContext(), dailyInfoActivity.class);
                saturdayInfo.putExtra("Saturday",data5);
                startActivity(saturdayInfo);
                break;

            case R.id.sundayBtn:
                String data6 = sunday.getText().toString();
                Intent sundayInfo = new Intent(getApplicationContext(), dailyInfoActivity.class);
                sundayInfo.putExtra("Sunday",data6);
                startActivity(sundayInfo);
                break;

        }

    }
}
