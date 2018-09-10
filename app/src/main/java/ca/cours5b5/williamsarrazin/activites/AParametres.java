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

        Log.d("Bonjour",  this.getResources().getString(R.string.message_bienvenue) + this.getResources().getString(R.string.orientation));



    }


    private void restaurerParametres( Bundle savedInstanceState){

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        Map<String, Object> objetJson = MParametres.instance.enObjetJson();

        String json = Jsonification.enChaine(objetJson);

        //outState.putInt(MParametres.instance.__hauteur, json);

    }

    private void sauvegarderParametres(Bundle outState) {



    }

}
