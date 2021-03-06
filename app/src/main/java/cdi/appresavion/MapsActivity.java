package cdi.appresavion;

// TODO : Etape suivante : https://developers.google.com/maps/documentation/distance-matrix/?hl=fr

// LIBRAIRIES
import android.*;
import android.Manifest;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;

public class MapsActivity extends AppCompatActivity
        implements
        OnMyLocationButtonClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback
{
    // Variables d'environnement pour l'API Google
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;
    private GoogleMap mMap;
    private Location location;
    private Marker mCurrLocationMarker;
    private LocationRequest mLocationRequest;
    private SupportMapFragment mapFrag;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Ident_User logUser = new Ident_User();
        //Toast.makeText(this, logUser.getEmail(), Toast.LENGTH_SHORT).show();;


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        int i = 1;

        //CONFIG
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setIndoorLevelPickerEnabled(false);
        map.getUiSettings().setCompassEnabled(false);

        Geoloc_Aeroport Aeroport = new Geoloc_Aeroport();
        // Ajoute un marqueur et déplacement de la camera :
        if (Aeroport.getNomCoord() == null) {
            Aeroport.setNomCoord("Place Stanislas, Nancy");
            Aeroport.setLongitude(48.693526);
            Aeroport.setLatitude(6.183232);
        }

        addMarker(Aeroport.getNomCoord(), Aeroport.getLongitude(), Aeroport.getLatitude());
        //Toast.makeText(this, Aeroport.getNomCoord()+" : "+String.valueOf(Aeroport.getLongitude())+" / "+String.valueOf(Aeroport.getLatitude()), Toast.LENGTH_SHORT).show();

        mMap.setOnMyLocationButtonClickListener(this);
        enableMyLocation();
    }


    public void addMarker (String nom, double longit, double latit) {
        LatLng posAeroport = new LatLng(longit, latit);
        mMap.addMarker(new MarkerOptions().position(posAeroport).title(nom));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(posAeroport));
    }

    private void enableMyLocation() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE, Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {

            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            try {
                //TODO (String.valueOf(location.getLongitude()).equals(null))
                if (String.valueOf(location.getLongitude()).equals(null)) {
                    Toast.makeText(getApplicationContext(), "Votre géolocalisation à échouée, impossible d'enregistrer votre position actuelle !", Toast.LENGTH_SHORT).show();
                } else {
                    Geoloc_User User = new Geoloc_User();
                    User.setLongitude((double) location.getLongitude());
                    User.setLatitude((double) location.getLatitude());
                    Toast.makeText(getApplicationContext(), "Coordonnée GPS : "+User.getLatitude()+" ; "+User.getLatitude(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                //Toast.makeText(this, String.valueOf(location), Toast.LENGTH_LONG).show();
                Log.w("ERROR",e.toString());
            }
            /*
            final LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    longitude = location.getLongitude();
                    latitude = location.getLatitude();
                    Toast.makeText(getApplicationContext(), String.format("longitude : " + longitude + "\nlatitude : " + latitude), Toast.LENGTH_SHORT).show();
                }
                public void onLocationChanged(Location location) {
                    Geoloc_User User = new Geoloc_User();
                    User.setLongitude(location.getLongitude());
                    User.setLatitude(location.getLatitude());
                    Toast.makeText(getApplicationContext(), String.format("longitude : " + User.getLongitude() + "\nlatitude : " + User.getLatitude()), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, (long) 2000, (float) 10, locationListener);
            }
*/
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        //String s = ""+event.latLng;
        Toast.makeText(this, R.string.geoloc_ok, Toast.LENGTH_SHORT).show();
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            enableMyLocation();
        } else {
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }
}





/** LOCALISATION GPS DE L'AEROPORT : */
class Geoloc_Aeroport {

    /* Déclaration des variables (+GET/SET)*/
    // Nom de l'Aéroport sélectionné
    private static String nomCoord;
    public void setNomCoord ( String data){
        nomCoord = data;
    }
    public String getNomCoord(){
        return nomCoord;
    }
    // Coordonnée longitudinale de l'Aéroport
    private static double longitude;
    public void setLongitude ( double data){
        longitude = data;
    }
    public double getLongitude(){
        return longitude;
    }
    // Coordonnée latitudinale de l'Aéroport
    private static double latitude;
    public void setLatitude ( double data){
        latitude = data;
    }
    public double getLatitude() {
        return latitude;
    }

    // Surcharge sur le constructeur
    public Geoloc_Aeroport() {
        // Ce constructeur vide sert uniquement à instancier la classe
        // Rien n'empeche d'utiliser les accesseurs/mutateurs (get/set)
    }
    public Geoloc_Aeroport(String nom, double longit, double latit)
    {
        // Ce constructeur permet d'instancier la classe et déclarer des valeurs (SET)
        // Rien n'empeche par la suite d'utiliser les accesseurs/mutateurs (get/set)
        super();
        this.nomCoord = nom;
        this.longitude = longit;
        this.latitude  = latit;
    }
}

/** LOCALISATION GMS/GPS DE L'UTILISATEUR : */
class Geoloc_User {

    /* Déclaration des variables (+GET/SET)*/
    // Coordonnée longitudinale de l'user
    private static double longitude;
    public void setLongitude ( double data){
        longitude = data;
    }
    public double getLongitude(){
        return longitude;
    }
    // Coordonnée latitudinale de l'user
    private static double latitude;
    public void setLatitude ( double data){
        latitude = data;
    }
    public double getLatitude() {
        return latitude;
    }

    public Geoloc_User() {    }
    public Geoloc_User(double longit, double latit)
    {
        super();
        this.longitude = longit;
        this.latitude  = latit;
    }
}