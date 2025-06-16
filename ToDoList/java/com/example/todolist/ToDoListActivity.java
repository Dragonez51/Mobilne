package com.example.todolist;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class ToDoListActivity extends AppCompatActivity {

    private ListView tasksList;
    private ArrayAdapter<Task> tasksAdapter;
    private LinkedList<Task> tasks = new LinkedList<>();
    private ImageButton addTask, settings, Return;
    private TextView Title;
    private boolean firstTask;
    private Settings s1;

    private static int currentIndex = -1;
    private static String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list_layout);

        tasksList = findViewById(R.id.List);
        addTask = findViewById(R.id.addTask);
        settings = findViewById(R.id.settings);
        Return = findViewById(R.id.Return);
        Title = findViewById(R.id.Title);

        try{
            currentIndex = (int) getIntent().getExtras().get("ID");
            title = String.valueOf(getIntent().getExtras().get("Title"));
        }catch(NullPointerException e){

        }
        s1 = SavedData.SETTINGS.get(currentIndex);

        tasks.clear();
        firstTask = true;
        Title.setText(title);
        Title.setAllCaps(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Title.setAutoSizeTextTypeWithDefaults(TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
        }

        tasks = SavedData.TASKSLIST.get(currentIndex);

        if(tasks.isEmpty()){
            tasks.add(new Task("Naciśnij przycisk na dole aby dodać pierwsze zadanie!"));
            firstTask = true;
        }else{
            firstTask = false;
        }

        tasksAdapter = new ArrayAdapter<>(this, R.layout.task_layout, R.id.task_layout_item, tasks);

        tasksList.setAdapter(tasksAdapter);

        tasksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(!tasks.get(i).isDone()){
                    ((TextView)view).setTextColor(getColor(R.color.DoneTask));
                    tasks.get(i).setDone(true);
                }else{
                    ((TextView)view).setTextColor(Color.rgb(s1.getRed(), s1.getGreen(), s1.getBlue()));
                    tasks.get(i).setDone(false);
                }
            }
        });

        addTask.setOnClickListener(view->{
            if(firstTask){
                tasks.clear();
                tasksAdapter.notifyDataSetChanged();
                firstTask = false;
            }
            addTaskDialog();
        });

        settings.setOnClickListener(view -> {
            Intent settingsIntent = new Intent(getApplicationContext(), SettingsActivity.class);
            if(firstTask){
                tasks.clear();
            }
            SavedData.TASKSLIST.set(currentIndex, tasks);
            settingsIntent.putExtra("ID", currentIndex);
            startActivity(settingsIntent);
        });

        Return.setOnClickListener(view -> {
            Intent mainPanel = new Intent(getApplicationContext(), MainActivity.class);
            mainPanel.putExtra("extra", "extras");
            if(firstTask){
                tasks.clear();
            }
            SavedData.TASKSLIST.set(currentIndex, tasks);
            startActivity(mainPanel);
        });

        tasksList.post(this::changeColor);
        tasksList.post(this::handleDoneTasks);
    }
    private void changeColor(){
        for(Task t : tasks){
            View viewToGreyOut = tasksList.getChildAt(t.getID());
            TextView temp = viewToGreyOut.findViewById(R.id.task_layout_item);
            temp.setTextColor(Color.rgb(s1.getRed(), s1.getGreen(), s1.getBlue()));
        }
    }
    private void handleDoneTasks(){
        for(Task t : tasks){
            if(t.isDone()){
                int firstVisible = tasksList.getFirstVisiblePosition();
                View viewToGreyOut = tasksList.getChildAt(t.getID() - firstVisible);
                TextView temp = viewToGreyOut.findViewById(R.id.task_layout_item);
                temp.setTextColor(getColor(R.color.DoneTask));
            }
        }
    }
    private void addTaskDialog(){
        final Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.add_task_layout);

        EditText taskName = dialog.findViewById(R.id.TaskName);
        Button submit = dialog.findViewById(R.id.submit);
        Button cancel = dialog.findViewById(R.id.cancel);

        submit.setOnClickListener(view -> {
            String inputText = taskName.getText().toString().trim();

            if(!inputText.isEmpty()){
                tasks.add(new Task(taskName.getText().toString(), tasks.size()));
                tasksAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }else{
                Toast.makeText(this, "Nie można dodać zadania bez nazwy!", Toast.LENGTH_SHORT).show();
            }

        });

        cancel.setOnClickListener(view -> {
            Toast.makeText(this, "Anulowano", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        dialog.show();
    }

}