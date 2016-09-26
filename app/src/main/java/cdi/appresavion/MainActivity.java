package cdi.appresavion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.AeroportDAO;
import dao.AvionDAO;
import dao.DAOBase;
import dao.PlaceDAO;
import dao.ReservationDAO;
import dao.TrajetDAO;
import dao.UtilisateurDAO;
import dbclass.Aeroport;
import dbclass.Avion;
import dbclass.Place;
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
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (isFirstTime()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Ajout dans la base
                            ajoutBase();
                        }
                    }).start();
                }
            }
        }).start();

        // SECTION LOGIN
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent Login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(Login);
            }
        }).start();


    }


    // Redirection dans le onResume()
    @Override
    public void onResume() {
        super.onResume();
        {
            Intent Login = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(Login);
        }
    }


    // Vérif comme quoi la base se remplit qu'une seule fois
    private boolean isFirstTime() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
    }

    /**
     * Ajout dans la base quelque données.
     */
    private void ajoutBase() {
        //Ajout d'utilisateur
        Utilisateur admin = new Utilisateur(0, "admin", "admin", "admin@admin.admin", "0388943632", "0622493390", "admin", "67160", "admin", "admin", "12345678");
        UtilisateurDAO.ajouterUtilisateur(admin);

        //Ajout d'aeroports
        Aeroport aeroport1 = new Aeroport(0, "Charles De Gaulle", "Paris", "France", "CDG", 49.012779, 2.55);
        Aeroport aeroport2 = new Aeroport(0, "West 30th St. Heliport", "New York", "Etats-Unis", "JRA", 40.7545, -74.0071);
        Aeroport aeroport3 = new Aeroport(0, "Gimpo", "Seoul", "Corée du Sud", "GMP", 37.558311, 126.790586);
        Aeroport aeroport4 = new Aeroport(0, "Tokyo Intl", "Tokyo", "Japon", "HND", 35.552258, 139.779694);
        Aeroport aeroport5 = new Aeroport(0, "Los Angeles Intl", "Los Angeles", "Etats-Unis", "LAX", 33.942536, -118.408075);
        Aeroport aeroport6 = new Aeroport(0, "Brussels Gare du Midi", "Bruxelles", "Belgique", "ZYR", 50.8, 4.4);
        Aeroport aeroport7 = new Aeroport(0, "Frankfurt-Main Hauptbahnhof", "Frankfurt", "Allemagne", "ZRB", 50.1070257990375, 8.66276050515751);
        Aeroport aeroport8 = new Aeroport(0, "MOW", "Moscou", "Russie", "MOW", 55.7557, 37.6176);
        AeroportDAO.ajouterAeroport(aeroport1);
        AeroportDAO.ajouterAeroport(aeroport2);
        AeroportDAO.ajouterAeroport(aeroport3);
        AeroportDAO.ajouterAeroport(aeroport4);
        AeroportDAO.ajouterAeroport(aeroport5);
        AeroportDAO.ajouterAeroport(aeroport6);
        AeroportDAO.ajouterAeroport(aeroport7);
        AeroportDAO.ajouterAeroport(aeroport8);

        //Ajout d'avions
        Avion avion1 = new Avion(0, "Boeing 737 600", "Boeing", 132, "Air France");
        Avion avion2 = new Avion(0, "Airbus A340 300", "Airbus", 295, "Air France");
        AvionDAO.ajouterAvion(avion1);
        AvionDAO.ajouterAvion(avion2);

        //Ajout d'un trajet
        Trajet trajet1 = new Trajet(0, 1, 1, 2, DateConvertisseur.stringToDate("2016-10-01 12:30:00"), DateConvertisseur.stringToDate("2016-10-01 20:30:00"), 500);
        Trajet trajet2 = new Trajet(0, 2, 2, 1, DateConvertisseur.stringToDate("2016-10-01 08:30:00"), DateConvertisseur.stringToDate("2016-10-01 16:30:00"), 550);
        Trajet trajet3 = new Trajet(0, 1, 4, 3, DateConvertisseur.stringToDate("2016-10-01 16:00:00"), DateConvertisseur.stringToDate("2016-10-01 22:00:00"), 550);
        Trajet trajet4 = new Trajet(0, 2, 5, 2, DateConvertisseur.stringToDate("2016-10-04 20:45:00"), DateConvertisseur.stringToDate("2016-10-05 06:45:00"), 550);
        Trajet trajet5 = new Trajet(0, 1, 7, 6, DateConvertisseur.stringToDate("2016-10-06 12:00:00"), DateConvertisseur.stringToDate("2016-10-06 16:00:00"), 550);
        Trajet trajet6 = new Trajet(0, 2, 6, 7, DateConvertisseur.stringToDate("2016-10-08 16:00:00"), DateConvertisseur.stringToDate("2016-10-08 21:00:00"), 550);
        Trajet trajet7 = new Trajet(0, 1, 8, 5, DateConvertisseur.stringToDate("2016-10-08 16:00:00"), DateConvertisseur.stringToDate("2016-10-08 21:00:00"), 550);
        Trajet trajet8 = new Trajet(0, 2, 1, 8, DateConvertisseur.stringToDate("2016-10-09 16:00:00"), DateConvertisseur.stringToDate("2016-10-09 21:00:00"), 550);
        Trajet trajet9 = new Trajet(0, 1, 3, 4, DateConvertisseur.stringToDate("2016-10-10 16:00:00"), DateConvertisseur.stringToDate("2016-10-10 21:00:00"), 550);
        TrajetDAO.ajouterTrajet(trajet1);
        TrajetDAO.ajouterTrajet(trajet2);
        TrajetDAO.ajouterTrajet(trajet3);
        TrajetDAO.ajouterTrajet(trajet4);
        TrajetDAO.ajouterTrajet(trajet5);
        TrajetDAO.ajouterTrajet(trajet6);
        TrajetDAO.ajouterTrajet(trajet7);
        TrajetDAO.ajouterTrajet(trajet8);
        TrajetDAO.ajouterTrajet(trajet9);
    }

    private void getAllDb() {
        try {
            //Affichage utilisateur
            ArrayList utilisateurtArrayList = UtilisateurDAO.getAllUtilisateur();
            Iterator<Utilisateur> utilisateurIterator = utilisateurtArrayList.iterator();
            while (utilisateurIterator.hasNext()) {
                Utilisateur utilisateur = utilisateurIterator.next();
                Log.w("TAG", utilisateur.getMail() + " | " + utilisateur.getMdp());
            }

            //Affichage aeroport
            ArrayList aeroportArrayList = AeroportDAO.getAllAeroport();
            Iterator<Aeroport> aeroportIterator = aeroportArrayList.iterator();
            while (aeroportIterator.hasNext()) {
                Aeroport aeroport = aeroportIterator.next();
                Log.w("TAG", aeroport.getId() + " | " + aeroport.getNom() + " | " + aeroport.getPays());
            }

            //Affichage avion
            ArrayList avionArrayList = AvionDAO.getAllAvion();
            Iterator<Avion> avionIterator = avionArrayList.iterator();
            while (avionIterator.hasNext()) {
                Avion avion = avionIterator.next();
                Log.w("TAG", avion.getModele() + " | " + avion.getCompagnie());
            }

            //Affichage trajet
            ArrayList trajetArrayList = TrajetDAO.getAllTrajet();
            Iterator<Trajet> trajetIterator = trajetArrayList.iterator();
            while (trajetIterator.hasNext()) {
                Trajet trajet = trajetIterator.next();
                Log.w("TAG", trajet.getAeroportId() + " | " + trajet.getAerAeroportId() + " | " + DateConvertisseur.dateToString(trajet.getDateDepart()));
            }

            //Affichage reservation
            ArrayList reservationArrayList = ReservationDAO.getAllReservation();
            Iterator<Reservation> reservationIterator = reservationArrayList.iterator();
            while (reservationIterator.hasNext()) {
                Reservation reservation = reservationIterator.next();
                Log.w("TAG", "RESERVATION : Reservation id : " + reservation.getReservationId() + " | Utilisateur id :" + reservation.getUtilisateurId());
            }

            //Affichage place
            ArrayList placeArrayList = PlaceDAO.getAllPlace();
            Iterator<Place> placeIterator = placeArrayList.iterator();
            while (placeIterator.hasNext()) {
                Place place = placeIterator.next();
                Log.w("TAG", "PLACE : Reservation id : " + place.getReservationId() + " | Trajet id :" + place.getTrajetId());
            }

        } catch (Exception e) {
            Log.w("TAG", e);
        }
    }


}