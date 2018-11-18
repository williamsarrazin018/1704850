package ca.cours5b5.williamsarrazin.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.AuthUI;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.controleurs.ControleurAction;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.williamsarrazin.global.GCommande;
import ca.cours5b5.williamsarrazin.global.GConstantes;
import ca.cours5b5.williamsarrazin.modeles.MPartieReseau;

public class AMenuPrincipal extends Activite implements Fournisseur {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        fournirActions();

    }

    private void fournirActions() {

        fournirActionOuvrirMenuParametres();

        fournirActionDemarrerPartie();

        fournirActionConnexion();

        fournirActionJoindreOuCreerPartieReseaau();
    }

    private void fournirActionJoindreOuCreerPartieReseaau() {

        Log.d("Atelier13", "Action DemarrerPartieReseau: ");
        ControleurAction.fournirAction(this,
                GCommande.JOINDRE_OU_CREER_PARTIE_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionPartieReseau();

                    }
                });

    }

    private void fournirActionConnexion() {

        ControleurAction.fournirAction(this,
                GCommande.CONNEXION,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        connexion();

                    }
                });
    }

    private void fournirActionOuvrirMenuParametres() {

        ControleurAction.fournirAction(this,
                GCommande.OUVRIR_MENU_PARAMETRES,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionParametres();

                    }
                });
    }

    private void fournirActionDemarrerPartie() {

        ControleurAction.fournirAction(this,
                GCommande.DEMARRER_PARTIE,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        transitionPartie();

                    }
                });
    }

    private void transitionPartieReseau() {

        Intent intentionPartieReseau = new Intent(this, APartieReseau.class);

        intentionPartieReseau.putExtra(MPartieReseau.class.getSimpleName(), GConstantes.FIXME_JSON_PARTIE_RESEAU);

        startActivity(intentionPartieReseau);

    }

    private void transitionParametres(){

        Intent intentionParametres = new Intent(this, AParametres.class);
        startActivity(intentionParametres);

    }

    private void transitionPartie(){

        Intent intentionParametres = new Intent(this, APartie.class);
        startActivity(intentionParametres);

    }

    private void connexion() {


        List<AuthUI.IdpConfig> fournisseurConnexion = new ArrayList<>();

        //Ajout modes de connexions
        fournisseurConnexion.add(new AuthUI.IdpConfig.GoogleBuilder().build());
        fournisseurConnexion.add(new AuthUI.IdpConfig.PhoneBuilder().build());
        fournisseurConnexion.add(new AuthUI.IdpConfig.EmailBuilder().build());

        Intent intentionConnexion = AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(fournisseurConnexion).build();

        //Start l'intention en attente d'une reponse
        startActivityForResult(intentionConnexion, GConstantes.CODE_CONNEXION);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GConstantes.CODE_CONNEXION) {

            if (resultCode == RESULT_OK) {

                // Connexion réussie
                Log.d("Atelier11", "Connexion réussi: ");

            } else {
                // connexion échouée
                Log.d("Atelier11", "Connexion echoue: ");
            }
        }
    }

}
