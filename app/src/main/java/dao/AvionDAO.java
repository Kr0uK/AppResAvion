package dao;

import android.content.ContentValues;
import android.database.Cursor;

import dbClass.Avion;

/**
 * Created by bigwanjeog.
 * 07/09/2016
 */
public class AvionDAO {

    //Entité de la table Avion + numéro de la colonne pour la sélection
    public static final String AVION_ID = "AVION_ID";
    public static final int NUM_AVION_ID = 0;
    public static final String AVION_MODELE = "AVION_MODELE";
    public static final int NUM_AVION_MODELE = 1;
    public static final String AVION_CONSTRUCTEUR = "AVION_CONSTRUCTEUR";
    public static final int NUM_AVION_CONSTRUCTEUR = 2;
    public static final String AVION_NBPLACES = "AVION_NBPLACES";
    public static final int NUM_AVION_NBPLACES = 3;
    public static final String AVION_COMPAGNIE = "AVION_COMPAGNIE";
    public static final int NUM_AVION_COMPAGNIE = 4;

    //Nom de la table
    public static final String TABLE_AVION = "AVION";

    //Création de la table AVION
    public static final String CREATE_AVION = "CREATE TABLE " + TABLE_AVION + "("
            + AVION_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
            + AVION_MODELE + " VARCHAR(50), "
            + AVION_CONSTRUCTEUR + " VARCHAR(60), "
            + AVION_NBPLACES + " INTEGER, "
            + AVION_COMPAGNIE + " VARCHAR(60));";
    //Suppresion de la table si elle existe déjà
    public static final String DROP_AVION = "DROP TABLE IF EXISTS " + TABLE_AVION + ";";

    /**
     * Ajout d'un avion.
     * @param avionAdd objet avion
     */
    public static void ajouterAvion(Avion avionAdd) {
        ContentValues value = new ContentValues();

        //Récupération des valeurs dans l'objet Avion
        value.put(AVION_MODELE, avionAdd.getModele());
        value.put(AVION_CONSTRUCTEUR, avionAdd.getConstructeur());
        value.put(AVION_NBPLACES, avionAdd.getNbPlaces());
        value.put(AVION_COMPAGNIE, avionAdd.getCompagnie());

        //Insert dans la base
        DAOBase.getWDb().insert(TABLE_AVION, null, value);

        //Fermeture de la connexion a la bdd
        DAOBase.close();
    }

    /**
     * Création du curseur qui va parcourir la base.
     * @param id id id de l'Avion
     * @return Avion
     */
    public static Avion selectionnerAvion(int id) {
        Cursor c = DAOBase.getRDb().rawQuery("SELECT * FROM " + TABLE_AVION + " WHERE " + AVION_ID + " = " + id, null);
        return cursorToAvion(c);
    }

    /**
     * Transformation du curseur en Avion.
     * @param c cursor
     * @return Avion
     */
    public static Avion cursorToAvion(Cursor c) {
        //Verifie qu'il y a une ligne
        if (c.getCount() == 0) {
            return null;
        }
        //Déplace le curseur a la valeur 0
        c.moveToFirst();

        //Ajoute les informations du curseur dans l'objet Avion
        Avion avionSelect = new Avion();
        avionSelect.setId(c.getInt(NUM_AVION_ID));
        avionSelect.setModele(c.getString(NUM_AVION_MODELE));
        avionSelect.setConstructeur(c.getString(NUM_AVION_CONSTRUCTEUR));
        avionSelect.setNbPlaces(c.getInt(NUM_AVION_NBPLACES));
        avionSelect.setCompagnie(c.getString(NUM_AVION_COMPAGNIE));

        c.close();
        return avionSelect;
    }
}
