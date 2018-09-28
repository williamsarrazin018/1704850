package ca.cours5b5.williamsarrazin.controleurs;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.williamsarrazin.modeles.MParametres;
import ca.cours5b5.williamsarrazin.modeles.MPartie;
import ca.cours5b5.williamsarrazin.modeles.Modele;

public class ControleurObservation {

    private static Map<Modele, ListenerObservateur> observations = new HashMap<>();

    private static MPartie partie;

    static {

    }

    public static void observerModele(String nomModele, final ListenerObservateur listenerObservateur) {

        if (nomModele == MParametres.class.getSimpleName()) {

            listenerObservateur.reagirChangementModele(MParametres.instance);

        } else if (nomModele == MPartie.class.getSimpleName()) {

            listenerObservateur.reagirChangementModele(ControleurObservation.partie);

        }

        observations.put(MParametres.instance, listenerObservateur);



    }

}
