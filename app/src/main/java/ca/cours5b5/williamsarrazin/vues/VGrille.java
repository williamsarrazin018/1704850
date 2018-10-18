package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.williamsarrazin.controleurs.Action;
import ca.cours5b5.williamsarrazin.controleurs.ControleurAction;
import ca.cours5b5.williamsarrazin.global.GCommande;
import ca.cours5b5.williamsarrazin.global.GCouleur;
import ca.cours5b5.williamsarrazin.modeles.MGrille;

public class VGrille extends GridLayout {

    //Pour stocker les cases de la grille
    private VCase[][] lesCases;

    //Action permettant de mettre un jeton dans la case
    private Action actionJouerCoup;

    public VGrille(Context context) {
        super(context);
    }

    public VGrille(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VGrille(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

    }

    //Contiendra les entetes pour chacune des colonnes + leur listener
    private List<VEntete> entetes = new ArrayList<>();


    @Override
    protected void onFinishInflate(){
        Log.d("atelier07", VGrille.class.getSimpleName() + "::Grid:Onfinishinflate");
        super.onFinishInflate();

    }



    void creerGrille(int hauteur, int largeur) {

        //Etapes pour initialiser la grille

        initialiserTableauDeCases(hauteur, largeur);

        ajouterEnTetes( largeur);

        ajouterCases(hauteur, largeur);

        //Demander l'action pour pouvoir jouer un tour
        demanderActionEntete();

    }


    private void ajouterEnTetes(int largeur){

        //Ajouter une entete et son listener pour chaque colonne de la grille

        for (int i = 0; i < largeur; i++) {

            VEntete enteteColonne = new VEntete(this.getContext(), i);

            //Ajouter dans la vue avec les bons parametres de layout
            addView(enteteColonne, getMisenEnPageEntete(i ));

            entetes.add(enteteColonne);

            installerListenerEntete(enteteColonne, i);
        }

    }

    private LayoutParams getMiseEnPageCase(int colonne, int rangee){

        LayoutParams params = new LayoutParams();

        Spec specRangee = GridLayout.spec(rangee + 1, 1.0f);
        Spec specColonne = GridLayout.spec(colonne, 1.0f);

        params = new LayoutParams(specRangee, specColonne);
        //Rendre l'affichage dynamique
        params.width = 0;
        params.height = 0;
        params.setGravity(Gravity.FILL);

        return params;
    }

    //TODO refactor
    private void ajouterCases(int hauteur, int largeur){

        //rangee
        for (int i = 0; i < largeur; i++) {

            //Colonne
            for (int j = 0; j < hauteur; j++) {

                VCase nouvelleCase = new VCase(this.getContext(), j, i);

                //Ajouter la case avec les bon parametres de layout
                addView(nouvelleCase, getMiseEnPageCase( i, (hauteur - (j + 1))));

                //Ajouter aussi dans le tableau de cases
                lesCases[j][i] = nouvelleCase;

            }

        }

    }

    private LayoutParams getMisenEnPageEntete(int colonne){

        LayoutParams params = new LayoutParams();

        //3 fois plus gros qu'une case
        Spec specRangee = GridLayout.spec(0, 3f);
        Spec specColonne = GridLayout.spec(colonne, 3f);

        params = new LayoutParams(specRangee, specColonne);

        //Pour rendre l'affichage dynamique
        params.width = 0;
        params.height = 0;
        params.setGravity(Gravity.FILL);

        return params;

    }


    private void demanderActionEntete() {

        //On demande l'action au modele pour jouer un jeton
        actionJouerCoup = ControleurAction.demanderAction(GCommande.JOUEUR_COUP_ICI);

    }

    private void initialiserTableauDeCases(int hauteur, int largeur) {
        lesCases = new VCase[hauteur][largeur];
    }


    private void installerListenerEntete(VEntete entete, final int colonne){

        entete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                //Ajouter arguments a l'action et faire la demande d'execution

                actionJouerCoup.setArguments(colonne);

                //Sera executer des que le modele sera cree
                actionJouerCoup.executerDesQuePossible();

            }
        });

    }

    void afficherJetons(MGrille grille) {

        //Recuperer le bon jeton et l'afficher a la bonne rangee, colonne

        //colonnes
        for (int i = 0; i < grille.getColonnes().size(); i++) {

            //rangees
            for (int y = 0; y < grille.getColonnes().get(i).getJetons().size(); y++) {

                //trouver le bon jeton
                GCouleur jetonCourant = grille.getColonnes().get(i).getJetons().get(y);

                afficherJeton(i, y, jetonCourant);

            }

        }

    }

    private void afficherJeton(int colonne, int rangee, GCouleur jeton) {

        //Changer la case de couleur pour montrer presence d'un jeton au bon endroit
        VCase casejeton = lesCases[rangee][colonne];

        casejeton.afficherJeton(jeton);

    }

}
