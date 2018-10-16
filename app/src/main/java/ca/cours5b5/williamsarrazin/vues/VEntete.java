package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerObservateur;

public class VEntete extends AppCompatButton {
    public VEntete(Context context) {
        super(context);
    }

    public VEntete(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VEntete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int colonne;

    public VEntete(Context context, int colonne){
        super(context);

        String fleche = "\u2193";

        this.setText(colonne + "\n" + fleche + "\n" + fleche);

    }

    public void setOnClickListener(ListenerObservateur listener) {
        this.setOnClickListener(listener);
    }
}
