package dao;

/**
 * Created by RENAUD on 07/09/2016.
 */
public class TrajetDAO {
    static AvionDAO av;
    static AeroportDAO ae;

    /**
     * Entité de la table TRAJET
     */
    public static final String TRAJET_ID = "TRAJET_ID";
    public static final String TRAJET_DATE_DEPART = "TRAJET_DATE_DEPART";
    public static final String TRAJET_HEURE_DEPART = "TRAJET_HEURE_DEPART";
    public static final String TRAJET_HEURE_ARRIVEE = "TRAJET_HEURE_ARRIVEE";
    public static final String AER_AEROPORT_ID = "AER_AEROPORT_ID";

    public static final String TABLE_TRAJET = "TRAJET";//Nom de la table
    /**
     * Création de la table TRAJET
     */
    public static final String CREATE_TRAJET = "CREATE TABLE " + TABLE_TRAJET + "("
            + TRAJET_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + av.AVION_ID + " INTEGER NOT NULL REFERENCES " + av.TABLE_AVION + "(" + av.AVION_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + ae.AEROPORT_ID + "INTEGER NOT NULL REFERENCES " + ae.TABLE_AEROPORT + "(" + ae.AEROPORT_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + AER_AEROPORT_ID + "INTEGER NOT NULL REFERENCES " + ae.TABLE_AEROPORT + "(" + ae.AEROPORT_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + TRAJET_DATE_DEPART + " date, "
            + TRAJET_HEURE_DEPART + " time, "
            + TRAJET_HEURE_ARRIVEE + " time);";
    public static final String DROP_TRAJET = "DROP TABLE IF EXISTS " + TABLE_TRAJET + ";";

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
