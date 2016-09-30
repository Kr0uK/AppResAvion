package cdi.appresavion;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
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
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.AeroportDAO;
import dao.AvionDAO;
import dao.DAOBase;
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

    static TextView intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (id == 0){
            id = Integer.parseInt((String)getIntent().getExtras().get("idUser"));
        }

        intro = (TextView) findViewById(R.id.intro);

        viewUserReserv(id);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Les Ents-AI", Snackbar.LENGTH_LONG).setAction("Action", null).show();
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
                Intent search = new Intent(AccueilActivity.this, RechercheActivity.class);
                startActivity(search);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    private static Trajet trajet;
    private ListView listViewRes;
    private Context ctx;

    // FRED : Methode permettant la récupération des données de reservation, de l'affichage et de rendre possible une vue en details
    private void viewUserReserv(int id) {
        try {
            ctx = this;
            List listReserv = new ArrayList();

            // Interrogation BDD
            requeteReservation(id, listReserv, getApplicationContext());

            // Affichage des données
            listViewRes = (ListView) findViewById(R.id.reserv_list);
            listViewRes.setAdapter(new ResListAdapterWithCache(ctx, R.layout.reserv_row_item, listReserv));

            // Rendre cliquable le tablerow
            TableRow tableRow = (TableRow) findViewById(R.id.ticket);
            tableRow.setClickable(true);

        } catch (Exception e) {
            Log.w("ERROR",e.toString());
        }
    }

    // FRED : Methode se lancant automatiquement en cas de clic sur un tablerow (redirection page Details)
    public void rowClick(View view) {
        switch(view.getId()) {
            case R.id.ticket:
                //Recup ID
                TableRow t = (TableRow) view;
                TextView monID = (TextView) t.getChildAt(3);
                TextView tonID = (TextView) t.getChildAt(4);

                // Ouverture de l'activité Détails (avec l'id du trajet)
                Intent detail = new Intent(AccueilActivity.this, DetailsActivity.class);
                detail.putExtra("idTrajet", monID.getText().toString());
                detail.putExtra("idReserv", tonID.getText().toString());
                detail.putExtra("btnReserv", "ACCUEIL");
                
                startActivity(detail);
                break;
        }
    }

    // RENAUD (edit FRED) : Methode permettant de recupérer les reservations de l'utilisateur depuis BDD
    public static void requeteReservation(int id, List listReserv, Context context) {
        try {
            DAOBase daoBase = new DAOBase(context);
            daoBase.getWDb();

            //Création de l'ArrayList qui contient les reservations qui ne sont pas encore passé
            ArrayList reservationArrayList = ReservationDAO.getReservationWhere(id);
            //Iterator qui va permetre de parcourir l'ArrayList de reservation
            Iterator<Reservation> reservationIterator = reservationArrayList.iterator();

            // Alarme pour service
            AlarmManager alarms = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, ServiceNotif.class);
            PendingIntent operation = PendingIntent.getService(context, 0, intent, 0);
            long alarmeService = 0;
            boolean recup = false;
            long alerteTemps = 18000000;

            //Parcours les reservations qui ne sont pas encore passé
            while (reservationIterator.hasNext()) {
                //Objet contenant les reservations, s'incrémente a chaque next
                Reservation reservation = reservationIterator.next();

                //Création de l'objet qui contient la place
                Place place = PlaceDAO.selectionnerPlace(reservation.getReservationId());

                //Création de l'objet qui contient le trajet
                trajet = TrajetDAO.selectionnerTrajet(place.getTrajetId());

                //Création de l'objet qui contient l'aeroport de depart
                Aeroport aeroportDepart = AeroportDAO.selectionnerAeroport(trajet.getAeroportId());

                //Création de l'objet qui contient l'aeroport d'arrivee
                Aeroport aeroportArrivee = AeroportDAO.selectionnerAeroport(trajet.getAerAeroportId());

                //Création de l'objet qui contient l'avion
                Avion avion = AvionDAO.selectionnerAvion(trajet.getAvionId());

                //Recuperation du vol le plus tot
                if (!recup){
                    alarmeService = trajet.getDateDepart().getTime() - alerteTemps;
                    TrajetNotif trajetNotif = new TrajetNotif(alarmeService, aeroportDepart.getNom(), aeroportArrivee.getNom());
                    recup = true;
                }

                // Stockage pour affichage ulterieur des données
                listReserv.add(new Reserv(
                        DateConvertisseur.dateToStringFormatShow(trajet.getDateDepart()).toString(),
                        ""+aeroportDepart.getNom(), ""+aeroportArrivee.getNom(), ""+trajet.getTrajetId(), ""+reservation.getReservationId()));
            }
            // Modif du Textview selon le nombre de billets d'avion réservés :
            if (reservationArrayList.size() >= 2) {
                intro.setText("Vous avez "+reservationArrayList.size()+" billets réservés !");
                //Création du service
                alarms.setExact(AlarmManager.RTC_WAKEUP, alarmeService, operation);
            } else if (reservationArrayList.size() == 1) {
                intro.setText("Vous avez un billet d'avion réservé pour un vol à venir... !");
                //Création du service
                alarms.setExact(AlarmManager.RTC_WAKEUP, alarmeService, operation);
            } else {
                intro.setText("Vous n'avez aucun billet d'avion réservé pour un vol à venir...");
            }

        } catch(Exception e) {
            Log.w("ERROR", e.toString());
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
