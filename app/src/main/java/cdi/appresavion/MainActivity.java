package cdi.appresavion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


import dao.AeroportDAO;
import dao.AvionDAO;
import dao.DAOBase;
import dao.ReservationDAO;
import dao.TrajetDAO;
import dao.UtilisateurDAO;
import dbclass.Aeroport;
import dbclass.Avion;
import dbclass.Reservation;
import dbclass.Trajet;
import dbclass.Utilisateur;
import shell.DateConvertisseur;


public class MainActivity extends AppCompatActivity { //implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


/**
 * Thread qui va gérer la création et le premier remplissage de la base
 */

        //Création de la base;
        Log.w("TAG", "Avant de lancer le thread");
        new Thread(new Runnable() {
            @Override
            public void run() {
                DAOBase daoBase = new DAOBase(getApplicationContext());
                daoBase.getWDb();

                Log.w("TAG", "On rentre bien dans le thread");

                Utilisateur utilisateur1 = new Utilisateur("METZ", "Renaud", "r@", "0388943632", "0622493390", "33 rue de la paix", "67160", "OBERLAUTERBACH", "renaud", "12345678");
                Utilisateur utilisateur2 = new Utilisateur("Kenobi", "Obi-Wan", "obiwan@kenobi.jedi", "0123456789", "0678945632", "35 rue de la paix", "70420", "Perpète-les-Bains", "obiwan", "12345678");
                UtilisateurDAO.ajouterUtilisateur(utilisateur1);
                Log.w("TAG", utilisateur1.toString());
                UtilisateurDAO.ajouterUtilisateur(utilisateur2);
                Log.w("TAG", utilisateur2.toString());

                //Ajout d'aeroports
                Aeroport aeroportDepart = new Aeroport("Charles De Gaulle", "Paris", "France", "CDG", 49.012779, 2.55);
                Aeroport aeroportArrivee = new Aeroport("West 30th St. Heliport", "New York", "Etats-Unis", "JRA", 40.7545, -74.0071);
                AeroportDAO.ajouterAeroport(aeroportDepart);
                Log.w("TAG", aeroportDepart.toString());

                AeroportDAO.ajouterAeroport(aeroportArrivee);
                Log.w("TAG", aeroportArrivee.toString());

                //Ajout d'un avion
                Avion avion = new Avion("Boeing 737 600", "Boeing", 132, "Air France");
                AvionDAO.ajouterAvion(avion);
                Log.w("TAG", avion.toString());

                //Ajout d'un trajet
                Trajet trajet1 = new Trajet(1, 1, 2, DateConvertisseur.stringToDate("10/09/2016 21:22"), DateConvertisseur.stringToDate("11/09/2016 14:21"));
                Trajet trajet2 = new Trajet(1, 1, 2, DateConvertisseur.stringToDate("24/10/2016 22:22"), DateConvertisseur.stringToDate("25/10/2016 15:21"));
                TrajetDAO.ajouterTrajet(trajet1);
                Log.w("TAG", trajet1.toString());
                TrajetDAO.ajouterTrajet(trajet2);
                Log.w("TAG", trajet2.toString());


                //Ajout d'une reservation/place
                Reservation reservation1 = new Reservation(1, DateConvertisseur.dateSysDate(), 384, 1);
                Reservation reservation2 = new Reservation(1, DateConvertisseur.dateSysDate(), 384, 1);
                Reservation reservation3 = new Reservation(2, DateConvertisseur.dateSysDate(), 384, 2);
                ReservationDAO.ajouterReservationPlace(reservation1, 1);
                ReservationDAO.ajouterReservationPlace(reservation2, 2);
                ReservationDAO.ajouterReservationPlace(reservation3, 2);

            }
        }).start();

                // SECTION LOGIN

                Intent Login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(Login);





    }

    class NavDetails {
        /**
         * id contenant le titre de la fenêtre
         */
        public final int titleId;

        /**
         * id contenant la description de la fenêtre.
         */
        public final int descriptionId;

        /**
         * le nom de la classe ouvrant la fenêtre
         */
        public final Class<? extends AppCompatActivity> activityClass;

        public NavDetails(
                int titleId, int descriptionId, Class<? extends AppCompatActivity> activityClass) {
            this.titleId = titleId;
            this.descriptionId = descriptionId;
            this.activityClass = activityClass;
        }
    }

    class Navigation {

        /**
         * Cette classe ne dois pas être instanciée.
         */
        private Navigation() {
        }

    }

}