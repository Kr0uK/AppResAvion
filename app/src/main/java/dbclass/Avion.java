package dbclass;

/**
 * Created by bigwanjeog.
 * 07/09/2016
 */
public class Avion {
    private int id;
    private String modele;
    private String constructeur;
    private int nbPlaces;
    private String compagnie;

    public Avion() {
    }

    public Avion(String modele, String constructeur, int nbPlaces, String compagnie) {
        this.modele = modele;
        this.constructeur = constructeur;
        this.nbPlaces = nbPlaces;
        this.compagnie = compagnie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getConstructeur() {
        return constructeur;
    }

    public void setConstructeur(String constructeur) {
        this.constructeur = constructeur;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public String getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(String compagnie) {
        this.compagnie = compagnie;
    }
}
