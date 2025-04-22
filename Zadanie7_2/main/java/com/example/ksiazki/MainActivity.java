package com.example.ksiazki;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean liked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.readMore).setOnClickListener(view -> {
           NotificationHelper.sendNotification(this, this, "Moja Książka", "", "Krótki opis: Ekscytująca historia pełna zwrotów akcji.", 1, 0, NotificationHelper.CHANNEL_ID_DEFAULT);
        });

        Button like = findViewById(R.id.like);
        like.setOnClickListener(view -> {
            if(!liked){
                like.setText("USUŃ Z CHCĘ PRZECZYTAĆ");
                findViewById(R.id.addedToList).setVisibility(View.VISIBLE);
                liked = true;
            }else{
                like.setText("DODAJ DO CHCĘ PRZECZYTAĆ");
                findViewById(R.id.addedToList).setVisibility(View.GONE);
                liked = false;
            }
        });

        findViewById(R.id.remind).setOnClickListener(view -> {
            NotificationHelper.sendNotification(this, this, "Moja Książka", "", "Pamiętaj aby znaleźć czas na lekturę!", 1, 0, NotificationHelper.CHANNEL_ID_DEFAULT);
        });

    }
}