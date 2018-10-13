package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.modeles.MParametres;

public class VGrille extends GridLayout {

    private GridLayout layout;

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

    public class Colonne extends ArrayList<VCase> {

        private VEntete entete;
        private int id;


        public Colonne( int id) {

            this.id = id;


        }

        public VEntete getEntete() {
            return entete;
        }

        public void setEntete(Context contexte, int entete) {
            this.entete = new VEntete(contexte, entete);
        }

        public int getId() {
            return id;
        }


    }

    private List<VEntete> entetes;

    private List<Colonne> colonnesDeCases = new ArrayList<>();

    @Override
    protected void onFinishInflate(){
        Log.d("grid", "Grid:Onfinishinflate");
        super.onFinishInflate();
        initialiser();
    }

    public void initialiser(){
        layout = this.findViewById(R.id.grilleLayout);
        int largeur = MParametres.instance.getLargeur();
        int hauteur = MParametres.instance.getHauteur();

        layout.setColumnCount(largeur);
        layout.setRowCount(hauteur + 1);

        creerGrille(hauteur, largeur);

        for (int i = 0; i < largeur; i++) {
            layout.addView(colonnesDeCases.get(i).getEntete(), getMisenEnPageEntete(i));
        }

        int col = 0;
        int rangee = 1;

        for (int i = 0; i < largeur; i++) {
            rangee = 1;

            for (int j = hauteur - 1; j > -1; j--) {
                layout.addView(colonnesDeCases.get(i).get(j), getMiseEnPageCase(col, rangee));
                rangee++;
            }
            col++;

        }


    }

    void creerGrille(int hauteur, int largeur) {

        initialiserColonnes(largeur);
        ajouterEnTetes( largeur);
        ajouterCases(hauteur, largeur);



    }

    private void initialiserColonnes(int largeur){
        colonnesDeCases = new ArrayList<>();
        for (int i = 0; i < largeur; i++) {

            colonnesDeCases.add(new Colonne(i));

        }




    }

    private void ajouterEnTetes(int largeur){

       Iterator<Colonne> iterateur = colonnesDeCases.iterator();

       int cpt = 0;

       while (iterateur.hasNext()) {

           Colonne col = iterateur.next();

         col.setEntete(this.getContext(), col.getId());

       }

    }

    private LayoutParams getMiseEnPageCase(int colonne, int rangee){


        LayoutParams params = new LayoutParams();
        Spec specRangee = GridLayout.spec(rangee, 1f);
        Spec specColonne = GridLayout.spec(colonne, 1f);

        params = new LayoutParams(specRangee, specColonne);
        params.width = 0;
        params.height = 0;
        params.setGravity(Gravity.FILL);


        return params;
    }

    private void ajouterCases(int hauteur, int largeur){

        Iterator<Colonne> iterateur = colonnesDeCases.iterator();

        int cpt = 0;

        while (iterateur.hasNext()) {

            Colonne col = iterateur.next();

            for (int i = 0; i < hauteur; i++) {

                col.add(new VCase(this.getContext(), i, col.getId()));

            }

        }


    }

    private LayoutParams getMisenEnPageEntete(int colonne){

        LayoutParams params = new LayoutParams();
        Spec specRangee = GridLayout.spec(0, 3f);
        Spec specColonne = GridLayout.spec(colonne, 3f);

        params = new LayoutParams(specRangee, specColonne);
        params.width = 0;
        params.height = 0;
        params.setGravity(Gravity.FILL);


        return params;
    }
    public GridLayout getLayout(){
        return layout;
    }
}
