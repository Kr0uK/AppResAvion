package cdi.appresavion;

//LIBRAIRES
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Frédéric on 22/09/2016
 *
 * Creation de l'adaptateur du cache : Dans ce cas, lorsque le système veut rendre une rangée, il
 * utilise la classe de cache pour prendre toutes les composantes de notre mise en page.
 * De cette façon, notre ListView sera très bien pour contenir de nombreuses lignes.
 */
public class ResListAdapterWithCache extends ArrayAdapter<Reserv> {

    private int	resource;
    private LayoutInflater inflater;
    private Context context;

    public ResListAdapterWithCache ( Context ctx, int resourceId, List<Reserv> objects) {
        super( ctx, resourceId, objects );
        resource = resourceId;
        inflater = LayoutInflater.from( ctx );
        context=ctx;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent ) {

        Reserv newReserv = getItem( position );
        ResListViewCache viewCache;

        if ( convertView == null ) {
            convertView = (RelativeLayout) inflater.inflate( resource, null );
            viewCache = new ResListViewCache( convertView );
            convertView.setTag( viewCache );
        }
        else {
            viewCache = ( ResListViewCache ) convertView.getTag();
        }

        TextView txtDep = viewCache.getTxtDepart(resource);
        txtDep.setText(newReserv.getDepart());

        TextView txtArr = viewCache.getTxtArrivee(resource);
        txtArr.setText(newReserv.getArrivee());

        TextView txtHeure = viewCache.getTxtHeure(resource);
        txtHeure.setText(newReserv.getHeure());

        TextView txtId = viewCache.getTxtId(resource);
        txtId.setText(newReserv.getId());

        return convertView;
    }
}

