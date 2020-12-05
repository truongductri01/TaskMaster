package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dailyInfoActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView itemsList;
    public static ArrayList<String> items;
    private List<Tasks> tasksList;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_info);

        // Buttons:
        Button backBtn = findViewById(R.id.backBtn);

        // TextView
        TextView dateTV = findViewById(R.id.dateTV);
        text = getWeekday();
        dateTV.setText(text);



        backBtn.setOnClickListener(this);

        // Adapter and ListView
        itemsList = findViewById(R.id.listItems);
        items = FileHelper.readData(this);
        setAdapter();
        itemsList.setOnItemClickListener(this);
    }

    public String getWeekday(){
        if (getIntent().hasExtra("Monday")) {
            text = getIntent().getExtras().getString("Monday");
        }
        else if (getIntent().hasExtra("Tuesday")) {
            text = getIntent().getExtras().getString("Tuesday");
        }
        else if (getIntent().hasExtra("Wednesday")) {
            text = getIntent().getExtras().getString("Wednesday");
        }
        else if (getIntent().hasExtra("Thursday")) {
            text = getIntent().getExtras().getString("Thursday");
        }
        else if (getIntent().hasExtra("Friday")) {
            text = getIntent().getExtras().getString("Friday");
        }
        else if (getIntent().hasExtra("Saturday")) {
            text = getIntent().getExtras().getString("Saturday");
        }
        else if (getIntent().hasExtra("Sunday")) {
            text = getIntent().getExtras().getString("Sunday");
        }
        return text;
    }

    public void setAdapter() {
        tasksList = new ArrayList<>();
        for (String string : items) {
            List<String> taskInfo = Arrays.asList(string.split("@"));
            String name = taskInfo.get(0);
            String due = taskInfo.get(1);
            String weekday = taskInfo.get(2);
            String time = taskInfo.get(3);

            if (weekday.toUpperCase().equals(text.toUpperCase())) {
                Tasks tasks = new Tasks(name, due, weekday, time);
                tasksList.add(tasks);
            }
        }
        TaskInfoAdapter taskInfoAdapter = new TaskInfoAdapter(dailyInfoActivity.this, tasksList);
        itemsList.setAdapter(taskInfoAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                Intent weekdaysActivity = new Intent(getApplicationContext(), weekdaysActivity.class);
                startActivity(weekdaysActivity);
                break;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String data = items.get(position);
        Intent toDetailAct = new Intent(getApplicationContext(), taskDetailActivity.class);
        toDetailAct.putExtra("taskDetail", data);
        toDetailAct.putExtra("taskPosition", position);
        startActivity(toDetailAct);
    }
}
