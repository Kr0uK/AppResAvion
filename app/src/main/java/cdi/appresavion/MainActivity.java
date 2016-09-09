package cdi.appresavion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import dao.DAOBase;
import dao.UtilisateurDAO;
import dbClass.Utilisateur;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création de la base
        DAOBase daoBase = new DAOBase(getApplicationContext());
        daoBase.getWDb();

        //Ajout d'un utilisateur util
        Utilisateur util =  new Utilisateur("METZ", "Renaud", "renaudmtz@gmail.com", "0388943632", "0622493390", "33 rue de la paix", "67160", "OBERLAUTERBACH", "renaud", "1610");
        //UtilisateurDAO.ajouterUtilisateur(util);

        //Modifier un utilisateur
        UtilisateurDAO.modifierUtilisateur(util, 1);

        //Affichage d'un utilisateur en fonction de son ID /!\ CRASH SI LA LIGNE EST VIDE /!\
        Utilisateur utilTest = UtilisateurDAO.selectionnerUtilisateur(1);
        String testString = utilTest.getNom() + " " + utilTest.getPrenom() + " " + utilTest.getMail();
        Log.w("TEST",testString);

        //Supprimer un utilisateur
        UtilisateurDAO.supprimerUtilisateur(3);
    }
}
