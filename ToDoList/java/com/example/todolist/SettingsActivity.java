package com.example.todolist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private SeekBar red, green, blue;
    private View colorPreview;
    private ImageButton Return;

    private int redVal, greenVal, blueVal;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout);

//        Intent intent = getIntent();
        int currentIndex = (int) getIntent().getExtras().get("ID");
        redVal = SavedData.SETTINGS.get(currentIndex).getRed();
        greenVal = SavedData.SETTINGS.get(currentIndex).getGreen();
        blueVal = SavedData.SETTINGS.get(currentIndex).getBlue();

        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);
        colorPreview = findViewById(R.id.TaskForegroundPreview);
        Return = findViewById(R.id.Return);

        red.setProgress(redVal);
        green.setProgress(greenVal);
        blue.setProgress(blueVal);

        colorPreview.setBackgroundColor(Color.rgb(redVal, greenVal, blueVal));

        Return.setOnClickListener(view->{
            Intent intent2 = new Intent(getApplicationContext(), ToDoListActivity.class);
            SavedData.SETTINGS.get(currentIndex).setRed(redVal);
            SavedData.SETTINGS.get(currentIndex).setGreen(greenVal);
            SavedData.SETTINGS.get(currentIndex).setBlue(blueVal);
            startActivity(intent2);
        });

        red.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int temp;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                temp = i;
                colorPreview.setBackgroundColor(Color.rgb(i, greenVal, blueVal));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                redVal = temp;
            }
        });

       green.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           int temp;
           @Override
           public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                temp = i;
                colorPreview.setBackgroundColor(Color.rgb(redVal, i, blueVal));
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {
                greenVal = temp;
           }
       });

       blue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           int temp;
           @Override
           public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                temp = i;
                colorPreview.setBackgroundColor(Color.rgb(redVal, greenVal, i));
           }

           @Override
           public void onStartTrackingTouch(SeekBar seekBar) {

           }

           @Override
           public void onStopTrackingTouch(SeekBar seekBar) {
                blueVal = temp;
           }
       });
    }

}