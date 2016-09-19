package cdi.appresavion.recherche;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import cdi.appresavion.R;
/*

public class Accueil extends AppCompatActivity {

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
    private static Spinner accueilSPclasse;
    private static ArrayAdapter<CharSequence> classeAdapter;
    private static Bundle extras;
    final static String DEPART = "depart";
    final static String ARRIVEE = "arrivee";
    final static String DEPART_OU_ARRIVEE = "depart ou arrivee";
    private static boolean estDepart;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil_main);






        // STRINGS
        accueilETchoixDepart = (EditText)findViewById(R.id.accueilETChoixDepart);
        accueilETchoixArrivee = (EditText)findViewById(R.id.accueilETChoixArrivee);
        accueilETdateDepart = (EditText)findViewById(R.id.accueilETdateDepart);
        accueilETdateArrivee = (EditText)findViewById(R.id.accueilETdateArrivee);
        accueilTVdateDepart = (TextView)findViewById(R.id.accueilTVdateDepart);
        accueilTVdateArrivee = (TextView)findViewById(R.id.accueilTVdateArrivee);
        accueilCBallerRetour = (CheckBox)findViewById(R.id.accueilCBallerRetour);
        accueilBTvalider = (Button)findViewById(R.id.accueilBTvalider);
        accueilSPclasse = (Spinner)findViewById(R.id.accueilSPclasse);
        classeAdapter = ArrayAdapter.createFromResource(this,R.array.accueilSPclasse,R.layout.accueil_spinner);
        accueilSPclasse.setAdapter(classeAdapter);
        accueilTVdateArrivee.setVisibility(View.GONE);
        accueilETdateArrivee.setVisibility(View.GONE);


        // REDIRECTUIONS
        accueil_to_lieu = new Intent(Accueil.this,ChoixLieu.class);
        accueil_to_resultat = new Intent(Accueil.this,Recherche.class);


        // EVENTS
        accueilETchoixDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estDepart = true;
                accueil_to_lieu.putExtra(Accueil.DEPART_OU_ARRIVEE, estDepart);
                startActivityForResult(accueil_to_lieu, REQUEST_CODE);
            }
        });
        accueilETchoixArrivee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estDepart = false;
                accueil_to_lieu.putExtra(Accueil.DEPART_OU_ARRIVEE, estDepart);
                startActivityForResult(accueil_to_lieu, REQUEST_CODE);
            }
        });
        accueilCBallerRetour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (accueilCBallerRetour.isChecked()){
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
                if (accueilCBallerRetour.isChecked()){
                    ouvrirCalendrier();
                }

            }
        });
        accueilBTvalider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(accueil_to_resultat);
            }
        });



    }

    /*
     * Page acceuil, click sur textbox depart/arrivée
     * -> page choixlieu : on choisi l'aeroport souhaité
     * Page accueil receptionne le resultat

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == 1) {
            if (resultCode == Activity.RESULT_OK) {
                if (estDepart) {
                    accueilETchoixDepart.setText(data.getStringExtra(Accueil.DEPART));
                } else {
                    accueilETchoixArrivee.setText(data.getStringExtra(Accueil.ARRIVEE));
                }
            }
        }
    }

    private void ouvrirCalendrier(){
        DialogFragment dialogFragment = Calendrier.newInstance(1);
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
*/