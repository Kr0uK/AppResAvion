package dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;

import dbclass.Place;

/**
 * Created by bigwanjeog.
 * 07/09/2016
 */
public class PlaceDAO {
    //TODO methode supprimer modifier pour une Place
    //Entité de la table PLACE + numéro de la colonne pour la sélection
    public static final String PLACE_RESERVATION_ID = "RESERVATION_ID";
    public static final int NUM_PLACE_RESERVATION_ID = 0;
    public static final String PLACE_TRAJET_ID = "TRAJET_ID";
    public static final int NUM_PLACE_TRAJET_ID = 1;
    public static final String PLACE_NUM = "PLACE_NUM";
    public static final int NUM_PLACE_NUM = 2;

    //Nom de la table
    public static final String TABLE_PLACE = "PLACE";

    //Création de la table
    public static final String CREATE_PLACE = "CREATE TABLE " + TABLE_PLACE + "("
            + PLACE_RESERVATION_ID + " INTEGER NOT NULL REFERENCES " + ReservationDAO.TABLE_RESERVATION + "(" + PLACE_RESERVATION_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + PLACE_TRAJET_ID + " INTEGER NOT NULL REFERENCES " + TrajetDAO.TABLE_TRAJET + "(" + PLACE_TRAJET_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + PLACE_NUM + " INTEGER, "
            + "PRIMARY KEY (" + PLACE_RESERVATION_ID + ", " + PLACE_TRAJET_ID + "));";
    //Suppression de la table PLACE si elle existe
    public static final String DROP_PLACE = "DROP TABLE IF EXISTS " + TABLE_PLACE + ";";

    /**
     * Ajout une place dans la bdd.
     * @param placeAdd objet place
     */
    public static void ajouterPlace(Place placeAdd) {
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Utilisateur
        value.put(PLACE_RESERVATION_ID, placeAdd.getReservationId());
        value.put(PLACE_TRAJET_ID, placeAdd.getTrajetId());
        value.put(PLACE_NUM, placeAdd.getPlaceNum());

        //Insert dans la base
        DAOBase.getWDb().insert(TABLE_PLACE, null, value);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    public static void supprimerPlace(int id) {
    }

    public static void modifierPlace() {
    }

    /**
     * Séléction d'une place dans la bdd a partir d'un id.
     * @param id id de la réservation
     * @return Place
     */
    public static Place selectionnerPlace(int id) {
        //Création du curseur
        Cursor cursor = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_PLACE + " WHERE " + PLACE_RESERVATION_ID + " = " + id, null);

        //Déplace le curseur a la valeur 0
        cursor.moveToFirst();

        //Ajoute les informations du curseur dans l'objet place
        Place placeSelect = new Place();
        placeSelect.setReservationId(cursor.getInt(NUM_PLACE_RESERVATION_ID));
        placeSelect.setTrajetId(cursor.getInt(NUM_PLACE_TRAJET_ID));
        placeSelect.setPlaceNum(cursor.getInt(NUM_PLACE_NUM));

        cursor.close();
        return placeSelect;
    }

    /**
     * Récupére toutes les places de la bdd.
     * @return arraylist de place
     */
    public static ArrayList<Place> getAllPlace() {
        //Création du curseur
        Cursor cursor = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_PLACE, null);

        //Déplace le curseur a la valeur 0
        cursor.moveToFirst();

        //ArrayList qui va contenir les places
        ArrayList<Place> arrayList = new ArrayList<>();

        while (!cursor.isAfterLast()){
            //Ajoute les informations du curseur dans l'objet place
            Place placeGetAll = new Place();
            placeGetAll.setReservationId(cursor.getInt(NUM_PLACE_RESERVATION_ID));
            placeGetAll.setTrajetId(cursor.getInt(NUM_PLACE_TRAJET_ID));
            placeGetAll.setPlaceNum(cursor.getInt(NUM_PLACE_NUM));

            //Ajout dans l'ArrayList
            arrayList.add(placeGetAll);
            cursor.moveToNext();
        }
        cursor.close();
        return arrayList;
    }
}
