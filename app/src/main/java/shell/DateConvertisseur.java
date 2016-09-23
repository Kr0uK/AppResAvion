package shell;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bigwanjeog.
 * 13/09/2016
 */
public class DateConvertisseur {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Permet de convertir une Date en String.
     * @param dateToConvert la date a convertir
     * @return String
     */
    public static String dateToString(Date dateToConvert) {
        return sdf.format(dateToConvert);
    }

    /**
     * Permet de convertir un String en Date.
     * @param stringToConvert le string a convertir
     * @return Date
     */
    public static Date stringToDate(String stringToConvert) {
        try {
            return sdf.parse(stringToConvert);
        } catch (Exception e) {
            Log.w("TEST", e);
            return null;
        }
    }

    /**
     * Récupére la date du systeme.
     * @return String de la date du systeme
     */
    public static String dateSysString() {
        Calendar calendar = Calendar.getInstance();
        return dateToString(calendar.getTime());
    }

    /**
     * Récupére la date du systeme.
     * @return date de la date du systeme
     */
    public static Date dateSysDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    /**
     * Change le format de la date en dd/MM/yyyy HH:mm.
     * @param date
     * @return string de la date
     */
    public static String dateToStringFormatShow(Date date){
        SimpleDateFormat sdfNew = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return sdfNew.format(date);
    }

    /**
     * Transforme le string en date dans le format dd/MM/yyyy.
     * @param stringToConvert string du calendrier
     * @return date
     */
    public static Date stringToDateTemp (String stringToConvert) {
        SimpleDateFormat sdfTemp = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdfTemp.parse(stringToConvert);
        } catch (Exception e) {
            return null;
        }
    }
}