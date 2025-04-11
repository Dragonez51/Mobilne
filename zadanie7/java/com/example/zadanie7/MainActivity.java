package com.example.zadanie7;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private boolean added = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 0);
        }

        NotificationHelper.createNotificationChannels(this);

        findViewById(R.id.zobaczOpis).setOnClickListener(view -> {
            NotificationHelper.sendNotification(this, this, "Moja książka", "Krótki opis: To biblia służąca do wypędzania błędów składniowych!", "Autor Bogi", 1, R.drawable.books_icon, NotificationHelper.CHANNEL_ID_DEFAULT);
        });

        findViewById(R.id.reminder).setOnClickListener(view -> {
            NotificationHelper.sendNotification(this, this, "Moja książka", "Pamiętaj, żeby znaleźć czas na lekturę!", "", 1, 0, NotificationHelper.CHANNEL_ID_DEFAULT);
        });

        Button wishlist = findViewById(R.id.wishlist);
        TextView addedTv = findViewById(R.id.additional);

        wishlist.setOnClickListener(view -> {
                if(added){
                    wishlist.setText("USUŃ Z CHCĘ PRZECZYTAĆ");
                    addedTv.setVisibility(TextView.VISIBLE);
                    added = false;
                }else{
                    wishlist.setText("DODAJ DO CHCĘ PRZECZYTAĆ");
                    addedTv.setVisibility(TextView.GONE);
                    added = true;
                }
        });
    }
}