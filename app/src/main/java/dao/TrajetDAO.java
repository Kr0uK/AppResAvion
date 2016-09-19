package dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import shell.DateConvertisseur;
import dbclass.Trajet;

/**
 * Created by bigwanjeog.
 * 07/09/2016
 */
public class TrajetDAO {

    //TODO recupérer tous les trajets + les infos du trajet au click
    //Entité de la table TRAJET + numéro de la colonne pour la sélection
    public static final String TRAJET_ID = "TRAJET_ID";
    public static final int NUM_TRAJET_ID = 0;
    public static final String TRAJET_AVION_ID = "AVION_ID";
    public static final int NUM_TRAJET_AVION_ID = 1;
    public static final String TRAJET_AEROPORT_ID = "AEROPORT_ID";
    public static final int NUM_TRAJET_AEROPORT_ID = 2;
    public static final String TRAJET_AER_AEROPORT_ID = "AER_AEROPORT_ID";
    public static final int NUM_TRAJET_AER_AEROPORT_ID = 3;
    public static final String TRAJET_DATE_DEPART = "TRAJET_DATE_DEPART";
    public static final int NUM_TRAJET_DATE_DEPART = 4;
    public static final String TRAJET_DATE_ARRIVEE = "TRAJET_DATE_ARRIVEE";
    public static final int NUM_TRAJET_DATE_ARRIVEE = 5;

    //Nom de la table
    public static final String TABLE_TRAJET = "TRAJET";

    //Création de la table TRAJET
    public static final String CREATE_TRAJET = "CREATE TABLE IF NOT EXISTS " + TABLE_TRAJET + "("
            + TRAJET_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + TRAJET_AVION_ID + " INTEGER NOT NULL REFERENCES " + AvionDAO.TABLE_AVION + "(" + TRAJET_AVION_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + TRAJET_AEROPORT_ID + " INTEGER NOT NULL REFERENCES " + AeroportDAO.TABLE_AEROPORT + "(" + TRAJET_AEROPORT_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + TRAJET_AER_AEROPORT_ID + " INTEGER NOT NULL REFERENCES " + AeroportDAO.TABLE_AEROPORT + "(" + TRAJET_AEROPORT_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + TRAJET_DATE_DEPART + " date, "
            + TRAJET_DATE_ARRIVEE + " date);";
    //Suppression de la table TRAJET si elle existe
    public static final String DROP_TRAJET = "DROP TABLE IF EXISTS " + TABLE_TRAJET + ";";

    /**
     * Ajout d'un trajet dans la bdd a partir de l'objet Trajet.
     * @param trajetAdd Trajet
     */
    public static void ajouterTrajet(Trajet trajetAdd) {
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Trajet
        value.put(TRAJET_AVION_ID, trajetAdd.getAvionId());
        value.put(TRAJET_AEROPORT_ID, trajetAdd.getAeroportId());
        value.put(TRAJET_AER_AEROPORT_ID, trajetAdd.getAerAeroportId());
        value.put(TRAJET_DATE_DEPART, DateConvertisseur.dateToString(trajetAdd.getDateDepart()));
        value.put(TRAJET_DATE_ARRIVEE, DateConvertisseur.dateToString(trajetAdd.getDateArrivee()));

        //Insert dans la base
        DAOBase.getWDb().insert(TABLE_TRAJET, null, value);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Supprime un trajet a partir d'un id.
     * @param id id du trajet
     */
    public static void supprimerTrajet(int id) {
        DAOBase.getWDb().delete(TABLE_TRAJET, TRAJET_ID + " = " + id, null);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Modifier un trajet a partir d'un id et l'objet Trajet.
     * @param trajetUpdate Trajet
     * @param id id du trajet
     */
    public static void modifierTrajet(Trajet trajetUpdate, int id) {
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Trajet
        value.put(TRAJET_AVION_ID, trajetUpdate.getAvionId());
        value.put(TRAJET_AEROPORT_ID, trajetUpdate.getAeroportId());
        value.put(TRAJET_AER_AEROPORT_ID, trajetUpdate.getAerAeroportId());
        value.put(TRAJET_DATE_DEPART, DateConvertisseur.dateToString(trajetUpdate.getDateDepart()));
        value.put(TRAJET_DATE_ARRIVEE, DateConvertisseur.dateToString(trajetUpdate.getDateArrivee()));

        //Update dans la base
        DAOBase.getWDb().update(TABLE_TRAJET, value, TRAJET_ID + " = " + id, null);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Récupéreu un trajet de la bdd.
     * @param id du trajet
     * @return trajet
     */
    public static Trajet selectionnerTrajet(int id) {
        //Création du curseur
        Cursor cursor = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_TRAJET + " WHERE " + TRAJET_ID + " = " + id, null);

        //Déplace le curseur a la valeur 0
        cursor.moveToFirst();

        //Ajoute les informations du curseur dans l'objet Trajet
        Trajet trajetGetAll = new Trajet();
        trajetGetAll.setTrajetId(cursor.getInt(NUM_TRAJET_ID));
        trajetGetAll.setAvionId(cursor.getInt(NUM_TRAJET_AVION_ID));
        trajetGetAll.setAeroportId(cursor.getInt(NUM_TRAJET_AEROPORT_ID));
        trajetGetAll.setAerAeroportId(cursor.getInt(NUM_TRAJET_AER_AEROPORT_ID));
        trajetGetAll.setDateDepart(DateConvertisseur.stringToDate(cursor.getString(NUM_TRAJET_DATE_DEPART)));
        trajetGetAll.setDateArrivee(DateConvertisseur.stringToDate(cursor.getString(NUM_TRAJET_DATE_ARRIVEE)));

        cursor.close();
        return trajetGetAll;
    }

    /**
     * Récupére tous les trajets de la bdd.
     * @return ArrayList de trajet
     */
    public static ArrayList<Trajet> getAllTrajet() {
        //Création du curseur
        Cursor cursor = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_TRAJET, null);

        //Déplace le curseur a la valeur 0
        cursor.moveToFirst();

        ArrayList<Trajet> arrayList = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            //Ajoute les informations du curseur dans l'objet Trajet
            Trajet trajetGetAll = new Trajet();
            trajetGetAll.setTrajetId(cursor.getInt(NUM_TRAJET_ID));
            trajetGetAll.setAvionId(cursor.getInt(NUM_TRAJET_AVION_ID));
            trajetGetAll.setAeroportId(cursor.getInt(NUM_TRAJET_AEROPORT_ID));
            trajetGetAll.setAerAeroportId(cursor.getInt(NUM_TRAJET_AER_AEROPORT_ID));
            trajetGetAll.setDateDepart(DateConvertisseur.stringToDate(cursor.getString(NUM_TRAJET_DATE_DEPART)));
            trajetGetAll.setDateArrivee(DateConvertisseur.stringToDate(cursor.getString(NUM_TRAJET_DATE_ARRIVEE)));

            //Ajout dans l'ArrayList
            arrayList.add(trajetGetAll);
            cursor.moveToNext();
        }
        cursor.close();
        return arrayList;
    }
}
