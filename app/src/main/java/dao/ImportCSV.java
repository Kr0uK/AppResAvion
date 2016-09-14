package dao;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cdi.appresavion.R;

/**
 * Created by user on 14/09/2016.
 */
public class ImportCSV {

/*

    Log.w("TAG", "début");

    DAOBase daoBase = new DAOBase(getApplicationContext());
    daoBase.getDb();

    Log.w("TAG", "avant l'inputstream");
    InputStream fic = getResources().openRawResource(R.raw.aeroports);
    InputStreamReader streamReader = new InputStreamReader(fic);
    BufferedReader bufferedReader = new BufferedReader(streamReader);
    String line;
    StringBuilder text = new StringBuilder();
    Log.w("TAG", "avant le try");
    try {
        while ((line = bufferedReader.readLine()) != null) {
            //  for (int i=0; i< 8108;i++){
            Log.w("TAG", "dans la boucle");
            line = bufferedReader.readLine();
            //  text.append(line);
            //  text.append('\n');

            String[] colums = line.split(",");
                /*
                ContentValues cv = new ContentValues();

                cv.put(AeroportDAO.AEROPORT_NOM,  colums[0].trim());
                cv.put(AeroportDAO.AEROPORT_VILLE, colums[1].trim());
                cv.put(AeroportDAO.AEROPORT_PAYS, colums[2].trim() );
                cv.put(AeroportDAO.AEROPORT_CODE,  colums[3].trim());
                cv.put(AeroportDAO.AEROPORT_LATITUDE, colums[4].trim());
                cv.put(AeroportDAO.AEROPORT_LONGITUDE, colums[5].trim());
            Log.w("TAG", "Nom : " + colums[0].trim() + " Ville: " + colums[1].trim() + " Pays: " + colums[2].trim() + " Code : " + colums[3].trim() + " LATITUDE :" + colums[4].trim() + " LONGITUDE :" + colums[5].trim());

                int index1 = line.indexOf(",");
                int index2 = line.indexOf(",", index1 + 1);
                int index3 = line.indexOf(",", index2 + 1);
                int index4 = line.indexOf(",", index3 + 1);
                int index5 = line.indexOf(",", index4 + 1);
                int index6 = line.indexOf(",", index5 + 1);
                int index7 = line.indexOf(",", index6 + 1);
                int index8 = line.indexOf(",", index7 + 1);

                double latit = Double.parseDouble(line.substring(index4 + 1, index5 - 1));
                double longit = Double.parseDouble(line.substring(index5 + 1, index6 - 1));


            Aeroport aeroport = new Aeroport(0, colums[0].trim(), colums[1].trim(), colums[2].trim(), colums[3].trim(), Double.parseDouble(colums[4].trim()), Double.parseDouble(colums[5].trim()));

            Log.w("TAG", "Nom : " + aeroport.getNom() + " Ville: " + aeroport.getVille() + " Pays: " + aeroport.getPays() + " AITA : " + aeroport.getCode() + " LATITUDE :" + aeroport.getLatitude() + " LONGITUDE :" + aeroport.getLongitude());


            //DAOBase.getWDb().insert(AeroportDAO.TABLE_AEROPORT, null, cv);
            // AeroportDAO.ajouterAeroport(aeroport);
            Log.w("TAG", "ajouté");
        }
    } catch (IOException e) {
        e.getMessage();
        e.printStackTrace();
    }
}


        InputStream inStream = null;


        Log.w("TAG", "avant le try");
        try {
             inStream = getAssets().open("/src/main/assets/aeroports");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.w("TAG", "ca lit");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";

        try {
            while ((line = buffer.readLine()) != null) {
                String[] colums = line.split(",");
                if (colums.length != 5) {
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
                ContentValues cv = new ContentValues(7);
                cv.put(AeroportDAO.AEROPORT_ID,"");
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
//DAOBase.getWDb().execSQL("INSERT INTO AEROPORT VALUES (null,\"Goroka\",\"Goroka\",\"Papua New Guinea\",\"GKA\", -6.081689, 145.391881");
//DAOBase.getWDb().rawQuery("INSERT INTO AEROPORT VALUES (null,\"Goroka\",\"Goroka\",\"Papua New Guinea\",\"GKA\", -6.081689, 145.391881)", null);

}
