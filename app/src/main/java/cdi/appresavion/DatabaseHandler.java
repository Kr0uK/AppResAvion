package cdi.appresavion;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import dao.AeroportDAO;
import dao.AvionDAO;
import dao.PlaceDAO;
import dao.ReservationDAO;
import dao.TrajetDAO;
import dao.UtilisateurDAO;
import dbclass.Aeroport;
import dbclass.Avion;
import dbclass.Reservation;
import dbclass.Trajet;
import dbclass.Utilisateur;
import shell.DateConvertisseur;


/**
 * Created by bigwanjeog.
 * 05/09/2016
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context, String name, int version) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {

            db.execSQL(UtilisateurDAO.CREATE_UTILISATEUR);
            db.execSQL(AeroportDAO.CREATE_AEROPORT);
            db.execSQL(AvionDAO.CREATE_AVION);
            db.execSQL(TrajetDAO.CREATE_TRAJET);
            db.execSQL(ReservationDAO.CREATE_RESERVATION);
            db.execSQL(PlaceDAO.CREATE_PLACE);
            Log.w("TAG", "Base created !");




        } catch (SQLiteException e) {
            Log.w("TEST", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(UtilisateurDAO.DROP_UTILISATEUR);
            db.execSQL(AeroportDAO.DROP_AEROPORT);
            db.execSQL(AvionDAO.DROP_AVION);
            db.execSQL(TrajetDAO.DROP_TRAJET);
            db.execSQL(ReservationDAO.DROP_RESERVATION);
            db.execSQL(PlaceDAO.DROP_PLACE);
            onCreate(db);
        } catch (SQLiteException e) {
            Log.w("TEST", e);
        }
    }
}