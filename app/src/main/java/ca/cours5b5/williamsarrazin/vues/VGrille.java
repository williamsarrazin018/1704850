package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.williamsarrazin.R;

public class VGrille extends GridLayout {

    public VGrille(Context context) {
        super(context);
    }

    public VGrille(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    GridLayout layout = this.findViewById(R.layout.);

    private int nombreRangees;

    private class colonne extends ArrayList<VCase> {}

    private List<VEntete> entetes;

    @Override
    protected void onFinishInflate(){

        super.onFinishInflate();
    }

    private void initialiser(){

    }

    void creerGrille(int hauteur, int largeur) {

    }

    private void initialiserColonnes(int largeur){

    }

    private void ajouterEnTetes(int largeur){

    }

    private LayoutParams getMiseEnPageCase(int colonne){
        return null;
    }

    private void ajouterCases(int hauteur, int largeur){

    }

    private LayoutParams getMiseEnPage(int rangee, int colonne){
        return null;
    }
}
