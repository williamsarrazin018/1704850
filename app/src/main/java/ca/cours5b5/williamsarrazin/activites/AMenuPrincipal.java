package ca.cours5b5.williamsarrazin.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import ca.cours5b5.williamsarrazin.R;

public class AMenuPrincipal extends Activite {

    static{
        Log.d("Atelier04", AMenuPrincipal.class.getSimpleName() + "::static");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void transitionParametres(){
        Intent intentionParametres = new Intent(this, AParametres.class);
        startActivity(intentionParametres);
    }

}
