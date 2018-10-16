package ca.cours5b5.williamsarrazin.modeles;

import java.util.List;
import java.util.Map;

import ca.cours5b5.williamsarrazin.exceptions.ErreurSerialisation;
import ca.cours5b5.williamsarrazin.global.GCouleur;

public class MGrille extends Modele{

    private List<MColonne> colonnes;



    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        //unused
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        //unused
        return null;
    }

    public MGrille(int largeur) {

    }

    public void initialiserColonnes(int largeur) {

    }

    public List<MColonne> getColonnes() {
        return  colonnes;
    }

    public void placerJeton(int colonne, GCouleur couleur) {

    }

}
