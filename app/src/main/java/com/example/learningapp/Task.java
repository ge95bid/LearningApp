package com.example.learningapp;

public class Task {
    public String name;
    public boolean completed;

    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }
}
