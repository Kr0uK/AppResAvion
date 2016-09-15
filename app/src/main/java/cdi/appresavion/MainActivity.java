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
import dao.DAOBase;
import dao.ReservationDAO;
import dbclass.Aeroport;
import dbclass.Reservation;
import shell.DateConvertisseur;
import shell.ThreadHandler;


public class MainActivity extends AppCompatActivity { //implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView) findViewById(R.id.list);


        Thread FirstBoot = new Thread(new ThreadHandler(getApplicationContext()));
        //Création de la base
        Log.w("TAG", "Avant de lancer le thread");
        FirstBoot.run();







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