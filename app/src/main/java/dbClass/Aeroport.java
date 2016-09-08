package dbClass;

/**
 * Created by RENAUD on 06/09/2016.
 */
public class Aeroport {
    private int id;
    private String nom;
    private String code;
    private String localisation;
    private String cp;
    private String ville;
    private String telephone;
    private String pays;

    public Aeroport(int id, String nom, String code, String localisation, String cp, String ville, String telephone, String pays) {
        this.id = id;
        this.nom = nom;
        this.code = code;
        this.localisation = localisation;
        this.cp = cp;
        this.ville = ville;
        this.telephone = telephone;
        this.pays = pays;
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

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
}
