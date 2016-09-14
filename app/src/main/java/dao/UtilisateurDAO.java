package dao;


import android.content.ContentValues;
import android.database.Cursor;

import dbclass.Utilisateur;

/**
 * Created by bigwanjeog.
 * 06/09/2016
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
     * Ajout d'un utilisateur dans la bdd a partir de l'objet Utilisateur.
     * @param utilisateurAdd Utilisateur
     */
    public static void ajouterUtilisateur(Utilisateur utilisateurAdd) {
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Utilisateur
        value.put(UTILISATEUR_NOM, utilisateurAdd.getNom());
        value.put(UTILISATEUR_PRENOM, utilisateurAdd.getPrenom());
        value.put(UTILISATEUR_MAIL, utilisateurAdd.getMail());
        value.put(UTILISATEUR_TELEPHONE, utilisateurAdd.getTelephone());
        value.put(UTILISATEUR_MOBILE, utilisateurAdd.getMobile());
        value.put(UTILISATEUR_ADRESSE, utilisateurAdd.getAdresse());
        value.put(UTILISATEUR_CP, utilisateurAdd.getCp());
        value.put(UTILISATEUR_VILLE, utilisateurAdd.getVille());
        value.put(UTILISATEUR_USERNAME, utilisateurAdd.getUsername());
        value.put(UTILISATEUR_MDP, utilisateurAdd.getMdp());

        //Insert dans la base
        DAOBase.getWDb().insert(TABLE_UTILISATEUR, null, value);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Supprime un utilisateur a partir d'un id.
     * @param id id id de l'utilisateur
     */
    public static void supprimerUtilisateur(int id) {
        DAOBase.getWDb().delete(TABLE_UTILISATEUR, UTILISATEUR_ID + " = " + id, null);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Modifier un utilisateur a partir d'un id et de l'objet Utilisateur.
     * @param utilisateurUpdate utilisateur
     * @param id id de l'utilisateur
     */
    public static void modifierUtilisateur(Utilisateur utilisateurUpdate, int id) {
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Utilisateur
        value.put(UTILISATEUR_NOM, utilisateurUpdate.getNom());
        value.put(UTILISATEUR_PRENOM, utilisateurUpdate.getPrenom());
        value.put(UTILISATEUR_MAIL, utilisateurUpdate.getMail());
        value.put(UTILISATEUR_TELEPHONE, utilisateurUpdate.getTelephone());
        value.put(UTILISATEUR_MOBILE, utilisateurUpdate.getMobile());
        value.put(UTILISATEUR_ADRESSE, utilisateurUpdate.getAdresse());
        value.put(UTILISATEUR_CP, utilisateurUpdate.getCp());
        value.put(UTILISATEUR_VILLE, utilisateurUpdate.getVille());
        value.put(UTILISATEUR_USERNAME, utilisateurUpdate.getUsername());
        value.put(UTILISATEUR_MDP, utilisateurUpdate.getMdp());

        //Update dans la base
        DAOBase.getWDb().update(TABLE_UTILISATEUR, value, UTILISATEUR_ID + " = " + id, null);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Création du curseur qui va parcourir la base.
     * @param id id id de l'utilisateur
     * @return utilisateur
     */
    public static Utilisateur selectionnerUtilisateur(int id) {
        Cursor c = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_UTILISATEUR + " WHERE " + UTILISATEUR_ID + " = " + id, null);
        return cursorToUtilisateur(c);
    }

    /**
     * Transformation du curseur en Utilisateur.
     * @param c cursor
     * @return utilisateur
     */
    public static Utilisateur cursorToUtilisateur(Cursor c) {
        //Verifie qu'il y a une ligne
        if (c.getCount() == 0) {
            return null;
        }
        //Déplace le curseur a la valeur 0
        c.moveToFirst();

        //Ajoute les informations du curseur dans l'objet utilisateur
        Utilisateur utilisateurSelect = new Utilisateur();
        utilisateurSelect.setId(c.getInt(NUM_UTILISATEUR_ID));
        utilisateurSelect.setNom(c.getString(NUM_UTILISATEUR_NOM));
        utilisateurSelect.setPrenom(c.getString(NUM_UTILISATEUR_PRENOM));
        utilisateurSelect.setMail(c.getString(NUM_UTILISATEUR_MAIL));
        utilisateurSelect.setTelephone(c.getString(NUM_UTILISATEUR_TELEPHONE));
        utilisateurSelect.setMobile(c.getString(NUM_UTILISATEUR_MOBILE));
        utilisateurSelect.setAdresse(c.getString(NUM_UTILISATEUR_ADRESSE));
        utilisateurSelect.setCp(c.getString(NUM_UTILISATEUR_CP));
        utilisateurSelect.setVille(c.getString(NUM_UTILISATEUR_VILLE));
        utilisateurSelect.setUsername(c.getString(NUM_UTILISATEUR_USERNAME));
        utilisateurSelect.setMdp(c.getString(NUM_UTILISATEUR_MDP));

        c.close();
        return utilisateurSelect;
    }
}
