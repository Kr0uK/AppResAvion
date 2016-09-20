package cdi.appresavion;


import android.app.Dialog;
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
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableRow;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.AeroportDAO;
import dao.TrajetDAO;
import dbclass.Aeroport;
import dbclass.Trajet;
import shell.Convertissor;
import shell.DateConvertisseur;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
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
                    // Intérrogation de la BDD avec les critères de l'utilisateur
                    // TODO : ID : Peut-on utiliser l'indice du TableRow comme etant l'indice pour lire dans la List listVol et recup ainsi l'id réeel du trajet ?

                    //Recherche des trajets
                    ArrayList trajettArrayList = TrajetDAO.getTrajetWhere(PrefVol.getAeroDepId(),PrefVol.getAeroArrId(), DateConvertisseur.stringToDate(PrefVol.getAeroDateDep()));
                    Iterator<Trajet> trajetIterator = trajettArrayList.iterator();
                    while (trajetIterator.hasNext()) {
                        Trajet trajet = trajetIterator.next();
                        // TODO : J'ai fais tout mon traitement dans une listVol... Forte chance d'incompatibilité, tout a revoir... :/

                        Aeroport aeroport = new Aeroport();
                        aeroport = AeroportDAO.selectionnerAeroport(trajet.getAeroportId());
                        listVol.add(new Vol(DateConvertisseur.dateToStringFormatShow(trajet.getDateDepart()).toString(), DateConvertisseur.dateToStringFormatShow(trajet.getDateArrivee()).toString(), aeroport.getCode(), ""+trajet.getPrix()));
                        //listVol.add(new Vol(trajet.getTrajetId(), trajet.getDateDepart().toString(), trajet.getDateArrivee().toString(), aeroport.getCode(), ""+trajet.getPrix()));
                    }
                }
            }).start();

            // TODO EXAMPLE a delete apres : public Vol(String depart, String arrivee, String code, String prix) {
            //listVol.add(new Vol("09/11/12 21:00:00", "10/11/12 01:00:00", "CDG", "100"));
            //listVol.add(new Vol("10/11/12 22:15:00", "11/11/12 02:00:00", "CDG", "110"));
            //listVol.add(new Vol("11/11/12 23:30:00", "12/11/12 03:00:00", "CDG", "120"));

            listViewVol = (ListView) findViewById(R.id.vol_list);
            // VolListAdapter : Context ctx, int resourceId, List objects
            Log.e("ERROR", "" + R.layout.vol_row_item);
            //listViewVol.setAdapter(new VolListAdapter(ctx, R.layout.vol_row_item, listVol));
            listViewVol.setAdapter(new VolListAdapterWithCache(ctx, R.layout.vol_row_item, listVol));
            /* // ADAPTER SANS CACHE
            VolListAdapter adapter = new VolListAdapter(ctx, R.layout.vol_row_item, listVol);
            adapter.notifyDataSetChanged();
            listViewVol.setAdapter(adapter);
            */
        } catch (Exception e) {
            Log.w("ERROR",e.toString());
        }

        /*
         * Gestion clics sur tablerowr
         */
        try {

            // GESTION ONCLICK SUR UN TABLEROW
            TableRow tableRow = (TableRow) findViewById(R.id.one);
            tableRow.setClickable(true);
            tableRow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent Login = new Intent(TrajetsActivity.this, DetailsActivity.class);
                        startActivity(Login);
                    }
            });
        } catch (Exception e) {
            Log.w("ERROR", ""+e.toString());
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