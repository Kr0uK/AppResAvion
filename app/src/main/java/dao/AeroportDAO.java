package dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import dbClass.Aeroport;

/**
 * Created by bigwanjeog
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

    public static void ajouterAeroport(Aeroport a){
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Utilisateur
        value.put(AEROPORT_NOM, a.getNom());
        value.put(AEROPORT_VILLE, a.getVille());
        value.put(AEROPORT_PAYS, a.getPays());
        value.put(AEROPORT_CODE, a.getCode());
        value.put(AEROPORT_LATITUDE, a.getLatitude());
        value.put(AEROPORT_LONGITUDE, a.getLongitude());

        //Insert dans la base
        DAOBase.getWDb().insert(TABLE_AEROPORT, null, value);
        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    public void supprimerAeroport(int id){
        //TODO supprimer un Aeroport
    }

    public void modifierAeroport(Aeroport a){
        //TODO modifier un Aeroport
    }

    /**
     * Création du curseur qui va parcourir la base
     * @param id id id de l'Aeroport
     * @return Aeroport
     */
    public static Aeroport selectionnerAeroport(int id){
        Cursor c = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_AEROPORT + " WHERE " + AEROPORT_ID + " = " + id, null);
        return cursorToAeroport(c);
    }

    /**
     * Transformation du curseur en Utilisateur
     * @param c cursor
     * @return utilisateur
     */
    public static Aeroport cursorToAeroport (Cursor c){
        //Verifie qu'il y a une ligne
        if (c.getCount() == 0){
            return null;
        }
        //Déplace le curseur a la valeur 0
        c.moveToFirst();

        //Ajoute les informations du curseur dans l'objet Aeroport
        Aeroport aeroport = new Aeroport();
        aeroport.setId(c.getInt(NUM_AEROPORT_ID));
        aeroport.setNom(c.getString(NUM_AEROPORT_NOM));
        aeroport.setVille(c.getString(NUM_AEROPORT_VILLE));
        aeroport.setPays(c.getString(NUM_AEROPORT_PAYS));
        aeroport.setCode(c.getString(NUM_AEROPORT_CODE));
        aeroport.setLatitude(c.getDouble(NUM_AEROPORT_LATITUDE));
        aeroport.setLongitude(c.getDouble(NUM_AEROPORT_LONGITUDE));

        c.close();
        return aeroport;
    }
}
