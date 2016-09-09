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

        DAOBase daoBase = new DAOBase(getApplicationContext());

        daoBase.getWDb();

        Utilisateur util =  new Utilisateur("METZ", "Renaud", "test@test.fr", "0388943632", "0622493390", "33 rue de la paix", "67160", "OBERLAUTERBACH", "renaud", "1610");

        UtilisateurDAO.ajouterUtilisateur(util);

        String test = UtilisateurDAO.selectionnerUtilisateur(1).getNom().toString();
        Log.w("TEST",test);
    }
}
