package dao;

import dbClass.Avion;

/**
 * Created by RENAUD on 07/09/2016.
 */
public class AvionDAO {
    public static final String AVION_ID = "AVION_ID";
    public static final String AVION_MODELE = "AVION_MODELE";
    public static final String AVION_CONSTRUCTEUR = "AVION_CONSTRUCTEUR";
    public static final String AVION_NBPLACES = "AVION_NBPLACES";
    public static final String AVION_COMPAGNIE = "AVION_COMPAGNIE";

    public static final String TABLE_AVION = "AVION";
    public static final String CREATE_AVION = "CREATE TABLE " + TABLE_AVION + "("
            + AVION_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + AVION_MODELE + " VARCHAR(50), "
            + AVION_CONSTRUCTEUR + " VARCHAR(60), "
            + AVION_NBPLACES + " INTEGER, "
            + AVION_COMPAGNIE + "VARCHAR(60));";
    public static final String DROP_AVION = "DROP TABLE IF EXISTS " + TABLE_AVION + ";";

    public void ajouterAvion(Avion a){
        //TODO ajouter un Avion
    }

    public void supprimerAvion(int id){
        //TODO supprimer un Avion
    }

    public void modifierAvion(Avion a){
        //TODO modifier un Avion
    }

    public void selectionnerAvion(int id){
        //TODO selectionner un Avion
    }
}
