package ca.cours5b5.williamsarrazin.controleurs;


import android.util.Log;

import ca.cours5b5.williamsarrazin.global.GCommande;
import ca.cours5b5.williamsarrazin.global.GCouleur;

public final class ControleurPartie {

    private ControleurPartie(){}

    private static final ControleurPartie instance = new ControleurPartie();
    public static ControleurPartie getInstance(){return instance;}

    public void gagnerPartie(GCouleur couleurGagnante){

        Action actionTerminerPartie = ControleurAction.demanderAction(GCommande.TERMINER_PARTIE);

        Action actionAfficherMessage = ControleurAction.demanderAction(GCommande.AFFICHER_MESSAGE_GAGNANT);


        actionAfficherMessage.setArguments(couleurGagnante,
                actionTerminerPartie);

        actionAfficherMessage.executerDesQuePossible();

    }


}
