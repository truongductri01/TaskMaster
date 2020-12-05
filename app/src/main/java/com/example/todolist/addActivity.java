package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;


// Idea: concatenate Task name and Due date

public class addActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    public EditText taskName, taskDate, weekday;
    private TextView timeTV;
    public Button submitBtn;
    public Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // TextView:
        taskName = findViewById(R.id.taskName);
        taskDate = findViewById(R.id.taskDate);
        weekday = findViewById(R.id.weekday);
        timeTV = findViewById(R.id.timeTV);
        timeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        // Buttons
        submitBtn = (Button) findViewById(R.id.submitBtn);
        cancelBtn = (Button) findViewById(R.id.cancelBtn);

        submitTask(this);
        cancel();
//        createNotificationChannel();
    }

    private void cancel(){
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMainAct = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(toMainAct);
            }
        });
    }
    public void submitTask(final Context context){
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = taskName.getText().toString();
                String dueDate = "None";
                String weekdayChoice = "None";
                String time = "None";
                if (name.length() == 0){
                    Toast.makeText(context, "Please enter Your Task's Name", Toast.LENGTH_SHORT).show();
                } else{
                    String dateDate = taskDate.getText().toString();
                    String weekdayData = weekday.getText().toString();
                    String timeData = timeTV.getText().toString();
                    if (!dateDate.equals("")){
                        dueDate = dateDate;
                    }
                    if (!weekdayData.equals("")){
                        weekdayChoice = weekdayData;
                    }
                    if (!timeData.equals("")){
                        time = timeData;
                    }
                    MainActivity.items.add(name+"@"+dueDate+"@"+weekdayChoice+"@"+time);
                    FileHelper.writeData(MainActivity.items, context);

                    // Add task to Adapter (ListView)
                    Toast.makeText(getBaseContext(), "Task added", Toast.LENGTH_LONG).show();



//                    Intent intent = new Intent(addActivity.this, NotificationActivity.class);
//                    PendingIntent pendingIntent =  PendingIntent.getBroadcast(addActivity.this, 0, intent, 0);
//
//                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//                    long timeAtButtonClick = System.currentTimeMillis();
//                    long tenSecondsInMillis = 1000 * 10;
//
//                    alarmManager.set(AlarmManager.RTC_WAKEUP,
//                            timeAtButtonClick + tenSecondsInMillis,
//                            pendingIntent);

                    Intent toMainAct = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(toMainAct);
                }
            }
        });
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "Notifications";
            String description = "Channel for notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("notifyMe", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    // Follow tutorial from this video: https://www.youtube.com/watch?v=yrpimdBRk5Q&t=128s
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        String data;
        data = DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        timeTV.setText(data);

        startAlarm(c);
    }

    public void startAlarm(Calendar c){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }



}