package ca.cours5b5.williamsarrazin.modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.williamsarrazin.exceptions.ErreurSerialisation;
import ca.cours5b5.williamsarrazin.global.GCouleur;

public class MGrille extends Modele{

    private List<MColonne> colonnes = new ArrayList<>();

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

        initialiserColonnes(largeur);

    }

    public void initialiserColonnes(int largeur) {

        for (int i = 0; i < largeur; i++) {
            //Ajouter une instance de MColonnes pour chaque colonne dans la liste
            colonnes.add(new MColonne());

        }

    }

    public List<MColonne> getColonnes() {

        return  colonnes;
    }

    public void placerJeton(int colonne, GCouleur couleur) {

        //Placer le jeton de la bonne couleur a la bonne colonne
        colonnes.get( colonne ).placerJeton(couleur);

    }

}
