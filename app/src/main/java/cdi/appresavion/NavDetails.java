package cdi.appresavion;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Frédéric on 12/09/2016.
 */
public class NavDetails {
    /**
     * id contenant le titre de la fenêtre
     */
    public final int titleId;

    /**
     * id contenant la description de la fenêtre.
     */
    public final int descriptionId;

    /**
     * le nom de la classe ouvrant la fenêtre
     */
    public final Class<? extends AppCompatActivity> activityClass;

    public NavDetails(
            int titleId, int descriptionId, Class<? extends AppCompatActivity> activityClass) {
        this.titleId = titleId;
        this.descriptionId = descriptionId;
        this.activityClass = activityClass;
    }
}
