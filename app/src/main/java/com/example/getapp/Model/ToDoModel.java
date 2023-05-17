package com.example.getapp.Model;

public class ToDoModel extends TaskId {
    private String task, due, priority, description;
    private int status;

    public String getTask() {
        return task;
    }

    public String getDue() {
        return due;
    }

    public String getPriority() {
        return priority;
    }

    public String getDescription(){return description;}

    public int getStatus() {
        return status;
    }
}
