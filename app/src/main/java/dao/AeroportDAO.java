package dao;

import android.content.ContentValues;
import android.util.Log;

import dbClass.Aeroport;

/**
 * Created by RENAUD on 07/09/2016.
 */
public class AeroportDAO {
    /**
     * Entité de la table AEROPORT
     */
    public static final String AEROPORT_ID = "AEROPORT_ID";
    public static final String AEROPORT_NOM = "AEROPORT_NOM";
    public static final String AEROPORT_VILLE = "AEROPORT_VILLE";
    public static final String AEROPORT_PAYS = "AEROPORT_PAYS";
    public static final String AEROPORT_CODE = "AEROPORT_CODE";
    public static final String AEROPORT_LATITUDE = "AEROPORT_LATITUDE";
    public static final String AEROPORT_LONGITUDE = "AEROPORT_LONGITUDE";
    //TODO mettre les champs


    public static final String TABLE_AEROPORT = "AEROPORT";//Nom de la table
    /**
     * Création de la table AEROPORT
     */
    //TODO mettre les champs
    public static final String CREATE_AEROPORT = "CREATE TABLE " + TABLE_AEROPORT + "("
            + AEROPORT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + AEROPORT_NOM + " VARCHAR(100), "
            + AEROPORT_VILLE + "VARCHAR(80), "
            + AEROPORT_PAYS + "VARCHAR(100)"
            + AEROPORT_CODE + " CHAR(3), "
            + AEROPORT_LATITUDE + "REAL, "
            + AEROPORT_LONGITUDE + "REAL);";


    public static final String DROP_AEROPORT = "DROP TABLE IF EXISTS " + TABLE_AEROPORT + ";";

    public void ajouterAeroport(Aeroport a){
        //TODO ajouter un Aeroport
        //databaseHandler = new DatabaseHandler(context);
        //sqLiteDatabase = databaseHandler.getWritableDatabase();

        ContentValues value = new ContentValues();
        Log.w("TEST","Je suis dans l'ajout d'util");
        value.put(AEROPORT_NOM, a.getNom());
        value.put(AEROPORT_VILLE, a.getVille());
        value.put(AEROPORT_PAYS, a.getPays());
        value.put(AEROPORT_CODE, a.getCode());
        value.put(AEROPORT_LATITUDE, a.getLatitude());
        value.put(AEROPORT_LONGITUDE, a.getLongitude());


        DAOBase.getWDb().insert(TABLE_AEROPORT, null, value);
        Log.w("TEST","J'ai réussi !");
    }

    public void supprimerAeroport(int id){
        //TODO supprimer un Aeroport
    }

    public void modifierAeroport(Aeroport a){
        //TODO modifier un Aeroport
    }

    public void selectionnerAeroport(int id){
        //TODO selectionner un Aeroport
    }
}
