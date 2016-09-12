package dao;

import android.content.ContentValues;
import android.database.Cursor;

import dbClass.Trajet;

/**
 * Created by bigwanjeog
 * 07/09/2016
 */
public class TrajetDAO {

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
    public static final String CREATE_TRAJET = "CREATE TABLE " + TABLE_TRAJET + "("
            + TRAJET_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + TRAJET_AVION_ID + " INTEGER NOT NULL REFERENCES " + AvionDAO.TABLE_AVION + "(" + TRAJET_AVION_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + TRAJET_AEROPORT_ID + "INTEGER NOT NULL REFERENCES " + AeroportDAO.TABLE_AEROPORT + "(" + TRAJET_AEROPORT_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + TRAJET_AER_AEROPORT_ID + "INTEGER NOT NULL REFERENCES " + AeroportDAO.TABLE_AEROPORT + "(" + TRAJET_AEROPORT_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + TRAJET_DATE_DEPART + " date, "
            + TRAJET_DATE_ARRIVEE + " date);";
    //Suppression de la table TRAJET si elle existe
    public static final String DROP_TRAJET = "DROP TABLE IF EXISTS " + TABLE_TRAJET + ";";

    /**
     * Ajout d'un trajet dans la bdd a partir de l'objet Trajet
     * @param TrajetAdd Trajet
     */
    public void ajouterTrajet(Trajet TrajetAdd){
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Trajet
        value.put(TRAJET_AVION_ID, TrajetAdd.getAvionId());
        value.put(TRAJET_AEROPORT_ID, TrajetAdd.getAeroportId());
        value.put(TRAJET_AER_AEROPORT_ID, TrajetAdd.getAerAeroportId());
        //value.put(TRAJET_DATE_DEPART, TrajetAdd.getDateDepart());
        //value.put(TRAJET_DATE_ARRIVEE, TrajetAdd.getDateArrivee());

        //Insert dans la base
        DAOBase.getWDb().insert(TABLE_TRAJET, null, value);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Supprime un trajet a partir d'un id
     * @param id id du trajet
     */
    public void supprimerTrajet(int id){
        DAOBase.getWDb().delete(TABLE_TRAJET, TRAJET_ID + " = " + id, null);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Modifier un trajet a partir d'un id et l'objet Trajet
     * @param TrajetUpdate Trajet
     * @param id id du trajet
     */
    public void modifierTrajet(Trajet TrajetUpdate, int id){
        //TODO /!\ si un champ est vide dans l'objet, il remplace par null a faire dans interface + date
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Trajet
        value.put(TRAJET_AVION_ID, TrajetUpdate.getAvionId());
        value.put(TRAJET_AEROPORT_ID, TrajetUpdate.getAeroportId());
        value.put(TRAJET_AER_AEROPORT_ID, TrajetUpdate.getAerAeroportId());
        //value.put(TRAJET_DATE_DEPART, TrajetUpdate.getDateDepart());
        //value.put(TRAJET_DATE_ARRIVEE, TrajetUpdate.getDateArrivee());

        //Update dans la base
        DAOBase.getWDb().update(TABLE_TRAJET, value, TRAJET_ID + " = " + id, null);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Création du curseur qui va parcourir la base
     * @param id id du trajet
     * @return Trajet
     */
    public static Trajet selectionnerTrajet(int id){
        Cursor c = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_TRAJET + " WHERE " + TRAJET_ID + " = " + id, null);
        return cursorToTrajet(c);
    }

    /**
     * Transformation du curseur en Utilisateur
     * @param c cursor
     * @return utilisateur
     */
    public static Trajet cursorToTrajet(Cursor c){
        //Verifie qu'il y a une ligne
        if (c.getCount() == 0){
            return null;
        }
        //Déplace le curseur a la valeur 0
        c.moveToFirst();

        //Ajoute les informations du curseur dans l'objet Trajet
        Trajet TrajetSelect = new Trajet();
        TrajetSelect.setTrajetId(c.getInt(NUM_TRAJET_ID));
        TrajetSelect.setAvionId(c.getInt(NUM_TRAJET_AVION_ID));
        TrajetSelect.setAeroportId(c.getInt(NUM_TRAJET_AEROPORT_ID));
        TrajetSelect.setAerAeroportId(c.getInt(NUM_TRAJET_AER_AEROPORT_ID));
        //TrajetSelect.setDateDepart(c.getString(NUM_TRAJET_DATE_DEPART));
        //TrajetSelect.setDateArrivee(c.getString(NUM_TRAJET_DATE_ARRIVEE));

        c.close();
        return TrajetSelect;
    }
}
