package com.example.zad10;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView wiek, result;
    private EditText godnosc, cel;
    private SeekBar sb;
    private RadioButton pies, kot, wydra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimePicker tp = findViewById(R.id.time);
        tp.setIs24HourView(true);
        tp.setHour(16);
        tp.setMinute(0);

        wiek = findViewById(R.id.wiek);
        sb = findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                wiek.setText(String.valueOf(i));
//                Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        pies = findViewById(R.id.pies);
        kot = findViewById(R.id.kot);
        wydra = findViewById(R.id.wydra);

        pies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb.setMax(18);
                kot.setChecked(false);
                wydra.setChecked(false);
            }
        });
        kot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb.setMax(20);
                pies.setChecked(false);
                wydra.setChecked(false);
            }
        });
        wydra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb.setMax(9);
                pies.setChecked(false);
                kot.setChecked(false);
            }
        });

        result = findViewById(R.id.result);
        godnosc = findViewById(R.id.imie_nazwisko);
        cel = findViewById(R.id.cel);
        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zwierze;
                if(pies.isChecked()){
                    zwierze = "pies";
                }else if(kot.isChecked()){
                    zwierze = "kot";
                }else{
                    zwierze = "wydra";
                }
                result.setText(godnosc.getText() + " z " + zwierze + " lat: " + wiek.getText() +" o godzinie: "+ tp.getHour() + ":"+tp.getMinute() + " w celu: " + cel.getText());
            }
        });
    }
}