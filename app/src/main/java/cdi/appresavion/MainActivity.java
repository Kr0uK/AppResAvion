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
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Création de la base
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

                            //Lecture de toutes les données de la base
                            getAllDb();

                            //Recherche sur aeroport
                            ArrayList aeroportArrayList = AeroportDAO.getAeroportWithNom("Etat");
                            Iterator<Aeroport> aeroportIterator = aeroportArrayList.iterator();
                            while (aeroportIterator.hasNext()) {
                                Aeroport aeroport = aeroportIterator.next();
                                Log.w("TAG", "Ceci est le test d'aeroport " + aeroport.getId() + " | " + aeroport.getNom() + " | " +aeroport.getPays());
                            }

                            //Recup de trajet + avion + aeroport avec date choisis
                            getTrajetInfos(1, 2, DateConvertisseur.stringToDate("2016-09-19 10:00:00"));
                            //Recup de trajet + avion + aeroport avec date sys
                            //getTrajetInfos(1, 2, DateConvertisseur.dateSysDate());

                        }
                    }).start();
                }
            }
        }).start();

        // SECTION LOGIN
        new Thread(new Runnable() {
            @Override
            public void run() {
                /*Intent Login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(Login);*/

                int idTrajet = 6;

                Intent detail = new Intent(MainActivity.this, DetailsActivity.class);
                //Stockage de l'idTrajet
                detail.putExtra("idTrajet", Integer.toString(idTrajet));
                startActivity(detail);
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

    private void getTrajetInfos(int aeroportDepart, int aeroportArrivee, Date dateDepart) {
        ArrayList trajetArrayList = TrajetDAO.getTrajetWhere(aeroportDepart, aeroportArrivee, dateDepart);
        Iterator<Trajet> trajetIterator = trajetArrayList.iterator();
        while (trajetIterator.hasNext()) {
            Trajet trajet = trajetIterator.next();

            //Création de l'objet qui contient l'aeroport de depart
            Aeroport aeDep = AeroportDAO.selectionnerAeroport(trajet.getAeroportId());

            //Création de l'objet qui contient l'aeroport d'arrivee
            Aeroport aeArr = AeroportDAO.selectionnerAeroport(trajet.getAerAeroportId());

            //Création de l'objet qui contient l'avion
            Avion avion = AvionDAO.selectionnerAvion(trajet.getAvionId());

            //Affichage
            Log.w("TAG", "Ceci est le test de trajet " + aeDep.getNom() + " | " + aeArr.getNom() + " | " + DateConvertisseur.dateToStringFormatShow(trajet.getDateDepart()));
        }
    }
    private void ajoutBase() {
        Utilisateur utilisateur1 = new Utilisateur(0, "METZ", "Renaud", "r@", "0388943632", "0622493390", "33 rue de la paix", "67160", "OBERLAUTERBACH", "renaud", "12345678");
        Utilisateur utilisateur2 = new Utilisateur(0, "Kenobi", "Obi-Wan", "obiwan@kenobi.jedi", "0123456789", "0678945632", "35 rue de la paix", "70420", "Perpète-les-Bains", "obiwan", "12345678");
        UtilisateurDAO.ajouterUtilisateur(utilisateur1);
        UtilisateurDAO.ajouterUtilisateur(utilisateur2);

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

        //Ajout d'un avion
        Avion avion = new Avion(0, "Boeing 737 600", "Boeing", 132, "Air France");
        AvionDAO.ajouterAvion(avion);

        //Ajout d'un trajet
        Trajet trajet1 = new Trajet(0, 1, 1, 2, DateConvertisseur.stringToDate("2016-09-10 21:22:00"), DateConvertisseur.stringToDate("2016-10-20 14:21:00"), 500);
        Trajet trajet2 = new Trajet(0, 1, 1, 2, DateConvertisseur.stringToDate("2016-10-24 22:22:00"), DateConvertisseur.stringToDate("2016-10-25 15:21:00"), 550);
        Trajet trajet3 = new Trajet(0, 1, 1, 2, DateConvertisseur.stringToDate("2016-10-24 20:22:00"), DateConvertisseur.stringToDate("2016-10-25 15:21:00"), 550);
        Trajet trajet4 = new Trajet(0, 1, 1, 2, DateConvertisseur.stringToDate("2016-10-24 23:22:00"), DateConvertisseur.stringToDate("2016-10-25 15:21:00"), 550);
        Trajet trajet5 = new Trajet(0, 1, 7, 6, DateConvertisseur.stringToDate("2016-09-19 12:00:00"), DateConvertisseur.stringToDate("2016-09-20 15:21:00"), 550);
        Trajet trajet6 = new Trajet(0, 1, 6, 7, DateConvertisseur.stringToDate("2016-09-19 16:00:00"), DateConvertisseur.stringToDate("2016-09-20 15:21:00"), 550);
        TrajetDAO.ajouterTrajet(trajet1);
        TrajetDAO.ajouterTrajet(trajet2);
        TrajetDAO.ajouterTrajet(trajet3);
        TrajetDAO.ajouterTrajet(trajet4);
        TrajetDAO.ajouterTrajet(trajet5);
        TrajetDAO.ajouterTrajet(trajet6);


        //Ajout d'une reservation/place
        Reservation reservation1 = new Reservation(0, 1, DateConvertisseur.dateSysDate(), 384, 1);
        Reservation reservation2 = new Reservation(0, 2, DateConvertisseur.dateSysDate(), 384, 2);
        ReservationDAO.ajouterReservationPlace(reservation1, 1);
        ReservationDAO.ajouterReservationPlace(reservation2, 2);
        ReservationDAO.ajouterReservationPlace(reservation1, 2);
        ReservationDAO.ajouterReservationPlace(reservation1, 3);
        ReservationDAO.ajouterReservationPlace(reservation1, 4);
        ReservationDAO.ajouterReservationPlace(reservation1, 5);
        ReservationDAO.ajouterReservationPlace(reservation1, 6);
    }

    private void getAllDb() {
        try{
            //Affichage utilisateur
            ArrayList utilisateurtArrayList = UtilisateurDAO.getAllUtilisateur();
            Iterator<Utilisateur> utilisateurIterator = utilisateurtArrayList.iterator();
            while (utilisateurIterator.hasNext()) {
                Utilisateur utilisateur = utilisateurIterator.next();
                Log.w("TAG", utilisateur.getMail() + " | " +utilisateur.getMdp());
            }

            //Affichage aeroport
            ArrayList aeroportArrayList = AeroportDAO.getAllAeroport();
            Iterator<Aeroport> aeroportIterator = aeroportArrayList.iterator();
            while (aeroportIterator.hasNext()) {
                Aeroport aeroport = aeroportIterator.next();
                Log.w("TAG",aeroport.getId() + " | " + aeroport.getNom() + " | " +aeroport.getPays());
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
                Trajet trajet= trajetIterator.next();
                Log.w("TAG", trajet.getAeroportId() + " | " + trajet.getAerAeroportId() + " | " + DateConvertisseur.dateToString(trajet.getDateDepart()));
            }

            //Affichage reservation
            ArrayList reservationArrayList = ReservationDAO.getAllReservation();
            Iterator<Reservation> reservationIterator = reservationArrayList.iterator();
            while (reservationIterator.hasNext()) {
                Reservation reservation = reservationIterator.next();
                Log.w("TAG", "RESERVATION : Reservation id : " + reservation.getReservationId() + " | Utilisateur id :"  + reservation.getUtilisateurId());
            }

            //Affichage place
            ArrayList placeArrayList = PlaceDAO.getAllPlace();
            Iterator<Place> placeIterator = placeArrayList.iterator();
            while (placeIterator.hasNext()) {
                Place place = placeIterator.next();
                Log.w("TAG", "PLACE : Reservation id : " + place.getReservationId() + " | Trajet id :"  + place.getTrajetId());
            }

        } catch (Exception e){
            Log.w("TAG", e);
        }
    }
}