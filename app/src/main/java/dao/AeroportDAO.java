package dao;

import dbClass.Aeroport;

/**
 * Created by RENAUD on 07/09/2016.
 */
public class AeroportDAO {
    /**
     * Entité de la table AEROPORT
     */
    public static final String AEROPORT_ID = "AEROPORT_ID";
    public static final String AEROPORT_NOM = "AEROPORT_NOM";
    public static final String AEROPORT_CODE = "AEROPORT_CODE";
    public static final String AEROPORT_LOCALISATION = "AEROPORT_LOCALISATION";
    public static final String AEROPORT_CP = "Aeroport_CP";
    public static final String AEROPORT_VILLE = "AEROPORT_VILLE";
    public static final String AEROPORT_TELEPHONE = "AEROPORT_TELEPHONE";
    public static final String AEROPORT_PAYS = "AEROPORT_PAYS";

    public static final String TABLE_AEROPORT = "AEROPORT";//Nom de la table
    /**
     * Création de la table AEROPORT
     */
    public static final String CREATE_AEROPORT = "CREATE TABLE " + TABLE_AEROPORT + "("
            + AEROPORT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + AEROPORT_NOM + " VARCHAR(100), "
            + AEROPORT_CODE + " CHAR(3), "
            + AEROPORT_LOCALISATION + " VARCHAR(100), "
            + AEROPORT_CP + " VARCHAR(10), "
            + AEROPORT_VILLE + " VARCHAR(80), "
            + AEROPORT_TELEPHONE + " VARCHAR(15), "
            + AEROPORT_PAYS + " VARCHAR(100));";
    public static final String DROP_AEROPORT = "DROP TABLE IF EXISTS " + TABLE_AEROPORT + ";";

    public void ajouterAeroport(Aeroport a){
        //TODO ajouter un Aeroport
    }

    public void supprimerAeroport(int id){
        //TODO supprimer un Aeroport
    }

    public void modifierAeroport(Aeroport a){
        //TODO modifier un Aeroport
    }

    public void selectionnerAeroport(int id){
        //TODO selectionner un Aeroport
    }
}
