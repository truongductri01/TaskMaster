package com.example.todolist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class TaskInfoAdapter extends ArrayAdapter<Tasks> {
    private Activity context;
    private List<Tasks> tasksList;

    public TaskInfoAdapter(Activity context, List<Tasks> tasksList){
        super(context, R.layout.list_view, tasksList);
        this.context = context;
        this.tasksList = tasksList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View listView = inflater.inflate(R.layout.list_view, null, true);

        TextView taskName = listView.findViewById(R.id.name);
        TextView taskDate = listView.findViewById(R.id.date);
//        TextView weekday = listView.findViewById(R.id.weekday);

        Tasks tasks = tasksList.get(position);
        taskName.setText(tasks.getTaskName());
        taskDate.setText("Due Date: " + tasks.getTaskDate() + " --- " + tasks.getWeekday() + " at " + tasks.getTimeData());
//        weekday.setText(tasks.getDay());
        return listView;
    }
}
