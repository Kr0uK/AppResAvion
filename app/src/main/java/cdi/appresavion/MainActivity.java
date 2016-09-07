package cdi.appresavion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dbClass.Utilisateur;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DatabaseHandler(this);

        Utilisateur util =  new Utilisateur("METZ", "Renaud", "test@test.fr", "0388943632", "0622493390", "33 rue de la paix", "67160", "OBERLAUTERBACH", "renaud", "1610");
    }
}
