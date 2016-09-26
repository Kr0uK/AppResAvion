package cdi.appresavion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import dao.AeroportDAO;
import dao.AvionDAO;
import dao.ReservationDAO;
import dao.TrajetDAO;
import dbclass.Aeroport;
import dbclass.Avion;
import dbclass.Reservation;
import dbclass.Trajet;
import shell.DateConvertisseur;

public class DetailsActivity extends AppCompatActivity {
    TextView tvAeroDepart;
    TextView tvAeroArrivee;
    TextView tvDateDepart;
    TextView tvAutres;
    TextView tvDateArrivee;
    TextView tvNumVol;
    Button btnReserver;

    // Pour le boutton GMap
    Double Longitude;
    Double Latitude;
    Button btnGmap;

    //prix du trajet
    int prixTrajet;
    //nombre de place dans l'avion
    int nbPlacesAvion;
    //nombre de place reservée
    int nbPlacesReservee = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadDetailsActivity();

    }

    private void loadDetailsActivity() {
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvAeroDepart = (TextView) findViewById(R.id.detailsAeroDepart);
        tvAeroArrivee = (TextView) findViewById(R.id.detailsAeroArrivee);
        tvDateDepart = (TextView) findViewById(R.id.detailsDateDepart);
        tvDateArrivee = (TextView) findViewById(R.id.detailsDateArrivee);
        tvAutres = (TextView) findViewById(R.id.txtAutre);
        tvNumVol = (TextView) findViewById(R.id.txtVol);
        btnReserver = (Button) findViewById(R.id.detailsBtnReserver);
        btnGmap = (Button) findViewById(R.id.btnGmap);

        // On instancie un Ident_User
        Ident_User ident_user = new Ident_User();
        // On récupère l'id de l'ident_user
        final int id = ident_user.getidUser();

        //Recupération de l'idTrajet
        final int idTrajet = Integer.parseInt((String)getIntent().getExtras().get("idTrajet"));
        //Infos de la page qu'on vient
        final String vientDe = (String) getIntent().getExtras().get("btnReserv");

        if (vientDe.equals("ACCUEIL")){
            btnReserver.setText("Annuler");
        }

        //Affichage du trajet
        detailTrajet(idTrajet);

        btnReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO nbPlacesReservee = Integer.parse(?.getText().toString());

                if (vientDe.equals("ACCUEIL")) {
                    ReservationDAO.supprimerReservation(Integer.parseInt((String)getIntent().getExtras().get("idReserv")));
                    Toast.makeText(DetailsActivity.this, "Votre réservation a bien était annulé !", Toast.LENGTH_LONG).show();
                } else {
                    //Verifie si il reste des places pour ce vol
                    if (nbPlacesAvion > ReservationDAO.sumPlace(idTrajet) + nbPlacesReservee) {
                        //Param id de l'util, prix du trajet, nombre de personne, id du trajet
                        ReservationDAO.ajouterReservationPlace(id, prixTrajet, nbPlacesReservee, idTrajet);
                        Toast.makeText(DetailsActivity.this, "Reservation enregistrée !", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(DetailsActivity.this, "Il ne reste plus de place pour ce vol", Toast.LENGTH_SHORT).show();
                    }
                }
                //Redirection de l'utilisateur
                Intent redirection = new Intent(DetailsActivity.this, AccueilActivity.class);
                startActivity(redirection);
            }
        });

        btnGmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // FRED : Redirection vers l'activité GMap avec marquage de l'aeroport
                    Geoloc_Aeroport Aeroport = new Geoloc_Aeroport(tvAeroDepart.getText().toString(), Latitude, Longitude);
                    Intent GMap = new Intent(DetailsActivity.this, MapsActivity.class);
                    startActivity(GMap);
                } catch (Exception e) {
                    Log.w("ERROR",e.toString());
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loadDetailsActivity();
    }

    /**
     * Recupere les details d'un trajet.
     * change les textview pour afficher les infos souhaitées
     * @param idTrajet id du trajet
     */
    private void detailTrajet(int idTrajet) {
        //Objet avec les infos du trajet cliquer
        Trajet trajet = TrajetDAO.selectionnerTrajet(idTrajet);

        //Objet avec les infos de l'aeroport de depart
        Aeroport aeroportDepart = AeroportDAO.selectionnerAeroport(trajet.getAeroportId());
        //Objet avec les infos de l'aeroport d'arrivee
        Aeroport aeroportArrivee = AeroportDAO.selectionnerAeroport(trajet.getAerAeroportId());

        //Objet avec les infos de l'avion
        Avion avion = AvionDAO.selectionnerAvion(trajet.getAvionId());

        //Stockage du prix du trajet
        prixTrajet = trajet.getPrix();
        //Stockage du nombre de places
        nbPlacesAvion = avion.getNbPlaces();
        //Place restantes
        int nbPlacesRestantes = nbPlacesAvion - ReservationDAO.sumPlace(idTrajet);

        //Affichage des infos du trajet
        tvAeroDepart.setText(aeroportDepart.getNom());
        tvAeroArrivee.setText(aeroportArrivee.getNom());
        tvDateDepart.setText(DateConvertisseur.dateToStringFormatShow(trajet.getDateDepart()));
        tvDateArrivee.setText(DateConvertisseur.dateToStringFormatShow(trajet.getDateArrivee()));
        tvNumVol.setText(Integer.toString(idTrajet));
        tvAutres.setText("Modèle de l'avion " + avion.getModele() + ", nombres de places restantes : " + nbPlacesRestantes);
        // FRED : recup d'infos pour localisation de l'aeroport
        Latitude = aeroportDepart.getLatitude();
        Longitude = aeroportDepart.getLongitude();
    }
}
