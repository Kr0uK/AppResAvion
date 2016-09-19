package cdi.appresavion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Iterator;

import dao.AeroportDAO;
import dao.AvionDAO;
import dao.PlaceDAO;
import dao.ReservationDAO;
import dao.TrajetDAO;
import dbclass.Aeroport;
import dbclass.Avion;
import dbclass.Place;
import dbclass.Reservation;
import dbclass.Trajet;
import shell.DateConvertisseur;

public class AccueilActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // On va utiliser requeteReservation avec l'id comme argument pour remplir la liste
    Ident_User ident_user = new Ident_User(); // On instancie un Ident_User
    int id = ident_user.getidUser(); // On récupère l'id de l'ident_user
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.w("TAG", "" + id); //Vérification
        requeteReservation(id);
        /*
         TODO mettre en place le thread

        new Thread(new Runnable() {
            @Override
            public void run() {
                requeteReservation(id);

                });
            }

        }).start();
*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Informations légales", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Boutton permettant de selectionner un vol pour reserver un ticket
        Button btsearch = (Button) findViewById(R.id.search);
        btsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent trajets = new Intent(AccueilActivity.this, TrajetsActivity.class);
                startActivity(trajets);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        getMenuInflater().inflate(R.menu.accueil, menu);
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
            Intent GMap = new Intent(AccueilActivity.this, MapsActivity.class);
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

    /**
     * Récupére les réservations d'un utilisateur a partir de son id.
     * TODO nom a définir
     *
     * @param id de l'utilisateur
     */
    private void requeteReservation(int id) {
        //Création de l'ArrayList qui contient les reservations qui ne sont pas encore passé
        ArrayList reservationArrayList = ReservationDAO.getReservationWhere(id);
        //Iterator qui va permetre de parcourir l'ArrayList de reservation
        Iterator<Reservation> reservationIterator = reservationArrayList.iterator();

        //Parcours les reservations qui ne sont pas encore passé
        while (reservationIterator.hasNext()) {
            //Objet contenant les reservations, s'incrémente a chaque next
            Reservation reservation = reservationIterator.next();

            //Création de l'objet qui contient la place
            Place place = PlaceDAO.selectionnerPlace(reservation.getReservationId());

            //Création de l'objet qui contient le trajet
            Trajet trajet = TrajetDAO.selectionnerTrajet(place.getTrajetId());

            //Création de l'objet qui contient l'aeroport de depart
            Aeroport aeroportDepart = AeroportDAO.selectionnerAeroport(trajet.getAeroportId());

            //Création de l'objet qui contient l'aeroport d'arrivee
            Aeroport aeroportArrivee = AeroportDAO.selectionnerAeroport(trajet.getAerAeroportId());

            //Création de l'objet qui contient l'avion
            Avion avion = AvionDAO.selectionnerAvion(trajet.getAvionId());

            //String de test avec quelque infos des reservations
            String res = "Réponse = Id Util "
                    + reservation.getUtilisateurId() + " | Aeroport de depart "
                    + aeroportDepart.getNom() + " | d'arrivée "
                    + aeroportArrivee.getNom() + " | la date ou on part "
                    + DateConvertisseur.dateToStringFormat(trajet.getDateDepart()) + " on part avec un "
                    + avion.getModele();

            //Resultat en Log pour les tests
            Log.w("TEST", res);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Accueil Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://cdi.appresavion/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Accueil Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://cdi.appresavion/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
