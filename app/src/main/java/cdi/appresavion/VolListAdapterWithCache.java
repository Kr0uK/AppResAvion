package cdi.appresavion;

// LIBRAIRIES
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;


/**
 * Created by Frédéric on 15/09/2016 - Last edit : 21/09/2016
 *
 * Creation de l'adaptateur du cache : Dans ce cas, lorsque le système veut rendre une rangée, il
 * utilise la classe de cache pour prendre toutes les composantes de notre mise en page.
 * De cette façon, notre ListView sera très bien pour contenir de nombreuses lignes.
 */
public class VolListAdapterWithCache extends ArrayAdapter<Vol> {

    private int	resource;
    private LayoutInflater inflater;
    private Context context;

    public VolListAdapterWithCache ( Context ctx, int resourceId, List<Vol> objects) {
        super( ctx, resourceId, objects );
        resource = resourceId;
        inflater = LayoutInflater.from( ctx );
        context=ctx;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent ) {

        Vol newVol = getItem( position );
        VolListViewCache viewCache;

        if ( convertView == null ) {
            convertView = (RelativeLayout) inflater.inflate( resource, null );
            viewCache = new VolListViewCache( convertView );
            convertView.setTag( viewCache );
        }
        else {
            viewCache = ( VolListViewCache ) convertView.getTag();
        }

        TextView txtDep = viewCache.getTxtDepart(resource);
        txtDep.setText(newVol.getDepart());
        TextView txtArr = viewCache.getTxtArrivee(resource);
        txtArr.setText(newVol.getArrivee());
        TextView txtCod = viewCache.getTxtCode(resource);
        txtCod.setText(newVol.getCode());
        TextView txtPri = viewCache.getTxtPrix(resource);
        txtPri.setText(newVol.getPrix());
        TextView txtId = viewCache.getTxtId(resource);
        txtId.setText(newVol.getId());

        return convertView;
    }
}
