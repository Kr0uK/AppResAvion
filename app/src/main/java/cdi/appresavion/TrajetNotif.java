package cdi.appresavion;

/**
 * Created by bigwanjeog
 * 30/09/2016
 */
public class TrajetNotif {
    private static long dateDepart;
    private static String aeroportDepart;
    private static String aeroportArrivee;

    public TrajetNotif() {
    }

    public TrajetNotif(long dateDepart, String aeroportDepart, String aeroportArrivee) {
        this.dateDepart = dateDepart;
        this.aeroportDepart = aeroportDepart;
        this.aeroportArrivee = aeroportArrivee;
    }

    public long getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(long dateDepart) {
        TrajetNotif.dateDepart = dateDepart;
    }

    public String getAeroportDepart() {
        return aeroportDepart;
    }

    public void setAeroportDepart(String aeroportDepart) {
        TrajetNotif.aeroportDepart = aeroportDepart;
    }

    public String getAeroportArrivee() {
        return aeroportArrivee;
    }

    public void setAeroportArrivee(String aeroportArrivee) {
        TrajetNotif.aeroportArrivee = aeroportArrivee;
    }
}
