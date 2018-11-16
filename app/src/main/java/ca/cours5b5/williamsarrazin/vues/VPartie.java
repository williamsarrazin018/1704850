package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.controleurs.ControleurObservation;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.williamsarrazin.exceptions.ErreurObservation;
import ca.cours5b5.williamsarrazin.modeles.MParametresPartie;
import ca.cours5b5.williamsarrazin.modeles.MPartie;
import ca.cours5b5.williamsarrazin.modeles.Modele;


public class VPartie extends Vue {

    private VGrille grille;

    public VPartie(Context context) {
        super(context);
    }

    public VPartie(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VPartie(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d("exam", VPartie.class.getSimpleName() + "onFinishinflate");
        initialiser();

        observerPartie();

    }

    private void initialiser() {

        grille = findViewById(R.id.grille);

    }

    private void observerPartie() {

        ControleurObservation.observerModele(MPartie.class.getSimpleName(),
                new ListenerObservateur() {
                    @Override
                    public void reagirNouveauModele(Modele modele) {

                        MPartie partie = getPartie(modele);

                        preparerAffichage(partie);

                        miseAJourGrille(partie);

                    }

                    @Override
                    public void reagirChangementAuModele(Modele modele) {

                        MPartie partie = getPartie(modele);

                        miseAJourGrille(partie);

                    }
                });
    }


    private void preparerAffichage(MPartie partie) {

        MParametresPartie parametresPartie = partie.getParametres();

        grille.creerGrille(parametresPartie.getHauteur(), parametresPartie.getLargeur());

    }

    private MPartie getPartie(Modele modele){

        try{

            return (MPartie) modele;

        }catch(ClassCastException e){

            throw new ErreurObservation(e);

        }

    }

    private void miseAJourGrille(MPartie partie){

        grille.afficherJetons(partie.getGrille());

    }

    public String getnomModele(){
        return MPartie.class.getSimpleName();
    }

}
