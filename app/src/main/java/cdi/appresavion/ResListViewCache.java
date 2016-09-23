package cdi.appresavion;

// LIBRAIRIES
import android.view.View;
import android.widget.TextView;

/**
 * Created by Frédéric on 22/09/2016
 *
 * Pour le rendu de chaque ligne , nous créons une nouvelle vue et nous prenons chaque composant
 * graphique de celui-ci (beaucoup findViewById de ce fait...). Ces opérations sont très coûteuses
 * en termes de mémoire et cpu. Pour l'éviter , nous pouvons modifier notre adaptateur pour
 * utiliser un cache de vue.
 */
public class ResListViewCache {

    private View baseView;
    private TextView txtDep;
    private TextView txtArr;
    private TextView txtHeure;
    private TextView txtId;

    public ResListViewCache ( View baseView ) {
        this.baseView = baseView;
    }
    public View getViewBase (  ) {
        return baseView;
    }

    public TextView getTxtDepart (int resource) {
        if ( txtDep == null ) {
            txtDep = ( TextView ) baseView.findViewById(R.id.txtDep);
        }
        return txtDep;
    }

    public TextView getTxtArrivee (int resource) {
        if ( txtArr == null ) {
            txtArr = ( TextView ) baseView.findViewById(R.id.txtArr);
        }
        return txtArr;
    }

    public TextView getTxtHeure (int resource) {
        if ( txtHeure == null ) {
            txtHeure = ( TextView ) baseView.findViewById(R.id.txtHeure);
        }
        return txtHeure;
    }

    public TextView getTxtId (int resource) {
        if ( txtId == null ) {
            txtId = ( TextView ) baseView.findViewById(R.id.txtId);
        }
        return txtId;
    }
}