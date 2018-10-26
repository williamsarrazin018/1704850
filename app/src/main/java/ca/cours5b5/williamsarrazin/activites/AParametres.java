package ca.cours5b5.williamsarrazin.activites;

import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.controleurs.ControleurModeles;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.modeles.MParametres;

public class AParametres extends ca.cours5b5.williamsarrazin.activites.Activite implements Fournisseur{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        Log.d("exam", AParametres.class.getSimpleName() + "onCreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("exam", AParametres.class.getSimpleName() + "onPause");
        ControleurModeles.sauvegarderModele(MParametres.class.getSimpleName());

    }

}
