package com.example.learningapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;

public class TODOActivity extends AppCompatActivity implements TaskAdapter.OnTaskLongClickListener {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> tasks;
    private EditText editTextTask;
    private Button buttonAddTask;
    android.widget.CheckBox[] boxes = new android.widget.CheckBox[50];
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
        tasks.add(new Task("EE Hausaufgaben machen", true));
        tasks.add(new Task("Nachhilfeunterricht EUM vorbereiten", false));
        tasks.add(new Task("Das gesamte Skript in einer Nacht durchzugehen", false));
        tasks.add(new Task("Projektarbeit PPMM beginnen", false));
        tasks.add(new Task("Bibliotheksbücher zurückgeben", true));

        adapter = new TaskAdapter(tasks, this); // Pass 'this' as the long click listener
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
                }
            }
        });
    }

    // Implement the onTaskLongClick method
    @Override
    public void onTaskLongClick(int position) {
        tasks.remove(position);
        adapter.notifyItemRemoved(position);
    }



    public void saveModuletodo()
    {
        android.content.SharedPreferences sharedPreferences = getSharedPreferences("Modules", MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        for(int i = 0; i < boxes.length; i++)
        {
            if(boxes[i] != null)
            {
                editor.putString("Modules" + i, boxes[i].getText().toString());
            }
        }
        editor.apply();
    }
    public void loadModules()
    {
        android.content.SharedPreferences sharedPreferences = getSharedPreferences("Modules", MODE_PRIVATE);
        for(int i = 0; i < boxes.length; i++)
        {
            if(sharedPreferences.contains("Module" + i))
            {
                taskname[i] = sharedPreferences.getString("Module" + i, "");
            }
        }
    }


}
