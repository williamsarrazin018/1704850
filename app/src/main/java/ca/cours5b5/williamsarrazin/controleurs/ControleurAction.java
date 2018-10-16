package ca.cours5b5.williamsarrazin.controleurs;

import android.util.Log;

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

        //On trouve l'action liees a la commande desiree

        Action action = new Action();

        action = actions.get(commande);

        return action;


    }

    public static void fournirAction(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur) {

        enregistrerFournisseur(fournisseur, commande, listenerFournisseur);
        executerActionsExecutables();

    }

    static void executerDesQuePossible(Action action){
        Log.d("atelier07", ControleurAction.class.getSimpleName() + "::executerDesQuePossible");

        //Mettre dans la file d'execution
        ajouterActionEnFileDAttente(action);
        executerActionsExecutables();

    }

    private static void executerActionsExecutables() {

        Log.d("atelier07", ControleurAction.class.getSimpleName() + "::executerActionsExecutables");

        ListIterator<Action> iterateur = fileAttenteExecution.listIterator();

        while (iterateur.hasNext()) {
            Action action = iterateur.next();

            //Execute l'action et lance l'observation
            if(siActionExecutable(action)){

                fileAttenteExecution.remove(action);

                executerMaintenant(action);

                lancerObservationSiApplicable(action);

            }

        }

    }

    static boolean siActionExecutable(Action action){
        //Executable quand le fournisseur de l'action n'est pas null
        return action.getListenerFournisseur() != null;

    }


    private static void lancerObservationSiApplicable(Action action){

        //Seulement si le fournissweur est un modele
        if(action.fournisseur instanceof Modele){

           ControleurObservation.lancerObservation((Modele) action.getFournisseur());

        }

    }

    private static synchronized void executerMaintenant(Action action){

        Log.d("atelier07", ControleurAction.class.getSimpleName() + "::executerMaintenant");

        //Executer l'action avec ses arguments respectifs

        action.getListenerFournisseur().executer(action.args);

    }

    private static void enregistrerFournisseur(Fournisseur fournisseur, GCommande commande, ListenerFournisseur listenerFournisseur){

        //enregistrer les informations dans l'action
        Action action = actions.get(commande);
        action.fournisseur = fournisseur;
        action.listenerFournisseur = listenerFournisseur;

    }

    private static void ajouterActionEnFileDAttente(Action action){
        //Mettre un clone de l'action dans la file d'attente des actions
        Action clone = action.cloner();

        fileAttenteExecution.add(clone);

    }

}
