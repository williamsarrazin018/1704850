package ca.cours5b5.williamsarrazin.controleurs;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.williamsarrazin.exceptions.ErreurObservation;
import ca.cours5b5.williamsarrazin.modeles.MParametres;
import ca.cours5b5.williamsarrazin.modeles.MParametresPartie;
import ca.cours5b5.williamsarrazin.modeles.MPartie;
import ca.cours5b5.williamsarrazin.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListenerObservateur> observations;

    private static MPartie partie;

    static {
        observations = new HashMap<>();
    }

    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur) {

        if (nomModele.equals(MPartie.class.getSimpleName())) {

            //On cree la partie avec les params
            partie = new MPartie( MParametresPartie.aPartirMParametres(MParametres.instance) );

            observations.put(ControleurObservation.partie, listenerObservateur);

            //On lance obesrvation de la nouvelle partie
            lancerObservationNouveauModele(ControleurObservation.partie);

        } else if (nomModele.equals(MParametres.class.getSimpleName())) {

            observations.put(MParametres.instance, listenerObservateur);
            lancerObservationNouveauModele(MParametres.instance);

        } else {

            throw  new ErreurObservation("Le modele est inconnu : " + nomModele);

        }


    }

    public static void lancerObservation(Modele modele){

        //Recuperer le listener approprie
        ListenerObservateur listener = observations.get(modele);

        if (listener != null) {

            listener.reagirChangementModele(modele);

        }

    }

    public static void lancerObservationNouveauModele(Modele modele){

        ListenerObservateur listenerObservateur = observations.get(modele);

        if (listenerObservateur != null) {

            listenerObservateur.reagirNouveauModele(modele);

        }

    }

}
