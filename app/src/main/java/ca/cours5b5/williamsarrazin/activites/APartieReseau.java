package ca.cours5b5.williamsarrazin.activites;

import android.os.Bundle;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.controleurs.ControleurPartieReseau;
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
        //Detruire la [artie du serveur avec le controller et le deconnecter du serveur aussi

        ControleurPartieReseau.getInstance().detruireSauvegardeServeur();

        ControleurPartieReseau.getInstance().deconnecterDuServeur();
    }

    //Connecter le controller au serveur
    @Override
    protected void onResume() {
        super.onResume();

        ControleurPartieReseau.getInstance().connecterAuServeur();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

}
