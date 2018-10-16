package ca.cours5b5.williamsarrazin.controleurs;

import java.util.HashMap;
import java.util.Map;

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
        //initialiser le map d'association des observations
        observations = new HashMap<>();
    }

    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur) {

        if (nomModele.equals(MPartie.class.getSimpleName())) {

            //On cree la partie avec les params
            partie = new MPartie( MParametresPartie.aPartirMParametres(MParametres.instance) );

            observations.put(ControleurObservation.partie, listenerObservateur);

            //On lance obeservation de la nouvelle partie
            lancerObservationNouveauModele(ControleurObservation.partie);

        } else if (nomModele.equals(MParametres.class.getSimpleName())) {

            //On ajoute l'observation dans le map et on lance l'observation de l'instance
            observations.put(MParametres.instance, listenerObservateur);
            lancerObservationNouveauModele(MParametres.instance);

        } else {

            //Si aucun des deux, on lance l'exception
            throw  new ErreurObservation("Le modele est inconnu : " + nomModele);

        }


    }

    public static void lancerObservation(Modele modele){

        //Recuperer le listener approprie d'apres le modele dans le map
        ListenerObservateur listener = observations.get(modele);

        if (listener != null) {

            listener.reagirChangementAuModele(modele);

        }

    }

    public static void lancerObservationNouveauModele(Modele modele){

        //Recuperer le listener approprie d'apres le modele dans le map
        ListenerObservateur listener = observations.get(modele);

        if (listener != null) {

            listener.reagirNouveauModele(modele);

        }

    }

}
