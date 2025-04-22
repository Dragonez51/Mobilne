package com.example.ksiazki;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationHelper {
    public static final String CHANNEL_ID_DEFAULT = "domyslny kanal";
    public static final String CHANNEL_ID_LOW = "niski kanal";
    public static final String CHANNEL_ID_HIGH = "wysoki kanal";
    private static final String CHANNEL_NAME = "domyslna nazwa kanalu";
    private static int NOTIFICATION_ID = 0;

    public static void createNotificationChannels(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channelLow = new NotificationChannel(CHANNEL_ID_LOW, CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
            NotificationChannel channelDefault = new NotificationChannel(CHANNEL_ID_DEFAULT, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationChannel channelHigh = new NotificationChannel(CHANNEL_ID_HIGH, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            if(notificationManager!=null){
                notificationManager.createNotificationChannel(channelLow);
                notificationManager.createNotificationChannel(channelDefault);
                notificationManager.createNotificationChannel(channelHigh);
            }
        }
    }

    public static void sendNotification(AppCompatActivity activity, Context context, String title, CharSequence message, String description, int styleType, Integer largeIconResID, String channelID){
        NOTIFICATION_ID++;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            if(activity.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                activity.requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                return;
            }
        }

        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notifiChannel = new NotificationChannel(channelID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notifiChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelID)
                .setSmallIcon(R.drawable.books)
                .setContentTitle(title)
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        switch(styleType){
            case 1:
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
                break;
            case 2:
                if(largeIconResID!=null){
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), largeIconResID);
                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setBigContentTitle(title));
                    break;
                }
                break;
            case 3:
                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                inboxStyle.addLine(message);
                inboxStyle.addLine("Linijka tekstu jakas");
                builder.setStyle(inboxStyle);
                break;
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}