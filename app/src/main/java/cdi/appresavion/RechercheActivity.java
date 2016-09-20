package cdi.appresavion;
import cdi.appresavion.R;
import dao.TrajetDAO;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import android.app.Activity;
import android.app.DialogFragment;
import android.widget.CompoundButton;

public class RechercheActivity extends AppCompatActivity {

    private static EditText accueilETchoixDepart;
    private static EditText accueilETchoixArrivee;
    private static TextView accueilTVdateDepart;
    private static TextView accueilTVdateArrivee;
    private static EditText accueilETdateDepart;
    private static EditText accueilETdateArrivee;
    private static CheckBox accueilCBallerRetour;
    private static Button accueilBTvalider;
    private static Intent accueil_to_lieu;
    private static Intent accueil_to_resultat;
    private static ArrayAdapter<CharSequence> classeAdapter;
    private static Bundle extras;
    public static String DEPART = "depart";
    public static String ARRIVEE = "arrivee";
    public static String DEPART_OU_ARRIVEE = "depart ou arrivee";
    private static boolean estDepart;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            // TODO : Connection à la BDD pour effectuer les recherches
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {

                ListeTablesBDD osef = new ListeTablesBDD(getApplicationContext());
                osef.open(getApplicationContext());
                InsertionDonnees.insertionDonnees(getApplicationContext());
                osef.close();

            }
        }).start();
        */

            // STRINGS
            accueilETchoixDepart = (EditText) findViewById(R.id.accueilETChoixDepart);
            accueilETchoixArrivee = (EditText) findViewById(R.id.accueilETChoixArrivee);
            accueilETdateDepart = (EditText) findViewById(R.id.accueilETdateDepart);
            accueilETdateArrivee = (EditText) findViewById(R.id.accueilETdateArrivee);
            accueilTVdateDepart = (TextView) findViewById(R.id.accueilTVdateDepart);
            accueilTVdateArrivee = (TextView) findViewById(R.id.accueilTVdateArrivee);
            accueilCBallerRetour = (CheckBox) findViewById(R.id.accueilCBallerRetour);
            accueilBTvalider = (Button) findViewById(R.id.accueilBTvalider);
            accueilTVdateArrivee.setVisibility(View.GONE);
            accueilETdateArrivee.setVisibility(View.GONE);


            // REDIRECTIONS
            accueil_to_lieu = new Intent(RechercheActivity.this, recherche.ChoixLieu.class);
            accueil_to_resultat = new Intent(RechercheActivity.this, TrajetsActivity.class);


            // EVENTS
            accueilETchoixDepart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    estDepart = true;
                    accueil_to_lieu.putExtra(RechercheActivity.DEPART_OU_ARRIVEE, estDepart);
                    startActivityForResult(accueil_to_lieu, REQUEST_CODE);
                }
            });
            accueilETchoixArrivee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    estDepart = false;
                    accueil_to_lieu.putExtra(RechercheActivity.DEPART_OU_ARRIVEE, estDepart);
                    startActivityForResult(accueil_to_lieu, REQUEST_CODE);
                }
            });
            accueilCBallerRetour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (accueilCBallerRetour.isChecked()) {
                        accueilTVdateArrivee.setVisibility(View.VISIBLE);
                        accueilETdateArrivee.setVisibility(View.VISIBLE);
                    } else {
                        accueilTVdateArrivee.setVisibility(View.GONE);
                        accueilETdateArrivee.setVisibility(View.GONE);
                    }
                }
            });
            accueilETdateDepart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    estDepart = true;
                    ouvrirCalendrier();
                }
            });
            accueilETdateArrivee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    estDepart = false;
                    if (accueilCBallerRetour.isChecked()) {
                        ouvrirCalendrier();
                    }

                }
            });
            accueilBTvalider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TrajetDAO.getTrajetWhere(accueilETchoixDepart.getText(), accueilETchoixArrivee.getText(),accueilETdateDepart.getText());
                    Choix_Avion stockage = new Choix_Avion(accueilETdateDepart.getText().toString());
                    startActivity(accueil_to_resultat);
                }
            });

        } catch (Exception e) {
            Log.e("ERROR",e.toString());
        }
    }

    /*
     * Page acceuil, click sur textbox depart/arrivée
     * -> page choixlieu : on choisi l'aeroport souhaité
     * Page accueil receptionne le resultat
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (estDepart) {
                    accueilETchoixDepart.setText(data.getStringExtra(RechercheActivity.DEPART));
                } else {
                    accueilETchoixArrivee.setText(data.getStringExtra(RechercheActivity.ARRIVEE));
                }
            }
        }
    }

    private void ouvrirCalendrier(){
        DialogFragment dialogFragment = recherche.Calendrier.newInstance(1);
        dialogFragment.show(getFragmentManager(), "dialog");
    }

    public static void setAccueilETdateDepart(String date){
        accueilETdateDepart.setText(date);
    }

    public static void setAccueilETdateArrivee(String date){
        accueilETdateArrivee.setText(date);
    }

    public static String getAccueilETdateDepart(){
        return accueilETdateDepart.getText().toString();
    }

    public static String getAccueilETdateArrivee(){
        return accueilETdateArrivee.getText().toString();
    }

    public static boolean getEstDepart(){
        return estDepart;
    }

}


