package com.example.todolist;

public class Tasks {
    private String taskName, taskDate, weekday, timeData;
    public Tasks(){}

    public Tasks(String taskName, String taskDate, String weekday, String timeData) {
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.weekday = weekday;
        this.timeData = timeData;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public String getWeekday() {
        return weekday;
    }

    public String getTimeData() {
        return timeData;
    }
}
