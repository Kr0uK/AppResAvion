package recherche;
import cdi.appresavion.R;
import cdi.appresavion.RechercheActivity;
import dao.AeroportDAO;
import dao.DAOBase;
import dbclass.Aeroport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class ChoixLieu extends Activity implements TextWatcher {

    // VARIABLES D'ENVIRONNEMENT
    private static TextView lieuTVnbResultats;
    private static EditText lieuETchoixAeroport;
    private static ListView lieuLVlisteAeroports;
    private static ListAdapter simpleadapter;
    private static ArrayList<HashMap<String,String>> listeMaj;

    //TODO
    // private static ListeTablesBDD listeTablesBDD;

    private static String resultat;
    private static String aitaResultat;
    private static String nomResultat;
    private static String nbReponses;
    private Intent retourAccueil;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choixlieu_main);

        //STRINGS
        lieuTVnbResultats = (TextView) findViewById(R.id.lieuTVnbResultats);
        lieuETchoixAeroport  = (EditText) findViewById(R.id.lieuETchoixAeroport);
        lieuLVlisteAeroports = (ListView) findViewById(R.id.list);

        // Intent vide (instancié en ligne 76/78
        retourAccueil = new Intent();
        // Bundle permettant d'eviter de retaper le contenu de extra
        extras = getIntent().getExtras();
        afficherClavier();

        lieuETchoixAeroport.addTextChangedListener(this);

        lieuETchoixAeroport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherClavier();
            }
        });

        lieuLVlisteAeroports.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /*
             * AJOUT DE L'AEROPORT CLIQUÉ
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                aitaResultat = listeMaj.get(position).get("Nom").
                        substring(listeMaj.get(position).get("Nom").lastIndexOf("("),listeMaj.get(position).get("Nom").lastIndexOf(")") + 1);
                nomResultat = listeMaj.get(position).get("Ville").
                        substring(0,listeMaj.get(position).get("Ville").indexOf("("));
                resultat = nomResultat + aitaResultat;
                if (extras.getBoolean(RechercheActivity.DEPART_OU_ARRIVEE)){
                    retourAccueil.putExtra(RechercheActivity.DEPART,resultat);
                } else {
                    retourAccueil.putExtra(RechercheActivity.ARRIVEE,resultat);
                }
                // startActivityForResult
                setResult(Activity.RESULT_OK,retourAccueil);
                finish();
            }
        });

    }

    private void afficherClavier() {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
        );
    }
    private void masquerClavier() {
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
        );

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /*
     * SAISIE AUTOMATIQUE (recherche pendant que l'on ecrit)
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TODO a modif
                String choix = lieuETchoixAeroport.getText().toString();
                listeMaj = AeroportDAO.RechercheAeroport(choix);
                Log.w("TAG", "" + listeMaj.get(0).toString());
                simpleadapter = new SimpleAdapter(getApplicationContext(),listeMaj, R.layout.choixlieu_listview, new String[]{"Nom","Ville"}, new int[] {R.id.list_content, R.id.list_content2});
                lieuLVlisteAeroports.setAdapter(simpleadapter);
                if (listeMaj.size() > 1) {
                    nbReponses = "Il y a " + listeMaj.size() + " résultats";
                } else if (listeMaj.size() == 1 &&
                        (listeMaj.get(0).get("Nom").equals(AeroportDAO.REPONSE_VIDE) || listeMaj.get(0).get("Nom").equals(AeroportDAO.AUCUNE_REPONSE))
                        ) {
                    nbReponses = null;
                } else if (listeMaj.size() == 1) {
                    nbReponses = "Il y a 1 résultat";
                }
                lieuTVnbResultats.setText(nbReponses);
                DAOBase.close();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
