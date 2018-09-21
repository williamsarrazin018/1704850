package ca.cours5b5.williamsarrazin.controleurs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.williamsarrazin.global.GCommande;
import ca.cours5b5.williamsarrazin.modeles.Modele;

public class ControleurAction {

    private static Map<GCommande, Action> actions = new HashMap<>();
    private static List<Action> fileAttenteExecution = new ArrayList<>();

    static {

        //Remplir la map d'actions vides


        for ( GCommande commande : GCommande.values()  ) {

            actions.put(commande, new Action());

        }

    }

    public static Action demanderAction(GCommande commande){

        Action action = new Action();

        for(Map.Entry<GCommande, Action> entry : actions.entrySet()) {

            if(entry.getKey() == commande) {
                action = entry.getValue();
            }

        }
        return action;


    }

    public static void fournirAction(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur) {


        enregistrerFournisseur(fournisseur, commande, listenerFournisseur);
        executerActionsExecutables();

    }

    static void executerDesQuePossible(Action action){

        fileAttenteExecution.add(action);
        executerActionsExecutables();

    }

    private static void executerActionsExecutables() {

        ListIterator<Action> iterateur = fileAttenteExecution.listIterator();

        while (iterateur.hasNext()) {

            Action action = iterateur.next();

            if(siActionExecutable(action)){

                fileAttenteExecution.remove(action);

                executerMaintenant(action);

                lancerObservationSiApplicable(action);

            }

        }

    }

    static boolean siActionExecutable(Action action){



        return action.listenerFournisseur != null;

    }


    private static void lancerObservationSiApplicable(Action action){

        if(action.fournisseur instanceof Modele){

            ControleurObservation.lancerObservation((Modele) action.fournisseur);

        }

    }

    private static synchronized void executerMaintenant(Action action){

        action.listenerFournisseur.executer();

    }

    private static void enregistrerFournisseur(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur){

        Action action = actions.get(commande);
        action.fournisseur = fournisseur;
        action.listenerFournisseur = listenerFournisseur;


    }

    private static void ajouterActionEnFileDAttente(Action action) throws CloneNotSupportedException{

        Action clone = action.cloner();

        fileAttenteExecution.add(clone);

    }

}
