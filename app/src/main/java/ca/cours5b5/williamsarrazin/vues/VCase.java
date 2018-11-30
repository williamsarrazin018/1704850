package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.modeles.MJeton;


public class VCase extends AppCompatButton {


    private int colonne;
    private int rangee;
    private boolean animation;

    public VCase(Context context) {
        super(context);
        initialiser();
    }

    public VCase(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialiser();
    }

    public VCase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialiser();
    }


    public VCase(Context context, int rangee, int colonne) {
        super(context);

        // Atelier08: afficher les indices
        setText(""+rangee+","+colonne);

        this.colonne = colonne;
        this.rangee = rangee;

        initialiser();
    }

    private void initialiser() {
        
        setEnabled(false);

        setBackgroundColor(getResources().getColor(R.color.VIDE, null));

    }

    public void afficherJeton(MJeton jeton) {

        afficherCouleurJeton(jeton);
        if(!animation){
            animationJeton();
        }

    }


    private void afficherCouleurJeton(MJeton jeton) {
        switch (jeton.getCouleur()){

            case ROUGE:

                setBackgroundColor(getResources().getColor(R.color.ROUGE, null));

                break;

            case JAUNE:

                setBackgroundColor(getResources().getColor(R.color.JAUNE, null));

                break;

        }
    }

    public void animationJeton() {

        int coordonnes[] = new int[2];
        this.getLocationOnScreen(coordonnes);

        //float y = -getResources().getDisplayMetrics().heightPixels + ( getHeight() * rangee);

        float y = -getResources().getDisplayMetrics().heightPixels + coordonnes[1];

        this.setY(y);

        float basEcran = 0;

        //Faire l'animation
        this.animate().translationY(basEcran).setInterpolator(new AccelerateInterpolator()).setDuration(750);

        animation = true;

    }


}
