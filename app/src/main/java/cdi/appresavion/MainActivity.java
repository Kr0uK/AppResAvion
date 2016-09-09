package cdi.appresavion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import dao.DAOBase;
import dao.UtilisateurDAO;
import dbClass.Utilisateur;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création de la base
        DAOBase daoBase = new DAOBase(getApplicationContext());
        daoBase.getWDb();

        //Ajout d'un utilisateur util
        Utilisateur util =  new Utilisateur("METZ", "Renaud", "test@test.fr", "0388943632", "0622493390", "33 rue de la paix", "67160", "OBERLAUTERBACH", "renaud", "1610");
        UtilisateurDAO.ajouterUtilisateur(util);

        //Affichage d'un utilisateur en fonction de son ID
        Utilisateur utilTest = UtilisateurDAO.selectionnerUtilisateur(3);
        String testString = utilTest.getNom() + " " + utilTest.getPrenom() + " " + utilTest.getMail();
        Log.w("TEST",testString);
    }
}
