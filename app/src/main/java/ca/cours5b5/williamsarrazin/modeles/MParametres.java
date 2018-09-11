package ca.cours5b5.williamsarrazin.modeles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.cours5b5.williamsarrazin.serialisation.AttributSerialisable;

public class MParametres extends Modele{

    //FIXME: C'est temporaire ; on va écrire un gestionnaire de modèles dans l'atelier 7
    public static MParametres instance = new MParametres();

    @AttributSerialisable
    public Integer hauteur = 6;
    public final String __hauteur = "hauteur";

    @AttributSerialisable
    public Integer largeur = 7;
    public final String __largeur = "largeur";

    @AttributSerialisable
    public Integer pourGagner = 4;
    public final String __pourGagner = "pourGagner";

    public MParametres() {

    }

    public List<Integer> getChoixHauteur() {
        List<Integer> liste = new ArrayList<Integer>();

        for (int i = 4; i < 11; i++) {
            liste.add(i);
        }

        return liste;

    }

    public List<Integer> getChoixLargeur(){

        List<Integer> liste = new ArrayList<Integer>();

        for (int i = 4; i < 11; i++) {
            liste.add(i);
        }

        return liste;

    }

    public List<Integer> getChoixPourGagner(){

        List<Integer> liste = new ArrayList<Integer>();

        for (int i = 3; i < 5; i++) {
            liste.add(i);
        }

        return liste;

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

        for (Map.Entry<String, Object> entry : objetJson.entrySet()){

            String cle = entry.getKey();
            Object valeur = entry.getValue();

            //si cle hauteur set hauteur , etc

        }

    }

    @Override
    public Map<String, Object> enObjetJson() {
        return null;
    }
}
