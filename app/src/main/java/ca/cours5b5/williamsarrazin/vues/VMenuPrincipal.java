package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.controleurs.Action;
import ca.cours5b5.williamsarrazin.controleurs.ControleurAction;
import ca.cours5b5.williamsarrazin.global.GCommande;


public class VMenuPrincipal extends Vue {

    private Button boutonParametres;
    private Action actionParametres;

    private Button boutonPartie;
    private Action actionPartie;

    private Button boutonPartieReseau;
    private Action actionPartieReseau;

    private Button boutonConnexion;
    private Action actionConnexion;

    public VMenuPrincipal(Context context) {
        super(context);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        recupererControles();

        demanderActions();

        installerListeners();

    }


    private void recupererControles() {

        //Récupérer les boutons du template

        boutonParametres = findViewById(R.id.bouton_parametres);

        boutonPartie = findViewById(R.id.bouton_partie);

        boutonConnexion = findViewById(R.id.btnConnexion);

        boutonPartieReseau = findViewById(R.id.bouton_jouer_en_ligne);

    }

    private void demanderActions() {

        //Demander les actions du menu

        actionParametres = ControleurAction.demanderAction(GCommande.OUVRIR_MENU_PARAMETRES);

        actionPartie = ControleurAction.demanderAction(GCommande.DEMARRER_PARTIE);

        actionConnexion =  ControleurAction.demanderAction(GCommande.CONNEXION);

        actionPartieReseau = ControleurAction.demanderAction(GCommande.JOINDRE_OU_CREER_PARTIE_RESEAU);

    }


    private void installerListeners() {

        installerListenerParametres();

        installerListenerPartie();

        installerListenerConnexion();

        installerListenerPartieReseau();

    }

    private void installerListenerConnexion() {

        boutonConnexion.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionConnexion.executerDesQuePossible();
            }
        });

    }

    private void installerListenerPartieReseau() {

        boutonPartieReseau.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                actionPartieReseau.executerDesQuePossible();
            }
        });

    }

    private void installerListenerPartie() {

        boutonPartie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionPartie.executerDesQuePossible();
            }
        });

    }

    private void installerListenerParametres() {

        boutonParametres.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                actionParametres.executerDesQuePossible();
            }
        });

    }

}
