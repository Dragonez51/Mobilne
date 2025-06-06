package com.example.zad9;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayAdapter<String> lvAdapter;
    private LinkedList<String> settingNames;
    private LinkedList<Integer> settingValues;
    private LinkedList<String> settingUnits;
    private LinkedList<String> displayItems;

    private TextView editTitle, valueBarValue;
    private SeekBar valueBar;

    private int selectedItemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedItemID = -1;

        lv = findViewById(R.id.listview);
        editTitle = findViewById(R.id.editTitle);
        valueBar = findViewById(R.id.valueBar);
        valueBarValue = findViewById(R.id.valueBarValue);

        editTitle.setText("Wybierz opcje:");
        valueBarValue.setText("Wybierz opcje");
        valueBar.setEnabled(false);

        settingNames = new LinkedList<>();
        settingValues = new LinkedList<>();
        settingUnits = new LinkedList<>();
        displayItems = new LinkedList<>();

        settingNames.add("Jasność ekranu");
        settingNames.add("Głośność dźwięków");
        settingNames.add("Czas automatycznej blokady");

        settingValues.add(50);
        settingValues.add(80);
        settingValues.add(30);

        settingUnits.add("%");
        settingUnits.add("%");
        settingUnits.add("s");

        for(int i=0; i< settingNames.size(); i++){
            displayItems.add(settingNames.get(i) + ": " + settingValues.get(i) + settingUnits.get(i));
        }

        lvAdapter = new ArrayAdapter<>(this, R.layout.task_layout, R.id.listViewItem, displayItems);

        lv.setAdapter(lvAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemID = i;
                editTitle.setText("Edytujesz: " + settingNames.get(i));
                valueBar.setEnabled(true);
                valueBar.setProgress(settingValues.get(i));
                valueBarValue.setText("Wartość: " + settingValues.get(i));
            }
        });

        valueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b && selectedItemID!=-1){
                    valueBarValue.setText("Wartość: " + i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(selectedItemID!=-1){
                    settingValues.set(selectedItemID, seekBar.getProgress());
                    displayItems.set(selectedItemID, settingNames.get(selectedItemID)+": "+settingValues.get(selectedItemID)+settingUnits.get(selectedItemID));
                    lvAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}