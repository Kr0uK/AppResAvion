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

import shell.DateConvertisseur;

/*
 * Boris : service + notifications
 * :)
 */
public class ServiceNotif extends Service {
    //Recupere de l'user
    Ident_User ident_user = new Ident_User(); // On instancie un Ident_User
    int id = ident_user.getidUser(); // On récupère l'id de l'ident_user

    //Recupere les infos du trajet
    TrajetNotif trajetNotif = new TrajetNotif();
    long dateDepart = trajetNotif.getDateDepart();
    String aeroportDepart = trajetNotif.getAeroportDepart();
    String aeroportArrivee = trajetNotif.getAeroportArrivee();


    String notificationTitle = "INFORMATIONS SUR LE VOL";
    String notificationDesc = "Votre vol de " + aeroportDepart + "\n va bientôt partir pour " + aeroportArrivee;

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
                if ((dateDepart + 60000) > DateConvertisseur.dateSysDate().getTime()) {
                    createNotification(notificationTitle, notificationDesc, 1);
                }
            }
        }).start();
        return START_STICKY;
    }

    // Méthode qui permet de générer une notif
    private final NotificationManager createNotification(String notification_title, String notification_desc, int notifID) {

        final NotificationManager mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent resultIntent = new Intent(this, AccueilActivity.class);
        resultIntent.putExtra("idUser", Integer.toString(id));

        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        try {
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Notification.Builder builder = new Notification.Builder(this)
                    .setWhen(System.currentTimeMillis())
                    .setTicker(notification_title)
                    .setSmallIcon(R.drawable.avion)
                    .setContentTitle(notification_title)
                    .setContentText(notification_desc)
                    .setSound(uri)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);


            mNotification.notify(notifID, builder.build());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getStackTrace().toString());
        }
        return mNotification;
    }
}