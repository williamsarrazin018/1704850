package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;

import ca.cours5b5.williamsarrazin.controleurs.ControleurObservation;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerObservateur;
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
    }

    private void initialiser(){

    }

    private void observerPartie(){

        ControleurObservation.observerModele(MPartie.class.getSimpleName(),
                new ListenerObservateur() {
                    @Override
                    public void reagirChangementModele(Modele modele) {
                        initialiserGrille(getPartie(modele));
                    }
                });

    }

    private MPartie getPartie(Modele modele){
        return null;
    }

    private void initialiserGrille(MPartie partie){

    }


}
