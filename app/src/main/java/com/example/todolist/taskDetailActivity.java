package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class taskDetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";
    public Button removeButton;
    public Button doneButton;
    public TextView taskName, taskDate, weekday;
    private TextView DatePicker; // Credit: https://www.youtube.com/watch?v=hwe1abDO2Ag
    private DatePickerDialog.OnDateSetListener DatePickerListener; // Credit: https://www.youtube.com/watch?v=hwe1abDO2Ag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        DatePicker = findViewById(R.id.addDate);

        removeButton = findViewById(R.id.removeButton);
        doneButton = findViewById(R.id.doneButton);

        getTaskInfo();
        Done(this);
        removeTask(this);
        showDatePicker();
        setDatePicker();
    }

    // Date Picker methods
    private void showDatePicker(){
        // Credit: https://www.youtube.com/watch?v=hwe1abDO2Ag
        DatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(taskDetailActivity.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        DatePickerListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }
    private void setDatePicker(){
        // Credit: https://www.youtube.com/watch?v=hwe1abDO2Ag
        DatePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyyy" + month + "/" + dayOfMonth + "/" + year);
                String date = month  + "/" + dayOfMonth + "/" + year;
                DatePicker.setText(date);
            }
        };
    }

    private void Done(final Context context) {
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = taskName.getText().toString();
                String dueDate = taskDate.getText().toString();
                String weekdayChoice = weekday.getText().toString();
                Intent toMainAct = new Intent(getApplicationContext(), MainActivity.class);
                if (name.length() == 0){
                    Toast.makeText(context, "Please enter Task's Name or Remove it", Toast.LENGTH_SHORT).show();
                }else{
                    if (dueDate.length() == 0){
                        dueDate = "None";
                    }
                    if (weekdayChoice.length() == 0){
                        weekdayChoice = "None";
                    }
                    String data = name + "@" + dueDate + "@" + weekdayChoice;
                    if (getIntent().hasExtra("taskPosition")){
                        int position = getIntent().getExtras().getInt("taskPosition");
                        MainActivity.items.set(position, data);
                        FileHelper.writeData(MainActivity.items, context);
                    }
                    startActivity(toMainAct);
                }

            }
        });
    }

    private void getTaskInfo(){
        taskName = findViewById(R.id.taskName);
        taskDate = findViewById(R.id.taskDate);
        weekday = findViewById(R.id.weekday);
        if(getIntent().hasExtra("taskDetail")){
            String data = getIntent().getExtras().getString("taskDetail");
            List<String> taskInfo = Arrays.asList(data.split("@"));
            String name = taskInfo.get(0);
            String date = taskInfo.get(1);
            String weekdayChoice = taskInfo.get(2);
            taskName.setText(name);
            taskDate.setText(date);
            weekday.setText(weekdayChoice);
        }
    }


    private void removeTask(final Context context){
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMain = new Intent(getApplicationContext(), MainActivity.class);
                if(getIntent().hasExtra("taskPosition")){
                    int position = getIntent().getExtras().getInt("taskPosition");
                    MainActivity.items.remove(position);
                    FileHelper.writeData(MainActivity.items, context); // This line is to write the data into the internal memory, so that even when you close the app, data remains.
                    Toast.makeText(context, "Item Deleted", Toast.LENGTH_SHORT).show();
                }
                startActivity(goToMain);
            }
        });
    }
}
