package dao;


import android.content.ContentValues;
import android.database.Cursor;

import dbClass.Utilisateur;

/**
 * Created by RENAUD on 06/09/2016.
 */
public class UtilisateurDAO {

    //Entité de la table UTILISATEUR + numéro de la colonne pour la sélection
    public static final String UTILISATEUR_ID = "UTILISATEUR_ID";
    public static final int NUM_UTILISATEUR_ID = 0;
    public static final String UTILISATEUR_NOM = "UTILISATEUR_NOM";
    public static final int NUM_UTILISATEUR_NOM = 1;
    public static final String UTILISATEUR_PRENOM = "UTILISATEUR_PRENOM";
    public static final int NUM_UTILISATEUR_PRENOM = 2;
    public static final String UTILISATEUR_MAIL = "UTILISATEUR_MAIL";
    public static final int NUM_UTILISATEUR_MAIL = 3;
    public static final String UTILISATEUR_TELEPHONE = "UTILISATEUR_TELEPHONE";
    public static final int NUM_UTILISATEUR_TELEPHONE = 4;
    public static final String UTILISATEUR_MOBILE = "UTILISATEUR_MOBILE";
    public static final int NUM_UTILISATEUR_MOBILE = 5;
    public static final String UTILISATEUR_ADRESSE = "UTILISATEUR_ADRESSE";
    public static final int NUM_UTILISATEUR_ADRESSE = 6;
    public static final String UTILISATEUR_CP = "UTILISATEUR_CP";
    public static final int NUM_UTILISATEUR_CP = 7;
    public static final String UTILISATEUR_VILLE = "UTILISATEUR_VILLE";
    public static final int NUM_UTILISATEUR_VILLE = 8;
    public static final String UTILISATEUR_USERNAME = "UTILISATEUR_USERNAME";
    public static final int NUM_UTILISATEUR_USERNAME = 9;
    public static final String UTILISATEUR_MDP = "UTILISATEUR_MDP";
    public static final int NUM_UTILISATEUR_MDP = 10;

    //Nom de la table
    public static final String TABLE_UTILISATEUR = "UTILISATEUR";

    //Création de la table UTILISATEUR
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
    //Suppression de la table UTILISATEUR si elle existe
    public static final String  DROP_UTILISATEUR = "DROP TABLE IF EXISTS " + TABLE_UTILISATEUR + ";";

    /**
     * Ajout d'un utilisateur dans la bdd a partir de l'objet Utilisateur
     * @param u Utilisateur
     */
    public static void ajouterUtilisateur(Utilisateur u){
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Utilisateur
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

        //Insert dans la base
        DAOBase.getWDb().insert(TABLE_UTILISATEUR, null, value);
    }

    /**
     * Supprime un utilisateur a partir d'un id
     * @param id
     */
    public static void supprimerUtilisateur(int id){
        DAOBase.getWDb().delete(TABLE_UTILISATEUR, UTILISATEUR_ID + " = " + id,null);
    }

    /**
     * Modifier un utilisateur a partir d'un id et de l'objet Utilisateur
     * @param u utilisateur
     * @param id
     */
    public static void modifierUtilisateur(Utilisateur u, int id){
        //TODO /!\ si un champ est vide dans l'objet, il remplace par null a faire dans interface
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Utilisateur
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

        //Update dans la base
        DAOBase.getWDb().update(TABLE_UTILISATEUR, value, UTILISATEUR_ID + " = " + id, null);
    }

    /**
     * Création du curseur qui va parcourir la base
     * @param id
     * @return utilisateur
     */
    public static Utilisateur selectionnerUtilisateur(int id){
        Cursor c = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_UTILISATEUR + " WHERE " + UTILISATEUR_ID + " = " + id, null);
        return cursorToUtilisateur(c);
    }

    /**
     * Transformation du curseur en Utilisateur
     * @param c cursor
     * @return utilisateur
     */
    public static Utilisateur cursorToUtilisateur(Cursor c){
        //Verifie qu'il y a une ligne
        if (c.getCount() == 0){
            return null;
        }
        //Déplace le curseur a la valeur 0
        c.moveToFirst();
        Utilisateur utilisateur = new Utilisateur();
        //Ajout les informations du curseur dans l'objet utilisateur
        utilisateur.setId(c.getInt(NUM_UTILISATEUR_ID));
        utilisateur.setNom(c.getString(NUM_UTILISATEUR_NOM));
        utilisateur.setPrenom(c.getString(NUM_UTILISATEUR_PRENOM));
        utilisateur.setMail(c.getString(NUM_UTILISATEUR_MAIL));
        utilisateur.setTelephone(c.getString(NUM_UTILISATEUR_TELEPHONE));
        utilisateur.setMobile(c.getString(NUM_UTILISATEUR_MOBILE));
        utilisateur.setAdresse(c.getString(NUM_UTILISATEUR_ADRESSE));
        utilisateur.setCp(c.getString(NUM_UTILISATEUR_CP));
        utilisateur.setVille(c.getString(NUM_UTILISATEUR_VILLE));
        utilisateur.setUsername(c.getString(NUM_UTILISATEUR_USERNAME));
        utilisateur.setMdp(c.getString(NUM_UTILISATEUR_MDP));

        c.close();
        return utilisateur;
    }
}
