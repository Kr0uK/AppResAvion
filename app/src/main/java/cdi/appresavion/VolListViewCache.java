package cdi.appresavion;

// LIBRAIRIES
import android.view.View;
import android.widget.TextView;

/**
 * Created by Frédéric on 15/09/2016 - Last edit : 21/09/2016
 *
 * Pour le rendu de chaque ligne , nous créons une nouvelle vue et nous prenons chaque composant
 * graphique de celui-ci (beaucoup findViewById de ce fait...). Ces opérations sont très coûteuses
 * en termes de mémoire et cpu. Pour l' éviter , nous pouvons modifier notre adaptateur pour
 * utiliser un cache de vue.
 */
public class VolListViewCache {

    private View baseView;
    private TextView txtDep;
    private TextView txtArr;
    private TextView txtCod;
    private TextView txtPri;
    private TextView txtId;

    public VolListViewCache ( View baseView ) {
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

    public TextView getTxtCode (int resource) {
        if ( txtCod == null ) {
            txtCod = ( TextView ) baseView.findViewById(R.id.txtCod);
        }
        return txtCod;
    }

    public TextView getTxtPrix (int resource) {
        if ( txtPri == null ) {
            txtPri = ( TextView ) baseView.findViewById(R.id.txtPri);
        }
        return txtPri;
    }

    public TextView getTxtId (int resource) {
        if ( txtId == null ) {
            txtId = ( TextView ) baseView.findViewById(R.id.txtId);
        }
        return txtId;
    }
}