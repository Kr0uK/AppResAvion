package dao;

/**
 * Created by RENAUD on 07/09/2016.
 */
public class PlaceDAO {
    static ReservationDAO r;
    static UtilisateurDAO u;

    public static final String PLACE_NUM = "¨PLACE_NUM";

    public static final String TABLE_PLACE = "PLACE";
    public static final String CREATE_PLACE = "CREATE TABLE " + TABLE_PLACE + "("
            + r.RESERVATION_ID + " INTEGER NOT NULL REFERENCES " + r.TABLE_RESERVATION + "(" + r.RESERVATION_ID + ") ON DELETE RESTRICT ON UPDATE RESTRICT, "
            + "";
    public static final String DROP_PLACE = "DROP TABLE IF EXISTS " + TABLE_PLACE + ";";
}
