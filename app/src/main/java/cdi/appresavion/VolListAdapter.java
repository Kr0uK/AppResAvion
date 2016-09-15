package cdi.appresavion;

//LIBRAIRIES
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.List;

/*
 * Dans le constructeur nous sauvons la mise en page personnalisée (resourceId), créer un objet
 * "LayoutInflater" (nous allons l' utiliser pour créer une vue de notre mise en page) et
 * sauvegarder le contexte (nous en aurons besoin juste pour accéder aux ressources).
 *
 * Ensuite, nous créons la méthode "getView". Android va l'appeler à chaque fois qu'il a besoin
 * de rendre une rangée. Dans cette méthode, nous avons :
 *
 * 1) créer une nouvelle vue par LayoutInflater et le mettre dans la vue de la rangée (convertView).
 *
 * 2) prendre le numéro de la ligne (position) qui doit être rendu et extraire la ville
 * correspondante du tableau.
 *
 * 3) trouver chaque composant graphique de notre point de vue et définir des informations.
 */
public class VolListAdapter extends ArrayAdapter {

    private int resource;
    private LayoutInflater inflater;
    private Context context;

    public VolListAdapter ( Context ctx, int resourceId, List objects) {
        super( ctx, resourceId, objects );
        resource = resourceId;
        inflater = LayoutInflater.from( ctx );
        context=ctx;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent ) {
        /* Creation d'une nouvelle vue dans le layout affichant les données */
        convertView =  (TableRow) inflater.inflate( resource, null ); //

        if( convertView == null ){
            //We must create a View:
            convertView =  (TableRow) inflater.inflate( resource, parent, false);
        }
        //convertView =  inflater.inflate( resource, null ); //(RelativeLayout)

        /* Exctraction des objets de type Vol pour affichage */
        Vol newvol = (Vol)getItem( position );

        /* Definir les textes des Textview sur le layout */
        TextView txtDep = (TextView) convertView.findViewById(R.id.txtDep);
        txtDep.setText(newvol.getDepart());
        TextView txtArr = (TextView) convertView.findViewById(R.id.txtArr);
        txtArr.setText(newvol.getArrivee());
        TextView txtCod = (TextView) convertView.findViewById(R.id.txtCod);
        txtCod.setText(newvol.getDepart());
        TextView txtPri = (TextView) convertView.findViewById(R.id.txtPri);
        txtPri.setText(newvol.getArrivee());


        /* // Definir l'image de la compagnie pour ce vol
        ImageView imgLogo = (ImageView) convertView.findViewById(R.id.volLogo);
        String uri = "drawable/" + newvol.getLogo();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable image = context.getResources().getDrawable(imageResource);
        imgLogo.setImageDrawable(image);
        */

        return convertView;
    }

}
