package shell;

import android.database.Cursor;

import java.util.ArrayList;

import dao.DAOBase;
import dbclass.Avion;

/**
 * Created by User on 15/09/2016.
 */
public class Request {
    public static ArrayList getCeQueLonVeux(String requete) {

        //Création du curseur
        Cursor cursor = DAOBase.getRDb().rawQuery(requete, null);

        //Déplace le curseur a la valeur 0
        cursor.moveToFirst();

        //ArrayList qui va contenir les avions
        ArrayList arrayList = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            for (int i = 0; i < cursor.getColumnCount(); i++)
                arrayList.add(cursor.getString(i));

            // change de ligne
            cursor.moveToNext();

        }
        cursor.close();
        return arrayList;
    }


    public static ArrayList getCeQueLonVeux(String requete, String table) {

        String MaTable = "";
        String MaRequete = "";
        switch (table.toUpperCase()) {
            case "AVION" :
                MaTable =  "TABLE_AVION";
                break;
            case "USER" :
                //etc
                break;
            default:
                break;
        }

        MaRequete = new StringBuilder(MaTable).append(requete).toString();

        //Création du curseur
        Cursor cursor = DAOBase.getRDb().rawQuery(MaRequete, null);

        //Déplace le curseur a la valeur 0
        cursor.moveToFirst();

        //ArrayList qui va contenir les avions
        ArrayList arrayList = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            for (int i = 0; i < cursor.getColumnCount(); i++)
                arrayList.add(cursor.getString(i));

            // change de ligne
            cursor.moveToNext();

        }
        cursor.close();
        return arrayList;
    }
}
