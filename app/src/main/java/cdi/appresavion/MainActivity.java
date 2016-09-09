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


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                DAOBase daoBase = new DAOBase(getApplicationContext());
                daoBase.getWDb();
                Aeroport aeroport;
                String mCSVfile = "aeroports.csv";
                AssetManager manager = getAssets();
                InputStream inStream = null;
                try {
                    inStream = manager.open(mCSVfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
                String line = "";
                try {
                    while ((line = buffer.readLine()) != null) {
                        String[] colums = line.split(",");
                        if (colums.length != 5) {
                            Log.d("CSVParser", "Skipping Bad CSV Row");
                            continue;
                        }

                        aeroport = new Aeroport(0,colums[0].trim(),colums[1].trim(),colums[2].trim(),colums[3].trim(),Float.parseFloat(colums[4].trim()),Float.parseFloat([5].trim()));
                        AeroportDAO.ajouterAeroport(aeroport);
                        Log.d("TAG",aeroport.toString());
                        Log.d("TAG","ajouté");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        });
    }
}
