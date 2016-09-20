package cdi.appresavion;

/**
 * Created by Frédéic
 * 20/09/2016
 */
public class Choix_Avion {

    /* Déclaration des variables (+GET/SET)*/
    // txtbox Aeroport Depart
    private static String aeroDep;

    public void setAeroDep(String data) {
        aeroDep = data;
    }

    public String getAeroDep() {
        return aeroDep;
    }

    // ID Aeroport Départ
    private static int aeroDepId;

    public void setAeroDepId(int data) {
        aeroDepId = data;
    }

    public int getAeroDepId() {
        return aeroDepId;
    }

    // txtbox Aeroport Arrivee
    private static String aeroArr;

    public void setAeroArr(String data) {
        aeroArr = data;
    }

    public String getAeroArr() {
        return aeroArr;
    }

    // ID Aeroport Arrivee
    private static int aeroArrId;

    public void setAeroArrId(int data) {
        aeroArrId = data;
    }

    public int getAeroArrId() {
        return aeroArrId;
    }

    // txtbox date Depart
    private static String aeroDateDep;

    public void setAeroDateDep(String data) {
        aeroDateDep = data;
    }

    public String getAeroDateDep() {
        return aeroDateDep;
    }

    // txtbox date Retour
    private static String aeroDateRet;

    public void setAeroDateRet(String data) {
        aeroDateRet = data;
    }

    public String getAeroDateRet() {
        return aeroDateRet;
    }

    // CONSTRUCTEUR
    public Choix_Avion() {
    }

    public Choix_Avion(String DateDep) {
        super();
        this.aeroDateDep = DateDep;
    }

    public Choix_Avion(String AeroportDep, String AeroportArr, String DateDep) {
        super();
        this.aeroDep = AeroportDep;
        this.aeroArr = AeroportArr;
        this.aeroDateDep = DateDep;
    }

    public Choix_Avion(String AeroportDep, String AeroportArr, String DateDep, String DateRet) {
        super();
        this.aeroDep = AeroportDep;
        this.aeroArr = AeroportArr;
        this.aeroDateDep = DateDep;
        this.aeroDateRet = DateRet;
    }
}
