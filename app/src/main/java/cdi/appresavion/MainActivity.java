package cdi.appresavion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import dao.AeroportDAO;
import dao.DAOBase;
import dao.ReservationDAO;
import dbclass.Aeroport;
import dbclass.Reservation;


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

        //Test des dates sur Reservation
        Reservation reservation = new Reservation(1, DateConvertisseur.stringToDate("16/10/1993 03:30"), 1500, 3);
        ReservationDAO.ajouterReservation(reservation);
        Log.w("TEST", DateConvertisseur.dateSys() + " !");
        Reservation reservation2 = ReservationDAO.selectionnerReservation(1);
        String testR = DateConvertisseur.dateToString(reservation2.getDate());
        Log.w("TEST", testR);

        //Test d'aeroport
        Aeroport aeroport = new Aeroport("Goroka", "Goroka", "Papua New Guinea", "GKA", -6.081689, 145.391881); //Objet aeroport
        AeroportDAO.ajouterAeroport(aeroport);                                                                  //Ajout de l'aeroport
        Aeroport aeroTest = AeroportDAO.selectionnerAeroport(1);                                            //Selection de l'aeroport 1
        String stringAero = aeroTest.getNom() + " situé : " + aeroTest.getLatitude() + " / " + aeroTest.getLongitude();
        Log.w("TEST", stringAero);

        /*
        //try and catch si l'utilisateur selectionner est vide
        try{
            Utilisateur utilTest = UtilisateurDAO.selectionnerUtilisateur(1);
            String testString = utilTest.getNom() + " " + utilTest.getPrenom() + " " + utilTest.getMail();
            Log.w("TEST",testString);

        } catch (Exception e){
            Log.w("TEST", e);
        }*/


        //Supprimer un utilisateur
        //UtilisateurDAO.supprimerUtilisateur(3);
    }
}
