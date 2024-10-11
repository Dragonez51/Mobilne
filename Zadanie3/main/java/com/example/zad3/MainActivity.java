package com.example.zad3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_COUNT = "count";
    private static final String KEY_WRITETEXT = "writeText";
    private static final String KEY_CHECKED = "checked";
    private static final String KEY_DARKMODE = "darkMode";

    private CountViewModel countViewModel;

    private TextView tekst;
    private Button przycisk;
    private EditText editText;
    private CheckBox checkBox;
    private TextView zaznaczenie;
    private Switch switchMode;

    private int counter = 0;
    private String writeText;
    private boolean checked = false;
    private boolean darkMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tekst = findViewById(R.id.text);
        przycisk = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        checkBox = findViewById(R.id.checkBox);
        zaznaczenie = findViewById(R.id.zaznaczenie);
        switchMode = findViewById(R.id.darkMode);

        if(savedInstanceState != null){
            counter = savedInstanceState.getInt(KEY_COUNT);
            writeText = savedInstanceState.getString(KEY_WRITETEXT);
            editText.setText(writeText);
            checked = savedInstanceState.getBoolean(KEY_CHECKED);
            darkMode = savedInstanceState.getBoolean(KEY_DARKMODE);
        }
        countViewModel = new ViewModelProvider(this).get(CountViewModel.class);
        //Update licznika
        updateCounter();
        //Update checkBoxa
        updateCheckBox();
        //Update trybu ciemnego
        updateDarkMode();
        //Przycisk inkrementacja licznika
        przycisk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                counter++;
                updateCounter();
            }
        });
        //CheckBox dzialanie przycisku
        checkBox.setChecked(checked);
        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(checked){
                    checked = false;
                    updateCheckBox();
                }else{
                    checked = true;
                    updateCheckBox();
                }
            }
        });
        //Tryb ciemny
        switchMode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(darkMode){
                    darkMode = false;
                    updateDarkMode();
                }else{
                    darkMode = true;
                    updateDarkMode();
                }
            }
        });

}
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, counter);
        writeText = String.valueOf(editText.getText());
        outState.putString(KEY_WRITETEXT, writeText);
        outState.putBoolean(KEY_CHECKED, checked);
        outState.putBoolean(KEY_DARKMODE, darkMode);
    }

    private void updateCounter(){
        tekst.setText("Licznik: "+ counter);
    }

    private void updateDarkMode(){
        View layout = findViewById(R.id.main);
        System.out.println(darkMode);
        if(darkMode){
            layout.setBackgroundColor(getColor(R.color.black));
            tekst.setTextColor(getColor(R.color.white));
            editText.setTextColor(getColor(R.color.white));
            checkBox.setTextColor(getColor(R.color.white));
            zaznaczenie.setTextColor(getColor(R.color.white));
            switchMode.setTextColor(getColor(R.color.white));
        }else{
            layout.setBackgroundColor(getColor(R.color.white));
            tekst.setTextColor(getColor(R.color.black));
            editText.setTextColor(getColor(R.color.black));
            checkBox.setTextColor(getColor(R.color.black));
            zaznaczenie.setTextColor(getColor(R.color.black));
            switchMode.setTextColor(getColor(R.color.black));
        }
    }
    private void updateCheckBox(){
        if(checked){
            zaznaczenie.setVisibility(View.VISIBLE);
        }else{
            zaznaczenie.setVisibility(View.INVISIBLE);
        }
    }
}
