package cdi.appresavion;
/**
 * Created by Frédéric on 15/09/2016 - Last edit : 21/09/2016
 */

//LIBRAIRIES
import shell.DateConvertisseur;
import dao.AeroportDAO;
import dao.TrajetDAO;
import dbclass.Aeroport;
import dbclass.Trajet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * Affichage des trajets disponibles
 * TODO : -> prendre en compte que si l'on ne viens pas de RechercheActivity (ou que l'envoi est vide), il n'y ai pas de bugs !
 * -> redirection vers DetailsActivity pour reservation (OK)
 */
public class TrajetsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView listViewVol;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trajets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Les Ents-AI", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
         * Récupération des trajets et affichage
         */
        try {
            ctx = this;

            final List listVol = new ArrayList();

            // TODO
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //Récupération des choix de l'utilisateur pour la sélection du trajet aerien souhaité :
                    Choix_Avion PrefVol = new Choix_Avion();
                    // Intérrogation des trajets dans la BDD avec les critères de l'utilisateur
                    ArrayList trajettArrayList = TrajetDAO.getTrajetWhere(PrefVol.getAeroDepId(),PrefVol.getAeroArrId(), DateConvertisseur.stringToDate(PrefVol.getAeroDateDep()));
                    Iterator<Trajet> trajetIterator = trajettArrayList.iterator();
                    // Stockage pour affichage des données récupérées
                    while (trajetIterator.hasNext()) {
                        Trajet trajet = trajetIterator.next();
                        Aeroport aeroport = new Aeroport();
                        aeroport = AeroportDAO.selectionnerAeroport(trajet.getAeroportId());
                        // TODO : id du trajet a recup -> revoir les objets pour ajouter ce champs, ainsi que xml pour stocker ca et le recup !
                        listVol.add(new Vol(DateConvertisseur.dateToStringFormatShow(trajet.getDateDepart()).toString(),
                                DateConvertisseur.dateToStringFormatShow(trajet.getDateArrivee()).toString(),
                                ""+aeroport.getCode(), ""+trajet.getPrix(), ""+trajet.getTrajetId()));

                    }
                }
            }).start();

            listViewVol = (ListView) findViewById(R.id.vol_list);
            listViewVol.setAdapter(new VolListAdapterWithCache(ctx, R.layout.vol_row_item, listVol));

            // Rendre cliquable le tablerow
            TableRow tableRow = (TableRow) findViewById(R.id.one);
            tableRow.setClickable(true);

        } catch (Exception e) {
            Log.w("ERROR",e.toString());
        }
    }

    // Methode se lancant automatiquement en cas de clic sur un tablerow
    public void rowClick(View view) {
        switch(view.getId()) {
            case R.id.one:

                //Recup ID
                TableRow t = (TableRow) view;
                TextView monID = (TextView) t.getChildAt(4);
                TextView idTrajet =  (TextView) findViewById(R.id.txtId);

                // Ouverture de l'activité Détails (avec l'id du trajet)
                Intent detail = new Intent(TrajetsActivity.this, DetailsActivity.class);
                detail.putExtra("idTrajet", monID.getText().toString());
                detail.putExtra("btnReserv", "TRAJETS");
                startActivity(detail);

                // TODO : amélioration future (WIP)
                //Changer la couleur de fond
                //view.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

                //TO DO : retirer la ligne selectionner -> transformer ca en recup id de la ligne selectionné
                // ((LinearLayout)v.getParent()).removeView(v);
                //get the data you need
                //TableRow tablerow = (TableRow)v.getParent();
                //TextView sample = (TextView) tablerow.getChildAt(2);
                //String result=sample.getText().toString();

                break;
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.trajets, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Geoloc_Aeroport Aeroport = new Geoloc_Aeroport("Sélectionnez un aéroport !", 0, 0);
            Intent GMap = new Intent(TrajetsActivity.this, MapsActivity.class);
            startActivity(GMap);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}