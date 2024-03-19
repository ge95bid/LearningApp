package com.example.learningapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TODOActivity extends AppCompatActivity implements TaskAdapter.OnTaskLongClickListener {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> tasks;
    private EditText editTextTask;
    private Button buttonAddTask;
    String[] taskname = new String[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolist);

        recyclerView = findViewById(R.id.recycler_view);
        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        tasks = new ArrayList<>();

        loadModules(); // Load saved tasks from SharedPreferences

        adapter = new TaskAdapter(tasks, this);
        recyclerView.setAdapter(adapter);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskName = editTextTask.getText().toString().trim();
                if (!taskName.isEmpty()) {
                    Task newTask = new Task(taskName, false);
                    tasks.add(newTask);
                    adapter.notifyItemInserted(tasks.size() - 1);
                    recyclerView.smoothScrollToPosition(tasks.size() - 1);
                    editTextTask.setText("");
                    saveModuletodo(); // Save the added task
                }
            }
        });
    }

    @Override
    public void onTaskLongClick(int position) {
        tasks.remove(position);
        adapter.notifyItemRemoved(position);
        saveModuletodo(); // Save the updated task list after removing a task
    }

    public void saveModuletodo() {
        SharedPreferences sharedPreferences = getSharedPreferences("To-Do", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Clear existing tasks before saving
        for (int i = 0; i < tasks.size(); i++) {
            editor.putString("To-Do" + i, tasks.get(i).getName());
        }
        editor.apply();
    }

    public void loadModules() {
        SharedPreferences sharedPreferences = getSharedPreferences("To-Do", MODE_PRIVATE);
        tasks.clear(); // Clear existing tasks before loading
        for (int i = 0; i < taskname.length; i++) {
            if (sharedPreferences.contains("To-Do" + i)) {
                taskname[i] = sharedPreferences.getString("To-Do" + i, "");
                tasks.add(new Task(taskname[i], false)); // Assuming all loaded tasks are not completed
            }
        }
        adapter.notifyDataSetChanged();
    }
}
