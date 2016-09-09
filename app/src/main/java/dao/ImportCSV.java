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
import cdi.appresavion.DatabaseHandler;
import dao.AeroportDAO;
import dbClass.Aeroport;
import dao.DAOBase;

public class ImportCSV {



    //TODO faire marcher ça
    public  ImportCSV (Context context) {
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
        db.beginTransaction();
        try {
            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length != 5) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues(5);
                cv.put(, colums[0].trim());
                cv.put(, colums[1].trim());
                cv.put(, colums[2].trim());
                cv.put(, colums[3].trim());
                cv.put(, colums[4].trim());
                db.insert(, null, cv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

    }
}
