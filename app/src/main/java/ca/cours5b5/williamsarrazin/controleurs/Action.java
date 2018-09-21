package ca.cours5b5.williamsarrazin.controleurs;

import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerFournisseur;

public class Action implements Cloneable {

    Fournisseur fournisseur;

    ListenerFournisseur listenerFournisseur;

    Object[] args;

    public void setArguments(Object... args){

        this.args = args;


    }

    public void executerDesQuePossible(){

        ControleurAction.executerDesQuePossible(this);

    }

    public Action cloner() throws CloneNotSupportedException {

        Action clone = (Action)this.clone();

        if (args != null) {

            clone.args = args.clone();

        }

        return clone;

    }
}
