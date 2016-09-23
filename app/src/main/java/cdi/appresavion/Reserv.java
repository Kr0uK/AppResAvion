package cdi.appresavion;

/**
 * Created by Frédéric on 22/09/2016.
 */
public class Reserv {

    // VARIABLES D'ENVIRONNEMENT
    private String aeroDep;     // Aeroport de départ
    private String aeroArr;     // Aeroport d'arrivée
    private String hourDep;     // Heure de départ
    private String Id;          // ID du trajet
    private String IdReserv;    // ID du numero de reservation


    // CONSTRUCTEURS (avec surcharge pour definir les variables lors interrogation BDD)
    public Reserv() {
    }
    public Reserv(String depart, String arrivee, String heure, String id, String idReserv) {
        super();
        this.aeroDep = depart;
        this.aeroArr = arrivee;
        this.hourDep = heure;
        this.Id = id;
        this.IdReserv = idReserv;
    }

    // GET/SET : Accesseurs/Mutateurs
    public String getDepart() {
        return aeroDep;
    }
    public void setDepart(String data) {
        aeroDep = data;
    }

    public String getArrivee() {
        return aeroArr;
    }
    public void setArrivee(String data) {
        aeroArr = data;
    }

    public String getHeure() { return hourDep;  }
    public void setHeure(String data) {
        hourDep = data;
    }

    public String getId() {
        return Id;
    }
    public void setId(String data) {
        this.Id = data;
    }

    public String getIdReserv() {
        return IdReserv;
    }
    public void setIdReserv(String data) {
        this.IdReserv = data;
    }
}

