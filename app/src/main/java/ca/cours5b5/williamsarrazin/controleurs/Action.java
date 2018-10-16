package ca.cours5b5.williamsarrazin.controleurs;

import android.util.Log;

import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.williamsarrazin.modeles.MPartie;

public class Action implements Cloneable {

    Fournisseur fournisseur;

    ListenerFournisseur listenerFournisseur;

    Object[] args;



    public void executerDesQuePossible(){
        Log.d("atelier07", "execdespossible");
        ControleurAction.executerDesQuePossible(this);

    }

    public Action cloner() {

        Action clone = new Action();

        //On clone les arguments seulement si presents
        if (args != null) {

            clone.setArguments(args.clone());

        }

        clone.setListenerFournisseur(listenerFournisseur);

        clone.setFournisseur(fournisseur);

        return clone;

    }

    // setters / getters

    public void setArguments(Object... args){

        this.args = args;

    }

    public void setFournisseur(Fournisseur fournisseur) {

        this.fournisseur = fournisseur;

    }

    public void setListenerFournisseur(ListenerFournisseur listenerFournisseur) {

        this.listenerFournisseur = listenerFournisseur;

    }

    public Fournisseur getFournisseur() {

        return fournisseur;

    }

    public ListenerFournisseur getListenerFournisseur() {

        return listenerFournisseur;

    }
}
