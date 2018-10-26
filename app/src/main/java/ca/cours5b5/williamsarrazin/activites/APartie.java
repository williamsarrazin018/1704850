package ca.cours5b5.williamsarrazin.activites;


import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.controleurs.ControleurModeles;
import ca.cours5b5.williamsarrazin.donnees.SauvegardeTemporaire;
import ca.cours5b5.williamsarrazin.modeles.MParametres;
import ca.cours5b5.williamsarrazin.modeles.MPartie;

public class APartie extends Activite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_partie);
        Log.d("exam", APartie.class.getSimpleName() + "OnCreate");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("exam", APartie.class.getSimpleName() + "OnPause");
        ControleurModeles.sauvegarderModele(MPartie.class.getSimpleName());

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ControleurModeles.sauvegarderModeleDansCetteSource(MPartie.class.getSimpleName(),
                new SauvegardeTemporaire(outState));

    }
}