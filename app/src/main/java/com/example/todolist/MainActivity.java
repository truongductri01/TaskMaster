package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static ArrayList<String> items;
    private List<Tasks> tasksList;
    private ListView itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons
        Button addBtn = findViewById(R.id.addBtn);
        Button creditBtn = findViewById(R.id.creditBtn);
        Button weekdaysBtn = findViewById(R.id.weekdaysBtn);

        // Adapter and ListView
        itemsList = findViewById(R.id.items_list);
        items = FileHelper.readData(this);
        setAdapter();

        // ListView onItem Click
        itemsList.setOnItemClickListener(this);

        // Button onClick Listener
        addBtn.setOnClickListener(this);
        creditBtn.setOnClickListener(this);
        weekdaysBtn.setOnClickListener(this);

        String [] quotes = getResources().getStringArray(R.array.inspirational_quotes);

        TextView quoteDisplay = findViewById(R.id.quoteDisplay);
        int randomIndex = new Random().nextInt(quotes.length);
        String randomName = quotes[randomIndex];
        quoteDisplay.setText(randomName);
    }

    public void setAdapter() {
        tasksList = new ArrayList<>();
        for (String string : items) {
            List<String> taskInfo = Arrays.asList(string.split("@"));
            String name = taskInfo.get(0);
            String due = taskInfo.get(1);
            String weekday = taskInfo.get(2);
            String time = taskInfo.get(3);

            Tasks tasks = new Tasks(name, due, weekday, time);
            tasksList.add(tasks);
        }
        TaskInfoAdapter taskInfoAdapter = new TaskInfoAdapter(MainActivity.this, tasksList);
        itemsList.setAdapter(taskInfoAdapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addBtn:
                Intent addActivity = new Intent(getApplicationContext(), addActivity.class);
                startActivity(addActivity);
                break;
            case R.id.creditBtn:
                Intent creditActivity = new Intent(getApplicationContext(), com.example.todolist.creditActivity.class);
                startActivity(creditActivity);
                break;
            case R.id.weekdaysBtn:
                Intent weekdaysActivity = new Intent(getApplicationContext(), weekdaysActivity.class);
                startActivity(weekdaysActivity);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String data = items.get(position);
        Intent toDetailAct = new Intent(getApplicationContext(), taskDetailActivity.class);
        toDetailAct.putExtra("taskDetail", data);
        toDetailAct.putExtra("taskPosition", position);
        startActivity(toDetailAct);
    }
}
