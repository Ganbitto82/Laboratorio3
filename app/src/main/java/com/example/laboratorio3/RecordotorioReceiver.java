package com.example.laboratorio3;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class RecordotorioReceiver extends BroadcastReceiver {

    private static final int NOTIFICACION_ID = 1000;
    public static String RECORDATORIO ="com.example.laboratorio3";
    @Override
    public void onReceive(final Context context, final Intent intent) {
       /* StringBuilder sb =new StringBuilder();

        for (String k: intent.getExtras().keySet()){
            sb.append(k+":"+intent.getExtras().get(k));
        }
        Log.d("INTENT_RECIBIDO",intent.getAction()+" : "+ intent.getCategories() +" / "+sb.toString());

        switch (intent.getAction()){
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:  NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(context, MainActivity.CANAL_MENSAJES_ID_1)
                            .setSmallIcon(R.drawable.baseline_circle_notifications_black_18)
                            .setContentTitle("NOTIFICACION AIRPLANE")
                            .setContentText("NOTIFICACION 1")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setAutoCancel(true)
                            .setDefaults(NotificationCompat.DEFAULT_ALL);
                // .setContentIntent(pendingIntent);

                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
                notificationManagerCompat.notify(NOTIFICACION_ID, mBuilder.build());
                break;

            case Intent.ACTION_BATTERY_LOW:  NotificationCompat.Builder mBuilder1 =
                    new NotificationCompat.Builder(context, MainActivity.CANAL_MENSAJES_ID_1)
                            .setSmallIcon(R.drawable.baseline_message_24)
                            .setContentTitle("ACTION_BATTERY_LOW")
                            .setContentText("NOTIFICACION 2")
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setAutoCancel(true)
                            .setDefaults(NotificationCompat.DEFAULT_ALL);
                // .setContentIntent(pendingIntent);

                NotificationManagerCompat notificationManagerCompat2 = NotificationManagerCompat.from(context);
                notificationManagerCompat2.notify(NOTIFICACION_ID, mBuilder1.build());
                break;

        }*/
       if(intent.getAction().equals(RECORDATORIO)) {

       // Toast.makeText(context, "LLEGUE!"  , Toast.LENGTH_LONG).show();
           Intent i= new Intent(context, MainActivity.class);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
           PendingIntent pendingIntent = PendingIntent.getActivity(context,0,i,0);



           NotificationCompat.Builder mBuilder =
                   new NotificationCompat.Builder(context, MainActivity.CANAL_MENSAJES_ID_1)
                           .setSmallIcon(R.drawable.baseline_message_24)
                           .setContentTitle("ALARM_MANAGER")
                           .setContentText("NOTIFICACION")
                           .setPriority(NotificationCompat.PRIORITY_HIGH)
                           .setAutoCancel(true)
                           .setDefaults(NotificationCompat.DEFAULT_ALL)
                           .setContentIntent(pendingIntent);

           NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
           notificationManagerCompat.notify(NOTIFICACION_ID, mBuilder.build());
        }





    }
}