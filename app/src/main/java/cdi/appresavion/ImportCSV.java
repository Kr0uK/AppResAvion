package cdi.appresavion;

import android.content.ContentValues;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ImportCSV {



    //TODO faire marcher ça
    public static () {
        String mCSVfile = "file.csv";
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
                if (colums.length != 4) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues(3);
                cv.put(dbCol0, colums[0].trim());
                cv.put(dbCol1, colums[1].trim());
                cv.put(dbCol2, colums[2].trim());
                cv.put(dbCol3, colums[3].trim());
                cv.put(dbCol4, colums[4].trim());
                db.insert(TABLE, null, cv);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

    }
}
