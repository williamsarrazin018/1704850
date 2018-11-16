package ca.cours5b5.williamsarrazin.activites;

import android.os.Bundle;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;

public class APartieReseau extends Activite implements Fournisseur {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie_reseau);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /*
         * Avec ControleurPartieReseau, détruire la partie sur le serveur
         * Déconnecter ControleurPartieReseau du serveur
         */
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
         * Connecter le ControleurPartieReseau au serveur*/
    }



}
