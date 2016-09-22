package cdi.appresavion;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceNotif extends Service {
    Ident_User ident_user = new Ident_User(); // On instancie un Ident_User
    int id = ident_user.getidUser(); // On récupère l'id de l'ident_user
    ListeResv listeResv = new ListeResv();
    String date;
    public static final String TAG = "DEBUG";


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            Log.w(TAG, "Service démarré");
            Log.w(TAG, "" + id);
            // TODO service
            if (id != 0) {
                date = listeResv.getHeure();
                Log.w(TAG, date);
                //TODO gérer la notif

                createNotification();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }


    private void createNotification() {
        try {
            //Récupération du notification Manager
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            //Récupération du titre et description de la notification
            String notificationTitle = "Testerino";
            String notificationDesc = "Kapparino";

            Notification.Builder builder = new Notification.Builder(this)
                    .setWhen(System.currentTimeMillis())
                    .setTicker(notificationTitle)
                    .setSmallIcon(R.drawable.avion)
                    .setContentTitle(notificationTitle)
                    .setContentText(notificationDesc);
            notificationManager.notify(1, builder.build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
