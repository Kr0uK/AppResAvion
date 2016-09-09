package dbClass;

/**
 * Created by RENAUD on 06/09/2016.
 */
public class Aeroport {
    private int id;
    private String nom;
    private String ville;
    private String pays;
    private String code;
    private float latitude;
    private float longitude;


    public Aeroport(int id, String nom,String ville, String pays, String code, float latitude,float longitude ) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
