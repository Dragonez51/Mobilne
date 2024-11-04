package com.example.zadanie5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView title, polubieniaTV, opisTitle, opis;
    private Button polub, usun, zapisz;
    private ImageView image;

    private int polubienia = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        polubieniaTV = findViewById(R.id.polubienia);
        opisTitle = findViewById(R.id.opisTitle);
        opis = findViewById(R.id.opis);

        polub = findViewById(R.id.polub);
        usun = findViewById(R.id.usun);
        zapisz = findViewById(R.id.zapisz);

        image = findViewById(R.id.image);

        polub.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                polub();
            }
        });
        usun.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                usun();
            }
        });
        zapisz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void polub(){
        polubienia++;
        updatePolubieniaTV(polubienia);
    }

    private void usun(){
        if(polubienia>0){
            polubienia--;
            updatePolubieniaTV(polubienia);
        }
    }
    private void updatePolubieniaTV(int polubienia){
        polubieniaTV.setText(polubienia+" polubień");
    }
}