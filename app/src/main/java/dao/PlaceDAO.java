package dao;

import android.content.ContentValues;

import dbclasses.Place;

/**
 * Created by bigwanjeog.
 * 07/09/2016
 */
public class PlaceDAO {
    //TODO methode pour une Place
    //Entité de la table PLACE + numéro de la colonne pour la sélection
    public static final String PLACE_RESERVATION_ID = "¨RESERVATION_ID";
    public static final String PLACE_TRAJET_ID = "¨TRAJET_ID";
    public static final String PLACE_NUM = "¨PLACE_NUM";

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

    public static void selectionnerPlace(int id) {
    }
}
