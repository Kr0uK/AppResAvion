package cdi.appresavion;

import android.content.ContentValues;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import dao.DAOBase;
import dao.ImportCSV;
import dao.AeroportDAO;
import dbClass.Aeroport;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {*/

                Log.w("TAG", "début");
                DAOBase daoBase = new DAOBase(getApplicationContext());
                daoBase.getWDb();

                Aeroport aeroport;

                String mCSVfile = "assets/aeroports.csv";
                AssetManager manager = getAssets();
                InputStream inStream = null;
                Log.w("TAG", "avant le try");
                try {
                    inStream = manager.open(mCSVfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.w("TAG", "ca lit");
            //    BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
              String line = "";
        Log.w("TAG", "avant l'autre try");
                try {
                    while ((line = )) != null) {
                        String[] colums = line.split(",");
                        /*
                        if (colums.length != 6) {
                            Log.d("CSVParser", "Skipping Bad CSV Row");
                            continue;
                        } */
                        Log.w("TAG", "ca marche");
                        aeroport = new Aeroport(0,colums[0].trim().toString(),colums[1].trim().toString(),colums[2].trim().toString(),colums[3].trim().toString(),Float.parseFloat(colums[4].trim()),Float.parseFloat(colums[5].trim()));
                        AeroportDAO.ajouterAeroport(aeroport);
                        Log.d("TAG",aeroport.toString());
                        Log.d("TAG","ajouté");
                    }
                }finally {
                }
        /*catch (IOException e) {
                    Log.w("TAG", "exception");
                    e.printStackTrace();
                }
*/
                }



            }



