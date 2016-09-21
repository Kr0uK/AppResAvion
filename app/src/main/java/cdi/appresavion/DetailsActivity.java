package cdi.appresavion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dao.AeroportDAO;
import dao.AvionDAO;
import dao.ReservationDAO;
import dao.TrajetDAO;
import dbclass.Aeroport;
import dbclass.Avion;
import dbclass.Trajet;
import shell.DateConvertisseur;

public class DetailsActivity extends AppCompatActivity {
    TextView tvAeroDepart;
    TextView tvAeroArrivee;
    TextView tvDateDepart;
    TextView tvDateArrivee;
    Button btnReserver;
    int prixTrajet;

    // On va utiliser requeteReservation avec l'id comme argument pour remplir la liste
    Ident_User ident_user = new Ident_User(); // On instancie un Ident_User
    int id = ident_user.getidUser(); // On récupère l'id de l'ident_user

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvAeroDepart = (TextView) findViewById(R.id.detailsAeroDepart);
        tvAeroArrivee = (TextView) findViewById(R.id.detailsAeroArrivee);
        tvDateDepart = (TextView) findViewById(R.id.detailsDateDepart);
        tvDateArrivee = (TextView) findViewById(R.id.detailsDateArrivee);
        btnReserver = (Button) findViewById(R.id.detailsBtnReserver);

        //Recupération de l'idTrajet
        final int idTrajet = Integer.parseInt((String)getIntent().getExtras().get("idTrajet"));

        //Affichage du trajet
        detailTrajet(idTrajet);

        btnReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO nb personne + redirection
                //Param id de l'util, prix du trajet, nombre de personne, id du trajet
                ReservationDAO.ajouterReservationPlace(id, prixTrajet, 1, idTrajet);

                Intent redirection = new Intent(DetailsActivity.this, AccueilActivity.class);
                startActivity(redirection);

                /** A mettre ou on veut appeler les details
                 Intent detail = new Intent(LoginActivity.this, DetailsActivity.class);
                 //Stockage de l'idTrajet
                 detail.putExtra("idTrajet", Integer.toString(6));
                 startActivity(detail);
                */
            }
        });
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

        //Affichage des infos du trajet
        tvAeroDepart.setText(aeroportDepart.getNom());
        tvAeroArrivee.setText(aeroportArrivee.getNom());
        tvDateDepart.setText(DateConvertisseur.dateToStringFormatShow(trajet.getDateDepart()));
        tvDateArrivee.setText(DateConvertisseur.dateToStringFormatShow(trajet.getDateArrivee()));
    }
}
