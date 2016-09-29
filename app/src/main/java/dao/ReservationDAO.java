package dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import dbclass.Place;
import dbclass.Trajet;
import shell.DateConvertisseur;
import dbclass.Reservation;

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
    public static void ajouterReservationPlace(Reservation reservationAdd, int idTrajet) {
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Reservation
        value.put(RESERVATION_UTILISATEUR_ID, reservationAdd.getUtilisateurId());
        value.put(RESERVATION_DATE, DateConvertisseur.dateSysString());
        value.put(RESERVATION_PRIX, reservationAdd.getPrix());
        value.put(RESERVATION_NBPERSONNES, reservationAdd.getNbPersonnes());

        //Insert dans la base reservation + récup du dernier id de reservation
        int idReservation = (int) DAOBase.getWDb().insert(TABLE_RESERVATION, null, value);

        //Ajout de la place
        Place place = new Place(idReservation, idTrajet, 50);
        PlaceDAO.ajouterPlace(place);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Ajout d'une reservation dans la bdd a partir de l'objet Reservation.
     * @param idUtilisateur id de l'utilisateur
     * @param prixTrajet prix du trajet
     * @param nbPersonne nombre de personne
     * @param idTrajet id du trajet
     */
    public static void ajouterReservationPlace(int idUtilisateur, int prixTrajet, int nbPersonne, int idTrajet) {
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Reservation
        value.put(RESERVATION_UTILISATEUR_ID, idUtilisateur);
        value.put(RESERVATION_DATE, DateConvertisseur.dateSysString());
        value.put(RESERVATION_PRIX, prixTrajet);
        value.put(RESERVATION_NBPERSONNES, nbPersonne);

        //Insert dans la base reservation + récup du dernier id de reservation
        int idReservation = (int) DAOBase.getWDb().insert(TABLE_RESERVATION, null, value);

        //Ajout de la place
        Place place = new Place(idReservation, idTrajet, 50);
        PlaceDAO.ajouterPlace(place);

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
     * Séléction de reservation dans la bdd a partir d'un id.
     * @param id id de l'utilisateur
     * @return ArrayList de reservation
     */
    public static ArrayList<Reservation> getReservationWhere(int id) {
        //Création du curseur
        Cursor cursor = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_RESERVATION + " r"
                        + " JOIN " + PlaceDAO.TABLE_PLACE + " p"
                        + " ON r." + RESERVATION_ID + " = p." + PlaceDAO.PLACE_RESERVATION_ID
                        + " JOIN " + TrajetDAO.TABLE_TRAJET+ " t"
                        + " ON t." + TrajetDAO.TRAJET_ID + " = p." + PlaceDAO.PLACE_TRAJET_ID
                        + " WHERE " + RESERVATION_UTILISATEUR_ID + " = " + id
                        + " AND " + TrajetDAO.TRAJET_DATE_DEPART + " >= '" + DateConvertisseur.dateSysString() + "'"
                        + " ORDER BY " + TrajetDAO.TRAJET_DATE_DEPART
                , null);

        //Déplace le curseur a la valeur 0
        cursor.moveToFirst();

        //ArrayList qui va contenir les reservations
        ArrayList<Reservation> arrayList = new ArrayList<>();

        while (!cursor.isAfterLast()){
            //Ajoute les informations du curseur dans l'objet reservation
            Reservation reservationGetAll = new Reservation();
            reservationGetAll.setReservationId(cursor.getInt(NUM_RESERVATION_ID));
            reservationGetAll.setUtilisateurId(cursor.getInt(NUM_RESERVATION_UTILISATEUR_ID));
            reservationGetAll.setDate(DateConvertisseur.stringToDate(cursor.getString(NUM_RESERVATION_DATE)));
            reservationGetAll.setPrix(cursor.getInt(NUM_RESERVATION_PRIX));
            reservationGetAll.setNbPersonnes(cursor.getInt(NUM_RESERVATION_NBPERSONNES));

            //Ajout dans l'ArrayList
            arrayList.add(reservationGetAll);
            cursor.moveToNext();
        }
        cursor.close();
        return arrayList;
    }

    /**
     * Récupére toutes les réservations de la bdd.
     * @return arraylist de reservation
     */
    public static ArrayList<Reservation> getAllReservation() {
        //Création du curseur
        Cursor cursor = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_RESERVATION, null);

        //Déplace le curseur a la valeur 0
        cursor.moveToFirst();

        //ArrayList qui va contenir les reservations
        ArrayList<Reservation> arrayList = new ArrayList<>();

        while (!cursor.isAfterLast()){
            //Ajoute les informations du curseur dans l'objet reservation
            Reservation reservationGetAll = new Reservation();
            reservationGetAll.setReservationId(cursor.getInt(NUM_RESERVATION_ID));
            reservationGetAll.setUtilisateurId(cursor.getInt(NUM_RESERVATION_UTILISATEUR_ID));
            reservationGetAll.setDate(DateConvertisseur.stringToDate(cursor.getString(NUM_RESERVATION_DATE)));
            reservationGetAll.setPrix(cursor.getInt(NUM_RESERVATION_PRIX));
            reservationGetAll.setNbPersonnes(cursor.getInt(NUM_RESERVATION_NBPERSONNES));

            //Ajout dans l'ArrayList
            arrayList.add(reservationGetAll);
            cursor.moveToNext();
        }
        cursor.close();
        return arrayList;
    }

    /**
     * Compte le nombre de place enregistré pour un trajet.
     * @param idTrajet id du trajet
     * @return le nombre de place prise
     */
    public static int sumPlace(int idTrajet) {
        Cursor cursor = DAOBase.getRDb().rawQuery("SELECT sum(" + RESERVATION_NBPERSONNES + ") FROM " + TABLE_RESERVATION + " r"
                        +" JOIN " + PlaceDAO.TABLE_PLACE + " p"
                        +" ON r." + RESERVATION_ID + " = p." + PlaceDAO.PLACE_RESERVATION_ID
                        +" WHERE " + PlaceDAO.PLACE_TRAJET_ID + " = " + idTrajet
                , null);

        //Déplace le curseur a la valeur 0
        cursor.moveToFirst();

        int nbPlace = cursor.getInt(0);

        cursor.close();

        return nbPlace;
    }
}
