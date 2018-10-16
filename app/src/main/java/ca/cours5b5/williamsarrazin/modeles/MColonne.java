package ca.cours5b5.williamsarrazin.modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.williamsarrazin.exceptions.ErreurSerialisation;
import ca.cours5b5.williamsarrazin.global.GCouleur;

public class MColonne extends Modele {

    private List<GCouleur> jetons;

    public MColonne() {

        //initialiser la liste de jetons
        jetons = new ArrayList<>();

    }

    public List<GCouleur> getJetons() {

        return jetons;
    }

    public void placerJeton(GCouleur couleur) {

        //Ajouter une couleur/jeton dans la liste
        jetons.add(couleur);

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {

        //Inutilise

    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {

        //Inutilise
        return null;
    }
}
