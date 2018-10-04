package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.modeles.MParametres;

public class VGrille extends GridLayout {

    private GridLayout layout = this.findViewById(R.id.grilleLayout);

    private int largeur;

    private int hauteur;

    public VGrille(Context context) {
        super(context);
    }

    public VGrille(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int nombreRangees;

    private class Colonne extends ArrayList<VCase> {

        private int colonne;

        public Colonne( int colonne) {

            this.colonne = colonne;

        }

    }

    private List<VEntete> entetes;

    private List<Colonne> colonnesDeCases;

    @Override
    protected void onFinishInflate(){

        super.onFinishInflate();
    }

    private void initialiser(){
        int largeur = MParametres.instance.getParametresPartie().largeur;
        int hauteur = MParametres.instance.getParametresPartie().hauteur;

    }

    void creerGrille(int hauteur, int largeur) {

        initialiserColonnes(largeur);



    }

    private void initialiserColonnes(int largeur){
        colonnesDeCases = new ArrayList<>();
        for (int i = 0; i < largeur; i++) {

            colonnesDeCases.add(new Colonne(i));

        }


    }

    private void ajouterEnTetes(int largeur){

    }

    private LayoutParams getMiseEnPageCase(int colonne){

        return null;
    }

    private void ajouterCases(int hauteur, int largeur){

    }

    private LayoutParams getMiseEnPage(int rangee, int colonne){

        LayoutParams params = new LayoutParams();

        params.width = 0;
        params.height = 0;
        params.setGravity(Gravity.FILL);


        return params;
    }
}
