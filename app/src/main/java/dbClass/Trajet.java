package dbClass;

import java.sql.Time;
import java.util.Date;

/**
 * Created by RENAUD on 06/09/2016.
 */
public class Trajet {
    private int id;
    private int avionId;
    private int aeroportId;
    private int aerAeroportId;
    private Date dateDepart;
    private Time heureDepart;
    private Time heureArrivee;

    public Trajet(int id, int avionId, int aeroportId, int aerAeroportId, Date dateDepart, Time heureDepart, Time heureArrivee) {
        this.id = id;
        this.avionId = avionId;
        this.aeroportId = aeroportId;
        this.aerAeroportId = aerAeroportId;
        this.dateDepart = dateDepart;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Time getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Time heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Time getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Time heureArrivee) {
        this.heureArrivee = heureArrivee;
    }
}
