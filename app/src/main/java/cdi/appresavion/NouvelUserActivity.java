package cdi.appresavion;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class NouvelUserActivity extends AppCompatActivity {

    // Variables
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


    }


}


/**
 * La classe qui va stocker les infos de l'utilisateur
 */
class Nouvel_User {
    private static String nom;

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        Nouvel_User.nom = nom;
    }

    private static String prenom;
    private static String pseudo;
    private static String mdp;
    private static String confmdp;
    private static String mail;
    private static String adresse;
    private static String ville;
    private static String cp;
    private static String tel;

    public static String getMobile() {
        return mobile;
    }

    public static void setMobile(String mobile) {
        Nouvel_User.mobile = mobile;
    }

    private static String mobile;

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
