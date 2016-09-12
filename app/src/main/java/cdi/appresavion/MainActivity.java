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

import dao.AeroportDAO;
import dao.DAOBase;
import dbClass.Aeroport;


public class MainActivity extends AppCompatActivity {



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
        daoBase.getDb();


/*
        String mCSVfile = "/assets/aeroports.csv";
        AssetManager manager = getAssets();
        InputStream inStream = null;
        Log.w("TAG", "avant le try");
        try {
            inStream = manager.open(mCSVfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.w("TAG", "ca lit");
        /BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";

        try {
            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length != 4) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues(6);
                cv.put(AeroportDAO.AEROPORT_NOM, colums[0].trim());
                cv.put(AeroportDAO.AEROPORT_VILLE, colums[1].trim());
                cv.put(AeroportDAO.AEROPORT_PAYS, colums[2].trim());
                cv.put(AeroportDAO.AEROPORT_CODE, colums[3].trim());
                cv.put(AeroportDAO.AEROPORT_LATITUDE, colums[4].trim());
                cv.put(AeroportDAO.AEROPORT_LONGITUDE, colums[5].trim());

                DAOBase.getWDb().insert(AeroportDAO.TABLE_AEROPORT, null, cv);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
        DAOBase.getWDb().execSQL("INSERT INTO AEROPORT VALUES (null,\"Goroka\",\"Goroka\",\"Papua New Guinea\",\"GKA\", -6.081689, 145.391881");
        DAOBase.getWDb().rawQuery("INSERT INTO AEROPORT VALUES (null,\"Goroka\",\"Goroka\",\"Papua New Guinea\",\"GKA\", -6.081689, 145.391881)", null);
        Log.w("TAG", "ajouté");
    }

}



