package dbClass;

/**
 * Created by bigwanjeog
 * 06/09/2016
 */
public class Aeroport {
    private int id;
    private String nom;
    private String ville;
    private String pays;
    private String code;
    private double latitude;
    private double longitude;

    public Aeroport() {
    }

    public Aeroport(String nom, String ville, String pays, String code, double latitude, double longitude) {
        this.nom = nom;
        this.ville = ville;
        this.pays = pays;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}