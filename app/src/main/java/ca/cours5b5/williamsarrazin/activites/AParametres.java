package ca.cours5b5.williamsarrazin.activites;

import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.modeles.MParametres;
import ca.cours5b5.williamsarrazin.serialisation.Jsonification;


public class AParametres extends Activite {



    static{
        Log.d("Atelier04",  AParametres.class + "::static");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parametres);

        if (savedInstanceState != null) {

            restaurerParametres(savedInstanceState);

        }



        Log.d("Bonjour",  this.getResources().getString(R.string.message_bienvenue) + this.getResources().getString(R.string.orientation));



    }


    private void restaurerParametres( Bundle savedInstanceState){

        String json = savedInstanceState.getString("cleModele");

        Map<String, Object> objetJson = Jsonification.enObjetJson(json);

        MParametres.instance.aPartirObjetJson(objetJson);

        Log.d("MParametres",  "AParametres::restaurerParametres" + json);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        sauvegarderParametres(outState);

    }


    private void sauvegarderParametres(Bundle outState) {

        Map<String, Object> objetJson = MParametres.instance.enObjetJson();

        String json = Jsonification.enChaine(objetJson);

        outState.putString("cleModele", json);

        Log.d("MParametres",  "AParametres::sauvegarderParametres" + json);


    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.d("Atelier04",  this.getClass().getSimpleName() + "::onDestroy");
    }



}
