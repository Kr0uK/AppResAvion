package cdi.appresavion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import java.util.Random;

/*
 * Boris : service + notifications
 * :)
 */
public class ServiceNotif extends Service {
    String notificationTitle = "INFORMATIONS SUR LE VOL";
    String notificationDesc = "Votre vol va bientôt partir";
    public static final String TAG = "DEBUG";

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                createNotification(notificationTitle, notificationDesc, 1);
            }
        }).start();
        return START_STICKY;
    }

    // Méthode qui permet de générer une notif
    private final NotificationManager createNotification(String notification_title, String notification_desc, int notifID) {

        final NotificationManager mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final Intent launchNotifiactionIntent = new Intent(this, ServiceNotif.class);

        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, launchNotifiactionIntent, PendingIntent.FLAG_ONE_SHOT);
        try {
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Notification.Builder builder = new Notification.Builder(this)
                    .setWhen(System.currentTimeMillis())
                    .setTicker(notification_title)
                    .setSmallIcon(R.drawable.avion)
                    .setContentTitle(notification_title)
                    .setContentText(notification_desc)
                    .setSound(uri)
                    .setAutoCancel(true);

            Intent resultIntent = new Intent(this, AccueilActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(MainActivity.class);
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(resultPendingIntent);

            mNotification.notify(notifID, builder.build());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getStackTrace().toString());
        }
        return mNotification;
    }
}