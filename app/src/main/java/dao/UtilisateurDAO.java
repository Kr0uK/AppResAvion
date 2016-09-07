package dao;

import android.content.ContentValues;

import dbClass.Utilisateur;

/**
 * Created by RENAUD on 06/09/2016.
 */
public class UtilisateurDAO extends DAOBase {
    /**
     * Entité de la table UTILISATEUR
     */
    public static final String UTILISATEUR_ID = "UTILISATEUR_ID";
    public static final String UTILISATEUR_NOM = "UTILISATEUR_NOM";
    public static final String UTILISATEUR_PRENOM = "UTILISATEUR_PRENOM";
    public static final String UTILISATEUR_MAIL = "UTILISATEUR_MAIL";
    public static final String UTILISATEUR_TELEPHONE = "UTILISATEUR_TELEPHONE";
    public static final String UTILISATEUR_MOBILE = "UTILISATEUR_MOBILE";
    public static final String UTILISATEUR_ADRESSE = "UTILISATEUR_ADRESSE";
    public static final String UTILISATEUR_CP = "UTILISATEUR_CP";
    public static final String UTILISATEUR_VILLE = "UTILISATEUR_VILLE";
    public static final String UTILISATEUR_USERNAME = "UTILISATEUR_USERNAME";
    public static final String UTILISATEUR_MDP = "UTILISATEUR_MDP";

    public static final String TABLE_UTILISATEUR = "UTILISATEUR";//Nom de la table
    /**
     * Création de la table UTILISATEUR
     */
    public static final String CREATE_UTILISATEUR = "CREATE TABLE " + TABLE_UTILISATEUR + " ("
            + UTILISATEUR_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + UTILISATEUR_NOM + " VARCHAR(80), "
            + UTILISATEUR_PRENOM + " VARCHAR(80), "
            + UTILISATEUR_MAIL + " VARCHAR(100), "
            + UTILISATEUR_TELEPHONE + " VARCHAR(10), "
            + UTILISATEUR_MOBILE + " VARCHAR(10), "
            + UTILISATEUR_ADRESSE + " VARCHAR(100), "
            + UTILISATEUR_CP + " VARCHAR(5), "
            + UTILISATEUR_VILLE + " VARCHAR(80), "
            + UTILISATEUR_USERNAME + " VARCHAR(20), "
            + UTILISATEUR_MDP + " VARCHAR(20));";
    public static final String  DROP_UTILISATEUR = "DROP TABLE IF EXISTS " + TABLE_UTILISATEUR + ";";

    /**
     * Ajout d'un utilisateur a partir de l'objet Utilisateur
     * @param u
     */
    public void ajouterUtilisateur(Utilisateur u){
        ContentValues value = new ContentValues();
        value.put(UTILISATEUR_NOM, u.getNom());
        value.put(UTILISATEUR_PRENOM, u.getPrenom());
        value.put(UTILISATEUR_MAIL, u.getMail());
        value.put(UTILISATEUR_TELEPHONE, u.getTelephone());
        value.put(UTILISATEUR_MOBILE, u.getMobile());
        value.put(UTILISATEUR_ADRESSE, u.getAdresse());
        value.put(UTILISATEUR_CP, u.getCp());
        value.put(UTILISATEUR_VILLE, u.getVille());
        value.put(UTILISATEUR_USERNAME, u.getUsername());
        value.put(UTILISATEUR_MDP, u.getMdp());
        mDb.insert(TABLE_UTILISATEUR, null, value);
    }

    public void supprimerUtilisateur(int id){
        //TODO supprimer un utilisateur
    }

    public void modifierUtilisateur(Utilisateur u){
        //TODO modifier un utilisateur
    }

    public void selectionnerUtilisateur(int id){
        //TODO selectionner un utilisateur
    }
}
