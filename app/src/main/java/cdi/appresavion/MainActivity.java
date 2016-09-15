package cdi.appresavion;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

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
        ListView list = (ListView) findViewById(R.id.list);
        final boolean firstBoot = false;

        //Création de la base;
        Log.w("TAG", "Avant de lancer le thread");
        new Thread(new Runnable() {
            @Override
            public void run() {
                DAOBase daoBase = new DAOBase(getApplicationContext());
                daoBase.getWDb();

                Log.w("TAG", "On rentre bien dans le thread");

                Utilisateur utilisateur1 = new Utilisateur("METZ", "Renaud", "r@", "0388943632", "0622493390", "33 rue de la paix", "67160", "OBERLAUTERBACH", "renaud", "12345678");
                Utilisateur utilisateur2 = new Utilisateur("METZ", "Renaud", "m@", "0388943632", "0622493390", "33 rue de la paix", "67160", "OBERLAUTERBACH", "renaud", "12345678");
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




        /*
        //try and catch si l'utilisateur selectionner est vide
        try{
            Utilisateur utilTest = UtilisateurDAO.selectionnerUtilisateur(1);
            String testString = utilTest.getNom() + " " + utilTest.getPrenom() + " " + utilTest.getMail();
            Log.w("TEST",testString);

        } catch (Exception e){
            Log.w("TEST", e);
        }*/


        //Supprimer un utilisateur
        //UtilisateurDAO.supprimerUtilisateur(3);
/*
        Navigationi lol = new Nevigationi();
        ListAdapter adapter = new ListAdapter(this, lol.NAVBAR);

        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
        list.setEmptyView(findViewById(R.id.empty));
*/
        // SECTION LOGIN

        Intent Login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(Login);

        // SECTION API GOOGLE MAP
        /*
        Geoloc_Aeroport Aeroport = new Geoloc_Aeroport("AFPA Frouard",48.776524,6.1393364);
        Intent GMap = new Intent(MainActivity.this, MapsActivity.class);
        startActivity(GMap);
        */
    }

/*
    private static class CustomArrayAdapter extends ArrayAdapter<DemoDetails> {
        public CustomArrayAdapter(Context context, DemoDetails[] demos) {
            super(context, R.layout.feature, R.id.title, demos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            FeatureView featureView;
            if (convertView instanceof FeatureView) {
                featureView = (FeatureView) convertView;
            } else {
                featureView = new FeatureView(getContext());
            }

            DemoDetails demo = getItem(position);

            featureView.setTitleId(demo.titleId);
            featureView.setDescriptionId(demo.descriptionId);

            Resources resources = getContext().getResources();
            String title = resources.getString(demo.titleId);
            String description = resources.getString(demo.descriptionId);
            featureView.setContentDescription(title + ". " + description);

            return featureView;
        }
    }
*/
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
/*
    public static final Navigation[] NAVBAR = {
            new Navigation(R.string.GMap_label,
                    R.string.GMap_description,
                    MapsActivity.class),
    };
*/
}