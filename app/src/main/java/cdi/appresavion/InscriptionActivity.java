package cdi.appresavion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.UtilisateurDAO;
import dbclass.Utilisateur;

public class InscriptionActivity extends AppCompatActivity {

    // Variables

    public static final String TAG = "DEBUG";

    // Patterns pour le Regex
    Pattern regex_alpha = Pattern.compile("^[^0-9]+");
    Pattern regex_alphanum = Pattern.compile("^[a-zA-Z_0-9]+");
    Pattern regex_num = Pattern.compile("^[0-9]{5}$");
    Pattern regex_tel = Pattern.compile("^(0)[1-59](\\s?\\d{2}){4}$");
    Pattern regex_mobile = Pattern.compile("^(0)[67](\\s?\\d{2}){4}$+");
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
    private String input_nom = "";
    private String input_prenom = "";
    private String input_pseudo = "";
    private String input_mdp = "";
    private String input_confmdp = "";
    private String input_mail = "";
    private String input_adresse = "";
    private String input_ville = "";
    private String input_cp = "";
    private String input_tel = "";
    private String input_mobile = "";
    View focusView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        // TODO inscription nouvel user


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


        // On réinitialise les erreurs
        nom_user.setError(null);
        prenom_user.setError(null);
        pseudo_user.setError(null);
        mdp_user.setError(null);
        confmdp_user.setError(null);
        mail_user.setError(null);
        adresse_user.setError(null);
        ville_user.setError(null);
        cp_user.setError(null);
        tel_user.setError(null);
        mobile_user.setError(null);


        // On instancie l'objet utilisateur
        final Utilisateur nv_user = new Utilisateur();


        btn_valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // On instancie la classe qui va stocker toutes les infos
                // On part du principe que c'est forcément juste quand on vza cliquer (les vérifs sont sur le onTextChanged)

                Log.d(TAG, "" + nv_user.getNom() + " " + nv_user.getPrenom() + " " + nv_user.getUsername() + " " + nv_user.getMdp() + " " + nv_user.getMail() + " " + nv_user.getAdresse() + " " + nv_user.getVille() + " " + nv_user.getCp() + " " + nv_user.getTelephone() + " " + nv_user.getMobile());

                UtilisateurDAO.ajouterUtilisateur(nv_user);

                Intent Login = new Intent(InscriptionActivity.this, LoginActivity.class);

                startActivity(Login);

            }
        });


        nom_user.addTextChangedListener(new

                                                TextWatcher() {
                                                    @Override
                                                    public void afterTextChanged(Editable s) {


                                                    }

                                                    @Override
                                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                                                    }

                                                    @Override
                                                    public void onTextChanged(CharSequence s, int start, int before, int count) {


                                                            input_nom = nom_user.getText().toString();
                                                            m = regex_alpha.matcher(input_nom);
                                                            if (m.find()) {
                                                                nv_user.setNom(input_nom);
                                                            } else {
                                                                nom_user.setError(getString(R.string.error_nom_user));
                                                                focusView = nom_user;
                                                            }
                                                            m.reset();
                                                        }

                                                }

        );
        prenom_user.addTextChangedListener(new

                                                   TextWatcher() {
                                                       @Override
                                                       public void afterTextChanged(Editable s) {


                                                       }

                                                       @Override
                                                       public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                                                       }

                                                       @Override
                                                       public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                           input_prenom = prenom_user.getText().toString();
                                                           m = regex_alpha.matcher(input_prenom);
                                                           if (m.find()) {
                                                               nv_user.setPrenom(input_prenom);
                                                           } else {
                                                               prenom_user.setError(getString(R.string.error_prenom_user));
                                                               focusView = prenom_user;
                                                           }
                                                           m.reset();

                                                       }
                                                   }

        );
        pseudo_user.addTextChangedListener(new

                                                   TextWatcher() {
                                                       @Override
                                                       public void afterTextChanged(Editable s) {


                                                       }

                                                       @Override
                                                       public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                                                       }

                                                       @Override
                                                       public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                           input_pseudo = pseudo_user.getText().toString();
                                                           m = regex_alphanum.matcher(input_pseudo);
                                                           if (m.find()) {
                                                               nv_user.setUsername(input_pseudo);
                                                           } else {
                                                               pseudo_user.setError(getString(R.string.error_pseudo_user));
                                                               focusView = pseudo_user;
                                                           }
                                                           m.reset();
                                                       }
                                                   }

        );
        mdp_user.addTextChangedListener(new

                                                TextWatcher() {
                                                    @Override
                                                    public void afterTextChanged(Editable s) {


                                                    }

                                                    @Override
                                                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                                                    }

                                                    @Override
                                                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                        input_mdp = mdp_user.getText().toString();
                                                        if (input_mdp.length() < 4) {
                                                            mdp_user.setError(getString(R.string.error_mdp_user));
                                                            focusView = mdp_user;
                                                        }
                                                        m.reset();
                                                    }

                                                }

        );
        confmdp_user.addTextChangedListener(new

                                                    TextWatcher() {
                                                        @Override
                                                        public void afterTextChanged(Editable s) {


                                                        }

                                                        @Override
                                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                                                        }

                                                        @Override
                                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                                            input_mdp = mdp_user.getText().toString();
                                                            input_confmdp = confmdp_user.getText().toString();
                                                            if (input_confmdp.equals(input_mdp)) {
                                                                nv_user.setMdp(input_mdp);
                                                            } else {
                                                                confmdp_user.setError(getString(R.string.error_confmdp_user));
                                                                focusView = confmdp_user;
                                                            }
                                                        }
                                                    });


        mail_user.addTextChangedListener(new

                                                 TextWatcher() {
                                                     @Override
                                                     public void afterTextChanged
                                                             (Editable s) {


                                                     }

                                                     @Override
                                                     public void beforeTextChanged
                                                             (CharSequence s, int start,
                                                              int count, int after) {


                                                     }

                                                     @Override
                                                     public void onTextChanged
                                                             (CharSequence s, int start,
                                                              int before, int count) {
                                                         input_mail = mail_user.getText().toString();
                                                         m = regex_email.matcher(input_mail);
                                                         if (m.find()) {
                                                             nv_user.setMail(input_mail);
                                                         } else {
                                                             mail_user.setError(getString(R.string.error_mail_user));
                                                             focusView = mail_user;
                                                         }
                                                         m.reset();
                                                     }
                                                 });


        adresse_user.addTextChangedListener(new

                                                    TextWatcher() {
                                                        @Override
                                                        public void afterTextChanged
                                                                (Editable s) {


                                                        }

                                                        @Override
                                                        public void beforeTextChanged
                                                                (CharSequence s, int start,
                                                                 int count, int after) {


                                                        }

                                                        @Override
                                                        public void onTextChanged
                                                                (CharSequence s, int start,
                                                                 int before, int count) {
                                                            input_adresse = adresse_user.getText().toString();
                                                            m = regex_alphanum.matcher(input_adresse);
                                                            if (m.find()) {
                                                                nv_user.setAdresse(input_adresse);
                                                            } else {
                                                                adresse_user.setError(getString(R.string.error_adresse_user));
                                                                focusView = adresse_user;
                                                            }
                                                            m.reset();


                                                        }
                                                    }

        );
        ville_user.addTextChangedListener(new

                                                  TextWatcher() {
                                                      @Override
                                                      public void afterTextChanged
                                                              (Editable s) {


                                                      }

                                                      @Override
                                                      public void beforeTextChanged
                                                              (CharSequence s, int start,
                                                               int count, int after) {


                                                      }

                                                      @Override
                                                      public void onTextChanged
                                                              (CharSequence s, int start,
                                                               int before, int count) {
                                                          input_ville = ville_user.getText().toString();
                                                          m = regex_alpha.matcher(input_ville);
                                                          if (m.find()) {
                                                              nv_user.setVille(input_ville);
                                                          } else {
                                                              ville_user.setError(getString(R.string.error_ville_user));
                                                              focusView = ville_user;
                                                          }
                                                          m.reset();


                                                      }
                                                  }

        );
        cp_user.addTextChangedListener(new

                                               TextWatcher() {
                                                   @Override
                                                   public void afterTextChanged
                                                           (Editable s) {


                                                   }

                                                   @Override
                                                   public void beforeTextChanged
                                                           (CharSequence s, int start,
                                                            int count, int after) {


                                                   }

                                                   @Override
                                                   public void onTextChanged
                                                           (CharSequence s, int start,
                                                            int before, int count) {
                                                       input_cp = cp_user.getText().toString();
                                                       m = regex_num.matcher(input_cp);
                                                       if (m.find()) {
                                                           nv_user.setCp(input_cp);
                                                       } else {
                                                           cp_user.setError(getString(R.string.error_cp_user));
                                                           focusView = cp_user;
                                                       }
                                                       m.reset();


                                                   }
                                               }

        );
        tel_user.addTextChangedListener(new

                                                TextWatcher() {
                                                    @Override
                                                    public void afterTextChanged
                                                            (Editable s) {


                                                    }

                                                    @Override
                                                    public void beforeTextChanged
                                                            (CharSequence s, int start,
                                                             int count, int after) {


                                                    }

                                                    @Override
                                                    public void onTextChanged
                                                            (CharSequence s, int start,
                                                             int before, int count) {
                                                        input_tel = tel_user.getText().toString();
                                                        m = regex_tel.matcher(input_tel);
                                                        if (m.find()) {
                                                            nv_user.setTelephone(input_tel);
                                                        } else {
                                                            tel_user.setError(getString(R.string.error_tel_user));
                                                            focusView = tel_user;
                                                        }
                                                        m.reset();


                                                    }
                                                }

        );
        mobile_user.addTextChangedListener(new

                                                   TextWatcher() {
                                                       @Override
                                                       public void afterTextChanged
                                                               (Editable s) {


                                                       }

                                                       @Override
                                                       public void beforeTextChanged
                                                               (CharSequence s, int start,
                                                                int count, int after) {


                                                       }

                                                       @Override
                                                       public void onTextChanged
                                                               (CharSequence s, int start,
                                                                int before, int count) {
                                                           input_mobile = mobile_user.getText().toString();
                                                           m = regex_mobile.matcher(input_mobile);
                                                           if (m.find()) {
                                                               nv_user.setMobile(input_mobile);
                                                           } else {
                                                               mobile_user.setError(getString(R.string.error_mobile_user));
                                                               focusView = mobile_user;
                                                           }
                                                           m.reset();


                                                       }
                                                   }

        );


    }


}


