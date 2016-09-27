package cdi.appresavion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
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
    // Strings
    String notificationTitle = "INFORMATIONS SUR LE VOL";
    String notificationDesc = "Ce modèle d'avion n'a jamais connu  de crashs recensés";
    String notificationTitle2 = "INFORMATIONS SUR LE VOL";
    String notificationDesc2 = "Ce modèle d'avion n'a jamais connu d'atterrissages réussis recensés";
    Ident_User ident_user = new Ident_User(); // On instancie un Ident_User
    int id = ident_user.getidUser(); // On récupère l'id de l'ident_user
    public static final String TAG = "DEBUG";
    
    //Générateur de temps aléatoire pour les notifs
    Random rnd = new Random();
    int tpsWait;
    int rndRoll;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(TAG, "Service démarré");
        Log.w(TAG, "" + id);

        // Notifie l'utilisateur tout les (wait), pendant x fois (nombre d'itérations du for)
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 5; i++) {

                    //RNG notif
                    rndRoll = rnd.nextInt(100)+1;
                    //RNG temps
                    tpsWait = (rnd.nextInt(30000) + 1000);

                    if (rndRoll < 50) {
                        // Lance la notif 1
                        createNotification(notificationTitle, notificationDesc, 1);
                        try {
                            synchronized(this){
                                wait(tpsWait);
                            }
                        }
                        catch(InterruptedException ex){
                            break;
                        }

                    }else {
                        // Lance la notif 2
                        createNotification2(notificationTitle2, notificationDesc2, 1);
                        try {
                            synchronized(this){
                                wait(tpsWait);
                            }
                        }
                        catch(InterruptedException ex){
                            break;
                        }
                    }
                }
            }
        }).start();

        return START_STICKY;
    }

    // Méthode qui permet de générer une notif
    private final NotificationManager createNotification(String notification_title, String notification_desc, int notifID) {

        final NotificationManager mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final Intent launchNotifiactionIntent = new Intent(this, ServiceNotif.class);

        final PendingIntent pendingIntent = PendingIntent.getActivity(this,
                1, launchNotifiactionIntent,
                PendingIntent.FLAG_ONE_SHOT);

        final Intent lancerAppIntent = new Intent(this, AccueilActivity.class);

        final PendingIntent lancerApp = PendingIntent.getActivity(this, 1, lancerAppIntent, PendingIntent.FLAG_ONE_SHOT);
        try {
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Notification.Builder builder = new Notification.Builder(this)
                    .setWhen(System.currentTimeMillis())
                    .setTicker(notification_title)
                    .setSmallIcon(R.drawable.avion)
                    .setContentTitle(notification_title)
                    .setContentText(notification_desc + " " + notifID)
                    .setContentIntent(pendingIntent)
                    .setSound(uri)
                    .setAutoCancel(true);

            mNotification.notify(notifID, builder.build());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getStackTrace().toString());
        }

        return mNotification;
    }

    // Méthode qui permet de générer une notif
    private final NotificationManager createNotification2(String notification_title, String notification_desc, int notifID) {

        final NotificationManager mNotification = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final Intent launchNotifiactionIntent = new Intent(this, ServiceNotif.class);

        final PendingIntent pendingIntent = PendingIntent.getActivity(this,
                1, launchNotifiactionIntent,
                PendingIntent.FLAG_ONE_SHOT);

        final Intent lancerAppIntent = new Intent(this, AccueilActivity.class);

        final PendingIntent lancerApp = PendingIntent.getActivity(this, 1, lancerAppIntent, PendingIntent.FLAG_ONE_SHOT);
        try {
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            Notification.Builder builder = new Notification.Builder(this)
                    .setWhen(System.currentTimeMillis())
                    .setTicker(notification_title)
                    .setSmallIcon(R.drawable.crash)
                    .setContentTitle(notification_title)
                    .setContentText(notification_desc + " " + notifID)
                    .setContentIntent(pendingIntent)
                    .setSound(uri)
                    .setAutoCancel(true);

            mNotification.notify(notifID, builder.build());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.getStackTrace().toString());
        }

        return mNotification;
    }
}
