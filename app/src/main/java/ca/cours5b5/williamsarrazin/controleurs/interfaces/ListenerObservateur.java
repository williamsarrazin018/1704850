package ca.cours5b5.williamsarrazin.controleurs.interfaces;

import ca.cours5b5.williamsarrazin.modeles.Modele;

public abstract class ListenerObservateur {

    public void reagirNouveauModele(Modele modele) {
        reagirNouveauModele(modele);
    }

    public abstract void reagirChangementModele(Modele modele);

}
