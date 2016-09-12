package cdi.appresavion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import dao.AeroportDAO;
import dao.DAOBase;
import dao.ReservationDAO;
import dao.UtilisateurDAO;
import dbClass.Aeroport;
import dbClass.Reservation;
import dbClass.Utilisateur;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Création de la base
        DAOBase daoBase = new DAOBase(getApplicationContext());
        daoBase.getWDb();

        //Ajout d'un utilisateur util
        //Utilisateur Util =  new Utilisateur("METZ", "Renaud", "renaudmtz@gmail.com", "0388943632", "0622493390", "33 rue de la paix", "67160", "OBERLAUTERBACH", "renaud", "1610");
        //UtilisateurDAO.ajouterUtilisateur(Util);

        //Modifier un utilisateur
        //UtilisateurDAO.modifierUtilisateur(Util, 1);
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Reservation Reserv = new Reservation(1, sdf.parse("1994-16-10"), 1500,3);
            ReservationDAO.ajouterReservation(Reserv);
            Log.w("TEST", sdf.format(Reserv.getDate()));
        }catch (Exception e){
            Log.w("TEST","nop");
        }*/

        Aeroport Aero = new Aeroport("Goroka", "Goroka", "Papua New Guinea", "GKA", -6.081689, 145.391881); //Objet aeroport
        AeroportDAO.ajouterAeroport(Aero);                                                                  //Ajout de l'aeroport
        Aeroport aeroTest = AeroportDAO.selectionnerAeroport(1);                                                //Selection de l'aeroport 1
        String stringAero = aeroTest.getNom() + " situé : " + aeroTest.getLatitude() + " / " + aeroTest.getLongitude();
        Log.w("TEST", stringAero);
        /*
        try{
            Utilisateur UtilTest = UtilisateurDAO.selectionnerUtilisateur(1);
            String testString = UtilTest.getNom() + " " + UtilTest.getPrenom() + " " + UtilTest.getMail();
            Log.w("TEST",testString);
            Reservation R = ReservationDAO.selectionnerReservation(1);
            String testR = sdf.format(R.getDate());
            Log.w("TEST", testR);
        } catch (Exception e){
            Log.w("TEST", e);
        }*/


        //Supprimer un utilisateur
        //UtilisateurDAO.supprimerUtilisateur(3);
    }
}
