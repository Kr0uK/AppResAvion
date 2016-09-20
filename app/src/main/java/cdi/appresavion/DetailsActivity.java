package cdi.appresavion;

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
                //TODO continuer l'ajout d'une reservation
                Log.w("TAG", "coucou");
                ReservationDAO.ajouterReservationPlace(1, prixTrajet, 1, idTrajet);
            }
        });
    }



    private void detailTrajet(int idTrajet) {
        Trajet trajet = TrajetDAO.selectionnerTrajet(idTrajet);

        Aeroport aeroportDepart = AeroportDAO.selectionnerAeroport(trajet.getAeroportId());
        Aeroport aeroportArrivee = AeroportDAO.selectionnerAeroport(trajet.getAerAeroportId());

        Avion avion = AvionDAO.selectionnerAvion(trajet.getAvionId());

        prixTrajet = trajet.getPrix();

        //Affichage
        tvAeroDepart.setText(aeroportDepart.getNom());
        tvAeroArrivee.setText(aeroportArrivee.getNom());
        tvDateDepart.setText(DateConvertisseur.dateToStringFormatShow(trajet.getDateDepart()));
        tvDateArrivee.setText(DateConvertisseur.dateToStringFormatShow(trajet.getDateArrivee()));
    }
}
