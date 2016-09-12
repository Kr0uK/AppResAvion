package dbClass;

/**
 * Created by bigwanjeog
 * 12/09/2016
 */
public class Place {
    private int reservationId;
    private int trajetId;
    private int placeNum;

    public Place() {
    }

    public Place(int reservationId, int trajetId, int placeNum) {
        this.reservationId = reservationId;
        this.trajetId = trajetId;
        this.placeNum = placeNum;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getTrajetId() {
        return trajetId;
    }

    public void setTrajetId(int trajetId) {
        this.trajetId = trajetId;
    }

    public int getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(int placeNum) {
        this.placeNum = placeNum;
    }
}
