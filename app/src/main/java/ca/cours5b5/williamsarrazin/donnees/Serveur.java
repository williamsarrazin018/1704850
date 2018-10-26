package ca.cours5b5.williamsarrazin.donnees;

import java.util.Map;

public final class Serveur extends SourceDeDonnees {

    private Serveur(){}

    private static final Serveur instance = null;

    public static Serveur getInstance(){
        return instance;
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson){

    }

    @Override
    public Map<String, Object> chargerModele(String cheminSauvegarde){

        return null;

    }

}