package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.williamsarrazin.modeles.MPartieOrdinateur;


public class VPartieOrdinateur extends VPartie {


    private VGrille grille;


    public VPartieOrdinateur(Context context) {
        super(context);
    }

    public VPartieOrdinateur(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartieOrdinateur(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected String getNomModele(){
        return MPartieOrdinateur.class.getSimpleName();
    }

}
