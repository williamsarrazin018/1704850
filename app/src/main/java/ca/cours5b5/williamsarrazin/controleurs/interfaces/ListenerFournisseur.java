package ca.cours5b5.williamsarrazin.controleurs.interfaces;


public abstract class ListenerFournisseur {

    public abstract void executer(Object... args);

    //Pour les entetes
    public boolean siExecutable(Object... args) {
        return true;
    }

}
