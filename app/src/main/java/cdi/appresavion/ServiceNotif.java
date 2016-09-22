package cdi.appresavion;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceNotif extends Service {

    public static final String TAG = "DEBUG";




    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(TAG, "Service démarré");

        // TODO service



        return START_STICKY;
    }

}
