package com.example.mobilne_13_09;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        i=0;
        TextView counter = findViewById(R.id.counter);
        counter.setText("0");

        Button przycisk1 = findViewById(R.id.button);
        przycisk1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                i= i+1;
                counter.setText(String.valueOf(i));
                przycisk1.setEnabled(false);
            };
        });

        Button przyciskOn = findViewById(R.id.buttonOn);
        przyciskOn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                przycisk1.setEnabled(true);
            }
        });

    }
}