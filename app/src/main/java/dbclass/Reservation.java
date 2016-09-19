package dbclass;

import java.util.Date;

/**
 * Created by bigwanjeog.
 * 06/09/2016
 */
public class Reservation {
    private int reservationId;
    private int utilisateurId;
    private Date date;
    private int prix;
    private int nbPersonnes;

    public Reservation() {
    }

    public Reservation(int reservationId, int utilisateurId, Date date, int prix, int nbPersonnes) {
        this.reservationId = reservationId;
        this.utilisateurId = utilisateurId;
        this.date = date;
        this.prix = prix;
        this.nbPersonnes = nbPersonnes;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }
}