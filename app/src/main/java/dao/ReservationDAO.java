package dao;

/**
 * Created by RENAUD on 07/09/2016.
 */
public class ReservationDAO {
    static UtilisateurDAO u;

    /**
     * Entité de la table RESERVATION
     */
    public static final String RESERVATION_ID = "RESERVATION_ID";
    public static final String RESERVATION_DATE = "RESERVATION_DATE";
    public static final String RESERVATION_PRIX = "RESERVATION_PRIX";
    public static final String RESERVATION_NBPERSONNES= "RESERVATION_NBPERSONNES";

    public static final String TABLE_RESERVATION = "RESERVATION";//Nom de la table
    /**
     * Création de la table RESERVATION
     */
    public static final String CREATE_RESERVATION = "CREATE TABLE " + TABLE_RESERVATION + "("
            + RESERVATION_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + u.UTILISATEUR_ID + " INTEGER NOT NULL REFERENCES " + u.TABLE_UTILISATEUR + "(" + u.UTILISATEUR_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + RESERVATION_DATE + " date, "
            + RESERVATION_PRIX + " INTEGER, "
            + RESERVATION_NBPERSONNES + " INTEGER);";
    public static final String DROP_RESERVATION = "DROP TABLE IF EXISTS " + TABLE_RESERVATION + ";";

    public void ajouterReservation(){
        //TODO ajouter une Reservation
    }

    public void supprimerReservation(int id){
        //TODO supprimer une Reservation
    }

    public void modifierReservation(){
        //TODO modifier une Reservation
    }

    public void selectionnerReservation(int id){
        //TODO selectionner une Reservation
    }
}
