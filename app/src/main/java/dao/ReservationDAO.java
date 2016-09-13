package dao;

import android.content.ContentValues;
import android.database.Cursor;

import cdi.appresavion.DateConvertisseur;
import dbtable.Reservation;

/**
 * Created by bigwanjeog.
 * 07/09/2016
 */
public class ReservationDAO {

    //Entité de la table RESERVATION + numéro de la colonne pour la sélection
    public static final String RESERVATION_ID = "RESERVATION_ID";
    public static final int NUM_RESERVATION_ID = 0;
    public static final String RESERVATION_UTILISATEUR_ID = "UTILISATEUR_ID";
    public static final int NUM_RESERVATION_UTILISATEUR_ID = 1;
    public static final String RESERVATION_DATE = "RESERVATION_DATE";
    public static final int NUM_RESERVATION_DATE = 2;
    public static final String RESERVATION_PRIX = "RESERVATION_PRIX";
    public static final int NUM_RESERVATION_PRIX = 3;
    public static final String RESERVATION_NBPERSONNES = "RESERVATION_NBPERSONNES";
    public static final int NUM_RESERVATION_NBPERSONNES = 4;

    //Nom de la table
    public static final String TABLE_RESERVATION = "RESERVATION";

    //Création de la table RESERVATION
    public static final String CREATE_RESERVATION = "CREATE TABLE " + TABLE_RESERVATION + "("
            + RESERVATION_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + RESERVATION_UTILISATEUR_ID + " INTEGER NOT NULL REFERENCES " + UtilisateurDAO.TABLE_UTILISATEUR + "(" + RESERVATION_UTILISATEUR_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + RESERVATION_DATE + " date, "
            + RESERVATION_PRIX + " INTEGER, "
            + RESERVATION_NBPERSONNES + " INTEGER);";
    //Suppression de la table RESERVATION si elle existe
    public static final String DROP_RESERVATION = "DROP TABLE IF EXISTS " + TABLE_RESERVATION + ";";

    /**
     * Ajout d'une reservation dans la bdd a partir de l'objet Reservation.
     * @param reservationAdd Reservation
     */
    public static void ajouterReservation(Reservation reservationAdd) {
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Reservation
        value.put(RESERVATION_UTILISATEUR_ID, reservationAdd.getUtilisateurId());
        value.put(RESERVATION_DATE, DateConvertisseur.dateSys());
        value.put(RESERVATION_PRIX, reservationAdd.getPrix());
        value.put(RESERVATION_NBPERSONNES, reservationAdd.getNbPersonnes());

        //Insert dans la base
        DAOBase.getWDb().insert(TABLE_RESERVATION, null, value);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Supprime une reservation a partir d'un id.
     * @param id id de la reservation
     */
    public static void supprimerReservation(int id) {
        DAOBase.getWDb().delete(TABLE_RESERVATION, RESERVATION_ID + " = " + id, null);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Modifier une reservation a partir d'un id et de l'objet Reservation.
     * @param reservationUpdate Reservation
     * @param id id de la reservation
     */
    public static void modifierReservation(Reservation reservationUpdate, int id) {
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Reservation
        value.put(RESERVATION_UTILISATEUR_ID, reservationUpdate.getUtilisateurId());
        value.put(RESERVATION_DATE, DateConvertisseur.dateToString(reservationUpdate.getDate()));
        value.put(RESERVATION_PRIX, reservationUpdate.getPrix());
        value.put(RESERVATION_NBPERSONNES, reservationUpdate.getNbPersonnes());

        //Update dans la base
        DAOBase.getWDb().update(TABLE_RESERVATION, value, RESERVATION_ID + " = " + id, null);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Création du curseur qui va parcourir la base.
     * @param id id de la reservation
     * @return Reservation
     */
    public static Reservation selectionnerReservation(int id) {
        Cursor c = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_RESERVATION + " WHERE " + RESERVATION_ID + " = " + id, null);
        return cursorToReservation(c);
    }

    /**
     * Transformation du curseur en Reservation.
     * @param c cursor
     * @return Reservation
     */
    public static Reservation cursorToReservation(Cursor c) {
        //Verifie qu'il y a une ligne
        if (c.getCount() == 0) {
            return null;
        }
        //Déplace le curseur a la valeur 0
        c.moveToFirst();

        //Ajoute les informations du curseur dans l'objet reservation
        Reservation reservationSelect = new Reservation();
        reservationSelect.setReservationId(c.getInt(NUM_RESERVATION_ID));
        reservationSelect.setUtilisateurId(c.getInt(NUM_RESERVATION_UTILISATEUR_ID));
        reservationSelect.setDate(DateConvertisseur.stringToDate(c.getString(NUM_RESERVATION_DATE)));
        reservationSelect.setPrix(c.getInt(NUM_RESERVATION_PRIX));
        reservationSelect.setNbPersonnes(c.getInt(NUM_RESERVATION_NBPERSONNES));

        c.close();
        return reservationSelect;
    }
}
