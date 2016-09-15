package cdi.appresavion;

// LIBRAIRIES
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Frédéric on 15/09/2016.
 * Creation de l'adaptateur du cache : Dans ce cas, lorsque le système veut rendre une rangée, il
 * utilise la classe de cache pour prendre toutes les composantes de notre mise en page
 * (getTextNameCity, getTextWikiCity, getImageView). De cette façon, notre ListView sera très
 * bien pour contenir de nombreuses lignes.
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
            convertView = ( RelativeLayout ) convertView;
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
        /*
        ImageView imageCity = viewCache.getImgLogo(resource);
        String uri = "drawable/" + newVol.getLogo();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable image = context.getResources().getDrawable(imageResource);
        imageCity.setImageDrawable(image);
    */
        return convertView;
    }
}
