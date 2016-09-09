package dao;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;

import cdi.appresavion.DatabaseHandler;
import dao.AeroportDAO;
import dbClass.Aeroport;
import dao.DAOBase;

public class ImportCSV {



    //TODO faire marcher ça
    public void ImportCSV (Context context) {
        Aeroport aeroport;
        String mCSVfile = "aeroports.csv";
        AssetManager manager = context.getAssets();
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
