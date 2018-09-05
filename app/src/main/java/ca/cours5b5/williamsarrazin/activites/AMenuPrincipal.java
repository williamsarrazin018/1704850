package ca.cours5b5.williamsarrazin.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.williamsarrazin.R;

public class AMenuPrincipal extends Activite {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);

        Button boutonParametres = this.findViewById(R.id.buttonParametres);
        final Intent ouvrirParametres = new Intent(this, AParametres.class);

        boutonParametres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ouvrirParametres);
            }
        });

    }

}
