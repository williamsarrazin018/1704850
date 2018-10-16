package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.controleurs.ControleurObservation;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.williamsarrazin.global.GCouleur;
import ca.cours5b5.williamsarrazin.modeles.MGrille;
import ca.cours5b5.williamsarrazin.modeles.MPartie;
import ca.cours5b5.williamsarrazin.modeles.Modele;

public class VPartie extends Vue{

    private VGrille grille;

    public VPartie(Context context){
        super(context);
    }

    public VPartie(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public VPartie(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate(){

        super.onFinishInflate();

        //Initialiser la partie et lancer l'observation pour reagir au changements dans la vue
        initialiser();

        observerPartie();
    }

    private void initialiser(){

        //Aller chercher le controle (gridlayout)
        grille = this.findViewById(R.id.grilleLayout);

    }

    private void observerPartie(){

        //Detecter des changements au modele
        ControleurObservation.observerModele(MPartie.class.getSimpleName(),
                new ListenerObservateur() {
                    @Override
                    public void reagirNouveauModele(Modele modele) {

                        //Reagir au nouveau modele
                        super.reagirNouveauModele(modele);

                        MPartie partie = getPartie(modele);

                        //MaJ grille avec les donnees de la partie (jetons)
                        initialiserGrille(getPartie(partie));

                        miseAJourGrille(partie);

                    }

                    @Override
                    public void reagirChangementAuModele(Modele modele) {

                        MPartie partie = getPartie(modele);

                        //MaJ grille avec les donnees de la partie ()jetons
                        miseAJourGrille(partie);

                    }
                });

    }

    private void miseAJourGrille(MPartie partie) {

        //Afficher les bons jetons actuels
        grille.afficherJetons(partie.getGrille());

    }

    private MPartie getPartie(Modele modele){

        return (MPartie) modele;

    }

    private void initialiserGrille(MPartie partie) {

        int hauteur = partie.getParametres().getHauteur();
        int largeur = partie.getParametres().getLargeur();

        grille.creerGrille(hauteur, largeur);

    }




}
