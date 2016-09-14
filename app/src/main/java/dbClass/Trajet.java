package dbclass;

import java.util.Date;

/**
 * Created by bigwanjeog.
 * 06/09/2016
 */
public class Trajet {
    private int trajetId;
    private int avionId;
    private int aeroportId;
    private int aerAeroportId;
    private Date dateDepart;
    private Date dateArrivee;

    public Trajet() {
    }

    public Trajet(int avionId, int aeroportId, int aerAeroportId, Date dateDepart, Date dateArrivee) {
        this.avionId = avionId;
        this.aeroportId = aeroportId;
        this.aerAeroportId = aerAeroportId;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
    }

    public int getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(int trajetId) {
        this.trajetId = trajetId;
    }

    public int getAvionId() {
        return avionId;
    }

    public void setAvionId(int avionId) {
        this.avionId = avionId;
    }

    public int getAeroportId() {
        return aeroportId;
    }

    public void setAeroportId(int aeroportId) {
        this.aeroportId = aeroportId;
    }

    public int getAerAeroportId() {
        return aerAeroportId;
    }

    public void setAerAeroportId(int aerAeroportId) {
        this.aerAeroportId = aerAeroportId;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }
}