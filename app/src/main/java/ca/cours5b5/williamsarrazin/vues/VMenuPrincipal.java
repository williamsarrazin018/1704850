package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.controleurs.Action;
import ca.cours5b5.williamsarrazin.controleurs.ControleurAction;
import ca.cours5b5.williamsarrazin.global.GCommande;


public class VMenuPrincipal extends Vue {

    //code debut action dans on click

    static{
        Log.d("Atelier04", VMenuPrincipal.class.getSimpleName() + "::static");
    }

    public VMenuPrincipal(Context context) {


        super(context);


        Button bouton = this.findViewById(R.id.button);
        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Action action = ControleurAction.demanderAction(GCommande.OUVRIR_MENU_PARAMETRES);
                action.executerDesQuePossible();
            }
        });

    }

    public VMenuPrincipal(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VMenuPrincipal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


}
