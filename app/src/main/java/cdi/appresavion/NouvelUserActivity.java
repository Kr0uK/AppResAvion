package cdi.appresavion;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NouvelUserActivity extends AppCompatActivity {

    // Variables

    public static final String TAG = "DEBUG";

    // Patterns pour le Regex
    Pattern regex_alpha = Pattern.compile("^[^0-9]+");
    Pattern regex_alphanum = Pattern.compile("^[a-zA-Z_0-9]+");
    Pattern regex_num = Pattern.compile("^[0-9]+");
    Pattern regex_tel = Pattern.compile("/^(0)[1-5|9](\\d{2}){4}$/");
    Pattern regex_mobile = Pattern.compile("/^(0)[67](\\d{2}){4}$/");
    Pattern regex_email = Pattern.compile("^[A-Za-z0-9](([_\\.\\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\\.\\-]?[a-zA-Z0-9]+)*)\\.([A-Za-z]{2,})$");


    // Matcher
    Matcher m;

    // EditTexts
    private EditText nom_user;
    private EditText prenom_user;
    private EditText pseudo_user;
    private EditText mdp_user;
    private EditText confmdp_user;
    private EditText mail_user;
    private EditText adresse_user;
    private EditText ville_user;
    private EditText cp_user;
    private EditText tel_user;
    private EditText mobile_user;
    private Button btn_valider;

    // Le texte qui va en être récupéré
    private String nom;
    private String prenom;
    private String pseudo;
    private String mdp;
    private String confmdp;
    private String mail;
    private String adresse;
    private String ville;
    private String cp;
    private String tel;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nouvel_user);

        // TODO inscription nouvel user

        // On instancie la classe qui va stocker toutes les infos
        Nouvel_User nv_user = new Nouvel_User();

        // On lie les EditText entre XML et Java
        nom_user = (EditText) findViewById(R.id.input_nom);
        prenom_user = (EditText) findViewById(R.id.input_prenom);
        pseudo_user = (EditText) findViewById(R.id.input_pseudo);
        mdp_user = (EditText) findViewById(R.id.input_mdp);
        confmdp_user = (EditText) findViewById(R.id.input_mdpconf);
        mail_user = (EditText) findViewById(R.id.input_mail);
        adresse_user = (EditText) findViewById(R.id.input_adresse);
        ville_user = (EditText) findViewById(R.id.input_ville);
        cp_user = (EditText) findViewById(R.id.input_cp);
        tel_user = (EditText) findViewById(R.id.input_tel);
        mobile_user = (EditText) findViewById(R.id.input_mobile);
        btn_valider = (Button) findViewById(R.id.btn_valider_nvuser);


        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // On récupère l'input

                String nom = nom_user.getText().toString();
                String prenom = prenom_user.getText().toString();
                String pseudo = pseudo_user.getText().toString();
                String mdp = mdp_user.getText().toString();
                String confmdp = confmdp_user.getText().toString();
                String mail = mail_user.getText().toString();
                String adresse = adresse_user.getText().toString();
                String ville = ville_user.getText().toString();
                String cp = cp_user.getText().toString();
                String tel = tel_user.getText().toString();
                String mobile = mobile_user.getText().toString();

                /**
                 * Vérifications des champs via Regex
                 * On lie un Pattern à un Matcher sur un String
                 * ça renvoie un booléen si ca correspond
                 * find() plutôt que matches() comme méthode
                 *
                 */
                // Nom
                m = regex_alpha.matcher(nom);
                if (m.find()) {
                    Log.d(TAG, "Nom OK"); // TODO toast
                } else {
                    Log.d(TAG, "Nom pas OK"); // TODO toast
                }
                // Prénom
                m = regex_alpha.matcher(prenom);
                if (m.find()) {
                    Log.d(TAG, "Prénom OK"); // TODO toast
                } else {
                    Log.d(TAG, "Prénom pas OK"); // TODO toast
                }
                // Pseudo
                m = regex_alphanum.matcher(pseudo);
                if (m.find()) {
                    Log.d(TAG, "Pseudo OK"); // TODO toast
                } else {
                    Log.d(TAG, "Pseudo pas OK"); // TODO toast
                }
                // Mot de passe



            }
        });
    }


}


/**
 * La classe qui va stocker les infos de l'utilisateur
 */

class Nouvel_User {


    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        Nouvel_User.nom = nom;
    }

    private static String nom;
    private static String prenom;
    private static String pseudo;
    private static String mdp;
    private static String confmdp;
    private static String mail;
    private static String adresse;
    private static String ville;
    private static String cp;
    private static String tel;
    private static String mobile;


    public static String getMobile() {
        return mobile;
    }

    public static void setMobile(String mobile) {
        Nouvel_User.mobile = mobile;
    }


    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        Nouvel_User.prenom = prenom;
    }

    public static String getPseudo() {
        return pseudo;
    }

    public static void setPseudo(String pseudo) {
        Nouvel_User.pseudo = pseudo;
    }

    public static String getMdp() {
        return mdp;
    }

    public static void setMdp(String mdp) {
        Nouvel_User.mdp = mdp;
    }

    public static String getConfmdp() {
        return confmdp;
    }

    public static void setConfmdp(String confmdp) {
        Nouvel_User.confmdp = confmdp;
    }

    public static String getMail() {
        return mail;
    }

    public static void setMail(String mail) {
        Nouvel_User.mail = mail;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        Nouvel_User.adresse = adresse;
    }

    public static String getVille() {
        return ville;
    }

    public static void setVille(String ville) {
        Nouvel_User.ville = ville;
    }

    public static String getCp() {
        return cp;
    }

    public static void setCp(String cp) {
        Nouvel_User.cp = cp;
    }

    public static String getTel() {
        return tel;
    }

    public static void setTel(String tel) {
        Nouvel_User.tel = tel;
    }

    public Nouvel_User() {
    }

    // On instancie le constructeur qui va récupérer ce qui va dans la base
    public Nouvel_User(String nom, String prenom, String pseudo, String mdp, String mail, String adresse, String ville, String cp, String tel, String mobile) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.mail = mail;
        this.adresse = adresse;
        this.ville = ville;
        this.cp = cp;
        this.tel = tel;
        this.mobile = mobile;


    }


}
