package com.example.zadanie3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_COUNT = "count";
    private static final String KEY_WRITETEXT = "writeText";
    private static final String KEY_CHECKED = "checked";
    private static final String KEY_DARKMODE = "darkMode";

    private StateViewModel viewModel;

    private TextView tekst;
    private Button przycisk;
    private EditText editText;
    private CheckBox checkBox;
    private TextView zaznaczenie;
    private Switch switchMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(StateViewModel.class);

        tekst = findViewById(R.id.text);
        przycisk = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        checkBox = findViewById(R.id.checkBox);
        zaznaczenie = findViewById(R.id.zaznaczenie);
        switchMode = findViewById(R.id.darkMode);

        if(savedInstanceState != null){
            viewModel.setCounter(savedInstanceState.getInt(KEY_COUNT));
            viewModel.setWriteText(savedInstanceState.getString(KEY_WRITETEXT));
            editText.setText(viewModel.getWriteText());
            viewModel.setChecked(savedInstanceState.getBoolean(KEY_CHECKED));
            viewModel.setDarkMode(savedInstanceState.getBoolean(KEY_DARKMODE));
        }

        updateCounter();

        updateCheckBox();

        updateDarkMode();

        przycisk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                viewModel.incrementCounter();
                updateCounter();
            }
        });

        checkBox.setChecked(viewModel.getChecked());
        checkBox.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(viewModel.getChecked()){
                    viewModel.setChecked(false);
                    updateCheckBox();
                }else{
                    viewModel.setChecked(true);
                    updateCheckBox();
                }
            }
        });

        switchMode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(viewModel.getDarkMode()){
                    viewModel.setDarkMode(false);
                    updateDarkMode();
                }else{
                    viewModel.setDarkMode(true);
                    updateDarkMode();
                }
            }
        });

}
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_COUNT, viewModel.getCounter());
        viewModel.setWriteText(String.valueOf(editText.getText()));
        outState.putString(KEY_WRITETEXT, viewModel.getWriteText());
        outState.putBoolean(KEY_CHECKED, viewModel.getChecked());
        outState.putBoolean(KEY_DARKMODE, viewModel.getDarkMode());
    }

    private void updateCounter(){
        tekst.setText("Licznik: "+ viewModel.getCounter());
    }
    private void updateDarkMode(){
        View layout = findViewById(R.id.main);
        if(viewModel.getDarkMode()){
            layout.setBackgroundColor(getColor(R.color.black));
            tekst.setTextColor(getColor(R.color.white));
            editText.setTextColor(getColor(R.color.white));
            checkBox.setTextColor(getColor(R.color.white));
            switchMode.setTextColor(getColor(R.color.white));
        }else{
            layout.setBackgroundColor(getColor(R.color.white));
            tekst.setTextColor(getColor(R.color.black));
            editText.setTextColor(getColor(R.color.black));
            checkBox.setTextColor(getColor(R.color.black));
            switchMode.setTextColor(getColor(R.color.black));
        }
    }
    private void updateCheckBox(){
        if(viewModel.getChecked()){
            zaznaczenie.setVisibility(View.VISIBLE);
        }else{
            zaznaczenie.setVisibility(View.GONE);
        }
    }
}
