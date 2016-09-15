package shell;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.CursorJoiner;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

import cdi.appresavion.MainActivity;
import dao.AeroportDAO;
import dao.DAOBase;
import dao.ReservationDAO;
import dao.UtilisateurDAO;
import dbclass.Aeroport;
import dbclass.Reservation;
import dbclass.Utilisateur;

/**
 * Created by user on 08/09/2016.
 */
public class ThreadHandler extends Thread {


    //On initialise le Context
    private Context context;
    private DAOBase daoBase = new DAOBase(context);

    public ThreadHandler(Context context) {
        this.context = context;
    }


    //Thread du premier boot pour créer et remplir la BDD
    public class FirstBoot implements Runnable {



        @Override
        public void run() { //La méthode qui va exécuter le code du thread

            daoBase.getWDb();
            Log.w("TAG", "On rentre bien dans le thread");

            //Ajout d'un utilisateur util
            Utilisateur util = new Utilisateur("METZ", "Renaud", "renaudmtz@gmail.com", "0388943632", "0622493390", "33 rue de la paix", "67160", "OBERLAUTERBACH", "renaud", "1610");
            UtilisateurDAO.ajouterUtilisateur(util);
            Log.w("TAG", "Bien ajouté");

            UtilisateurDAO.ajouterUtilisateur(new Utilisateur("Kenobi", "Obi-Wan", "obiwan@kenobi.jedi", "0000000000", "0000000000", "33 rue de la paix", "68000", "COLMAR", "Obiwan", "12345678"));
            Log.w("TAG", "Bien ajouté");

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
            //Objet aeroport
            Aeroport aeroport = new Aeroport("Renaud", "METZ", "Papua New Guinea", "GKA", -6.081689, 145.391881);
            //Ajout de l'aeroport
            AeroportDAO.ajouterAeroport(aeroport);



        }


    }


    public class LoginCheck implements Runnable {




        @Override
        public void run() {
            daoBase.getRDb();

            daoBase.close();
        }
    }
}






