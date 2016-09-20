package shell;

/*	GESTIONNAIRES D'EXCEPTIONS ANDROID
 *	Auteur 		: COUPEZ Frédéric
 *  Date 		: 31 AOÛT 2016
 *	Compatible 	: Tout programme Androïd
 *
 * Utilité 		: Permettre de gérer chaque exception de manière plus simple :
 * 		(public static) tryCatchErrorAndroid Error = new tryCatchErrorAndroid();
 * 		try { }(catch (typeexception e) { Error.gestionException(e); }
 *
 * Il est possible de l'améliorer (ajout d'autres exceptions) ou de la modifier (toast/log) selon vos besoins
 */

// LIBRAIRIES
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
import android.net.ParseException;

import java.io.FileNotFoundException;
import java.io.EOFException;
import java.io.IOException;


public class TryCatchError {

    public TryCatchError() {

    }
    private Context mContext;   // Pour permettre d'utiliser le contexte de l'activité appellante


    /* // Diverses Exceptions :
    AclNotFoundException, ActivationException, AlreadyBoundException, ApplicationException, AWTException,
    BackingStoreException, BadAttributeValueExpException, BadBinaryOpValueExpException, BadLocationException,
    BadStringOperationException, BrokenBarrierException, CertificateException, CloneNotSupportedException,
    DataFormatException, DatatypeConfigurationException, DestroyFailedException, ExecutionException, ExpandVetoException,
    FontFormatException, GeneralSecurityException, GSSException, IllegalClassFormatException, InterruptedException,
    IntrospectionException, InvalidApplicationException, InvalidMidiDataException, InvalidPreferencesFormatException,
    InvalidTargetObjectTypeException, IOException, JAXBException, JMException, KeySelectorException, LastOwnerException,
    LineUnavailableException, MarshalException, MidiUnavailableException, MimeTypeParseException, MimeTypeParseException,
    NamingException, NoninvertibleTransformException, NotBoundException, NotOwnerException, ParseException,
    ParserConfigurationException, PrinterException, PrintException, PrivilegedActionException, PropertyVetoException,
    ReflectiveOperationException, RefreshFailedException, RemarshalException, RuntimeException, SAXException, ScriptException,
    ServerNotActiveException, SOAPException, SQLException, TimeoutException, TooManyListenersException, TransformerException,
    TransformException, UnmodifiableClassException, UnsupportedAudioFileException, UnsupportedCallbackException,
    UnsupportedFlavorException, UnsupportedLookAndFeelException, URIReferenceException, URISyntaxException, UserException,
    XAException, XMLParseException, XMLSignatureException, XMLStreamException, XPathException
    */
/*
    public void gestionException(AuthenticationException e){
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "AuthenticationException : "+e.toString());
    }	// Exceptions liées à un objet d'authentification étant invalide pour une raison quelconque.
*/
    public void gestionException(ClassNotFoundException e){
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "ClassNotFoundException : "+e.toString());
    }	// getException() : Exception soulevée si une erreur est survenue lors de la tentative de charger une classe
    /*
        public void gestionException(ClientProtocolException e){
            Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            Log.w("TAG", "ClientProtocolException : "+e.toString());
        }	// Signale une erreur dans le protocole HTTP.
    */
    public void gestionException(EOFException e){
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "EOFException  : "+e.toString());
    }	// Signale une fin innatendue de fichier ou de flux.

    public void gestionException(Exception e){
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "Exception : "+e.toString());
    }	// Levée lorsqu'une tentative de récupérer le résultat d'une tâche échoue...

    public void gestionException(FileNotFoundException e){
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "FileNotFoundException  : "+e.toString());
    }	// Echec lors de la tentative d'ouvrerture d'une fichier désigné par un chemin d'accès spécifié.

    public void gestionException(IllegalAccessException e){
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "IllegalAccessException : "+e.toString());
    }	// Lancée lorsqu'une application tente de créer reflexivement une instance (autre qu'un tableau),
    // définir ou obtenir un champ, ou invoquer une méthode ; mais que la méthode en cours d'exécution
    // n'a pas accès à la définition de la classe, au champ spécifié, à la méthode ou au constructeur.

    public void gestionException(IllegalArgumentException e) {
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "IllegalArgumentException : "+e.toString());
    }	// Levée lorsque qu'une méthode a reçue un argument illégal ou inapproprié.

    public void gestionException(IllegalStateException e) {
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "IllegalStateException : "+e.toString());
    }	// L'environnement Java ou l'application Java ne sont pas dans un état ​​approprié pour l'opération demandée.

    public void gestionException(IndexOutOfBoundsException e) {
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "IndexOutOfBoundsException : "+e.toString());
    }	// L'indice est hors de portée (par exemple for(int i = 0;  ; i++))

    public void gestionException(InstantiationException e){
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "InstantiationException : "+e.toString());
    }	// Levée lorsqu'une instanciation echoue (diverses raisons possibles...)
    // Exemples : la classe n'a pas de constructeur, l'objet de classe représente une
    // classe abstraite, une interface, une classe de tableau, un type primitif, null, ...

    public void gestionException(IOException e){
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "IOException : "+e.toString());
    }	// Un flux en entrée/sortie (I/O) a echoué ou a été interrompu

    public void gestionException(ParseException e){
        Toast.makeText(mContext.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        Log.w("TAG", "ParseException : "+e.toString());
    }	// Signale une erreur innatendue lors de l'analyse.
}



/* // Un petit exemple d'utilisation, à compléter avec vos propres erreurs :D

public class MainActivity extends AppCompatActivity {

	// Instanciation de la classe tryCatchErrorAndroid :
	public static tryCatchErrorAndroid Error = new tryCatchErrorAndroid();

	protected void onCreate(Bundle savedInstanceState) {
        try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

			// Appel de la methode qui va bugger :
			methode(arg);

		} catch (Exception e) {

			Log.w("TAG", "onCreate");
			Error.gestionException(e);

		} finally {
			Log.w("TAG", "finally");
			// ...
		}
	}

	public void methode(type arg) throws Exception {
		try {

			String entier = uneMéthodeQuiRenvoitPlusieursTypesDException();

		} catch (Exception e) {
			Log.w("TAG", "methode > 1");

			// Encapsulation des exceptions dans une exception unique
			// Renvoi la valeur à la classe ayant appelé la methode
			throw new Exception("Un problème est survenue", e);

		} catch (IOException | EOFException e) {
		// catch multiple : uniquement à partir de JAVA 7

			Log.w("TAG", "methode > 2");
			Error.gestionException(e);

		}
	}

	public boolean uneMéthodeQuiRenvoitPlusieursTypesDException() {

		Log.w("TAG", "uneMéthodeQuiRenvoitPlusieursTypesDException");
		return true;

	}
}
*/