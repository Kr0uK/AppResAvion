package cdi.appresavion;

//LIBRAIRIES
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;


/**
 * Classe permettant la conversion d'une adresse en coordonnées GPS (longitude/latitude)
 */
public class GeoCoder extends AsyncTask<String, Void, String[]> {

    private Context mContext;   // Pour permettre d'utiliser le contexte de l'activité appellante

    ProgressDialog dialog = new ProgressDialog(mContext.getApplicationContext());

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Chargement en cours, on active le message d'attente
        dialog.setMessage("Veuillez patienter...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected String[] doInBackground(String... params) {
        String response;
        try {
            response = getLatLongByURL("http://maps.google.com/maps/api/geocode/json?address=mumbai&sensor=false");
            Log.d("reponse",""+response);
            return new String[]{response};
        } catch (Exception e) {
            return new String[]{"erreur"};
        }
    }

    @Override
    protected void onPostExecute(String... result) {
        try {
            JSONObject jsonObject = new JSONObject(result[0]);

            double lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lng");

            double lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0)
                    .getJSONObject("geometry").getJSONObject("location")
                    .getDouble("lat");

            Log.d("latitude", "" + lat);
            Log.d("longitude", "" + lng);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Chargement terminé, on desactive le message d'attente
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public String getLatLongByURL(String requestURL) {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }

        } catch (Exception e) {
            Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
        return response;
    }
}
