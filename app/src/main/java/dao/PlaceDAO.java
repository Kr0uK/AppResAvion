package dao;

/**
 * Created by RENAUD on 07/09/2016.
 */
public class PlaceDAO {
    static ReservationDAO r;
    static TrajetDAO t;

    /**
     * Entité de la table PLACE
     */
    public static final String PLACE_NUM = "¨PLACE_NUM";

    public static final String TABLE_PLACE = "PLACE";//Nom de la table
    /**
     * Création de la table PLACE
     */
    public static final String CREATE_PLACE = "CREATE TABLE " + TABLE_PLACE + "("
            + r.RESERVATION_ID + " INTEGER NOT NULL REFERENCES " + r.TABLE_RESERVATION + "(" + r.RESERVATION_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + t.TRAJET_ID + " INTEGER NOT NULL REFERENCES " + t.TABLE_TRAJET + "(" + t.TRAJET_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + PLACE_NUM + " INTEGER, "
            + "PRIMARY KEY (" + r.RESERVATION_ID + ", " + t.TRAJET_ID + "));";
    public static final String DROP_PLACE = "DROP TABLE IF EXISTS " + TABLE_PLACE + ";";

    public void ajouterPlace(){
        //TODO ajouter une Place
    }

    public void supprimerPlace(int id){
        //TODO supprimer une Place
    }

    public void modifierPlace(){
        //TODO modifier une Place
    }

    public void selectionnerPlace(int id){
        //TODO selectionner une Place
    }
}
