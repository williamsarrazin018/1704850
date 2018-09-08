package ca.cours5b5.williamsarrazin.modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.williamsarrazin.global.GConstantes;
import ca.cours5b5.williamsarrazin.serialisation.AttributSerialisable;

public class MParametres extends Modele{

    //FIXME: C'est temporaire ; on va écrire un gestionnaire de modèles dans l'atelier 7
    public static MParametres instance;

    @AttributSerialisable
    public Integer hauteur;
    public final String __hauteur = "hauteur";

    @AttributSerialisable
    public Integer largeur;
    public final String __largeur = "largeur";

    @AttributSerialisable
    public Integer pourGagner;
    public final String __pourGagner = "pourGagner";

    //private List<Integer> choixHauteur;
    //private List<Integer> choixLargeur;
    //private List<Integer> choixPourGagner;



    public List<Integer> getChoixHauteur(){
        return null;
    }

    public List<Integer> getChoixLargeur(){
        return null;
    }

    public List<Integer> getChoixPourGagner(){
        return null;
    }

    public Integer getHauteur() {
        return hauteur;
    }

    public Integer getLargeur() {
        return largeur;
    }

    public Integer getPourGagner() {
        return pourGagner;
    }

    public void setHauteur(int hauteur){

        this.hauteur = hauteur;

    }

    public void setLargeur(int largeur){

        this.largeur = largeur;

    }

    public void setPourGagner(int pourGagner){

        this.pourGagner = pourGagner;

    }


    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) {

    }

    @Override
    public Map<String, Object> enObjetJson() {
        return null;
    }
}
