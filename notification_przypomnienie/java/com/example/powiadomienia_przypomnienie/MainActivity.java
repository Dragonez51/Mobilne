package com.example.powiadomienia_przypomnienie;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "2234";
    private static final String CHANNEL_NAME = "nazwa kanalu";
    private static int NOTIFICATION_ID = 0;

    private Button przycisk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 0);
        }

        NotificationHelper.createNotificationChannels(this);

        findViewById(R.id.button1).setOnClickListener(view -> {
                NotificationHelper.sendNotification(this, this, "notifi", "hello world!", "what?", 1, 0, CHANNEL_ID);
            });
    }
}