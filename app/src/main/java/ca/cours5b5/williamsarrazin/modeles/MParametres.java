package ca.cours5b5.williamsarrazin.modeles;

import java.util.List;

import ca.cours5b5.williamsarrazin.serialisation.AttributSerialisable;

public class MParametres extends Modele{

    //FIXME: C'est temporaire ; on va écrire un gestionnaire de modèles dans l'atelier 7
    public static MParametres instance;

    @AttributSerialisable
    public Integer hauteur;

    @AttributSerialisable
    public Integer largeur;

    @AttributSerialisable
    public Integer pourGagner;

    private List<Integer> choixHauteur;
    private List<Integer> choixLargeur;
    private List<Integer> choixPourGagner;

    public MParametres(){

    }

    public List<Integer> getChoixHauteur(){
        return choixHauteur;
    }

    public List<Integer> getChoixLargeur(){
        return choixLargeur;
    }

    public List<Integer> getChoixPourGagner(){
        return choixPourGagner;
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

    public void setHauteur(){


    }

    public void setLargeur(){

    }

    public void setPourGagner(){

    }

    private void genererListeChoixHauteur(){

    }

    private void genererListeChoixLargeur(){

    }

    private void genererListeChoixPourGagner(){

    }
}
