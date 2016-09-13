package dao;

import android.content.ContentValues;
import android.database.Cursor;

import dbclass.Aeroport;

/**
 * Created by bigwanjeog.
 * 07/09/2016
 */
public class AeroportDAO {

    //Entité de la table AEROPORT + numéro de la colonne pour la sélection
    public static final String AEROPORT_ID = "AEROPORT_ID";
    public static final int NUM_AEROPORT_ID = 0;
    public static final String AEROPORT_NOM = "AEROPORT_NOM";
    public static final int NUM_AEROPORT_NOM = 1;
    public static final String AEROPORT_VILLE = "AEROPORT_VILLE";
    public static final int NUM_AEROPORT_VILLE = 2;
    public static final String AEROPORT_PAYS = "AEROPORT_PAYS";
    public static final int NUM_AEROPORT_PAYS = 3;
    public static final String AEROPORT_CODE = "AEROPORT_CODE";
    public static final int NUM_AEROPORT_CODE = 4;
    public static final String AEROPORT_LATITUDE = "AEROPORT_LATITUDE";
    public static final int NUM_AEROPORT_LATITUDE = 5;
    public static final String AEROPORT_LONGITUDE = "AEROPORT_LONGITUDE";
    public static final int NUM_AEROPORT_LONGITUDE = 6;

    //Nom de la table
    public static final String TABLE_AEROPORT = "AEROPORT";

    //Création de la table aeroport
    public static final String CREATE_AEROPORT = "CREATE TABLE " + TABLE_AEROPORT + "("
            + AEROPORT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + AEROPORT_NOM + " VARCHAR(100), "
            + AEROPORT_VILLE + " VARCHAR(80), "
            + AEROPORT_PAYS + " VARCHAR(100), "
            + AEROPORT_CODE + " VARCHAR(3), "
            + AEROPORT_LATITUDE + " REAL, "
            + AEROPORT_LONGITUDE + " REAL );";
    //Suppresion de la table si elle existe déjà
    public static final String DROP_AEROPORT = "DROP TABLE IF EXISTS " + TABLE_AEROPORT + ";";

    /**
     * Ajout d'un aeroport.
     * @param aeroportAdd objet aeroport
     */
    public static void ajouterAeroport(Aeroport aeroportAdd) {
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Aeoroport
        value.put(AEROPORT_NOM, aeroportAdd.getNom());
        value.put(AEROPORT_VILLE, aeroportAdd.getVille());
        value.put(AEROPORT_PAYS, aeroportAdd.getPays());
        value.put(AEROPORT_CODE, aeroportAdd.getCode());
        value.put(AEROPORT_LATITUDE, aeroportAdd.getLatitude());
        value.put(AEROPORT_LONGITUDE, aeroportAdd.getLongitude());

        //Insert dans la base
        DAOBase.getWDb().insert(TABLE_AEROPORT, null, value);
        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Création du curseur qui va parcourir la base.
     * @param id id id de l'Aeroport
     * @return Aeroport
     */
    public static Aeroport selectionnerAeroport(int id) {
        Cursor c = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_AEROPORT + " WHERE " + AEROPORT_ID + " = " + id, null);
        return cursorToAeroport(c);
    }

    /**
     * Transformation du curseur en Aeroport.
     * @param c cursor
     * @return Aeroport
     */
    public static Aeroport cursorToAeroport(Cursor c) {
        //Verifie qu'il y a une ligne
        if (c.getCount() == 0) {
            return null;
        }
        //Déplace le curseur a la valeur 0
        c.moveToFirst();

        //Ajoute les informations du curseur dans l'objet Aeroport
        Aeroport aeroportSelect = new Aeroport();
        aeroportSelect.setId(c.getInt(NUM_AEROPORT_ID));
        aeroportSelect.setNom(c.getString(NUM_AEROPORT_NOM));
        aeroportSelect.setVille(c.getString(NUM_AEROPORT_VILLE));
        aeroportSelect.setPays(c.getString(NUM_AEROPORT_PAYS));
        aeroportSelect.setCode(c.getString(NUM_AEROPORT_CODE));
        aeroportSelect.setLatitude(c.getDouble(NUM_AEROPORT_LATITUDE));
        aeroportSelect.setLongitude(c.getDouble(NUM_AEROPORT_LONGITUDE));

        c.close();
        return aeroportSelect;
    }
}
