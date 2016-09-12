package cdi.appresavion;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import dao.AeroportDAO;
import dao.ImportCSV;


import dao.UtilisateurDAO;


/**
 * Created by RENAUD on 05/09/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context, String name, int version) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.w("TEST", "Je suis dans le onCreate");
        //db.execSQL(UtilisateurDAO.CREATE_UTILISATEUR);

        db.execSQL(AeroportDAO.CREATE_AEROPORT);
        Log.w("TEST", "Je suis a la fin du onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL(UtilisateurDAO.DROP_UTILISATEUR);
        db.execSQL(AeroportDAO.DROP_AEROPORT);
        onCreate(db);
    }
}
