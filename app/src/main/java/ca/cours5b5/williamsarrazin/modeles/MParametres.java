package ca.cours5b5.williamsarrazin.modeles;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.williamsarrazin.global.GConstantes;
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

        genererListeChoixHauteur();
        genererListeChoixLargeur();
        genererListeChoixPourGagner();

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

    public void setHauteur(int hauteur){

        this.hauteur = hauteur;

    }

    public void setLargeur(int largeur){

        this.largeur = largeur;

    }

    public void setPourGagner(int pourGagner){

        this.pourGagner = pourGagner;

    }

    private void genererListeChoixHauteur(){

        List<Integer> listeChoix = new ArrayList<Integer>();

        for (int i = GConstantes.HAUTEUR_MIN; i <= GConstantes.HAUTEUR_MAX; i++) {
            listeChoix.add(i);
        }

        this.setHauteur(2);
        choixHauteur = listeChoix;


    }

    private void genererListeChoixLargeur(){

        List<Integer> listeChoix = new ArrayList<Integer>();

        for (int i = GConstantes.LARGEUR_MIN; i <= GConstantes.LARGEUR_MAX; i++) {
            listeChoix.add(i);
        }

        this.setLargeur(2);
        choixLargeur = listeChoix;

    }

    private void genererListeChoixPourGagner(){

        List<Integer> listeChoix = new ArrayList<Integer>();

        for (int i = GConstantes.GAGNER_MIN; i <= GConstantes.GAGNER_MAX; i++) {
            listeChoix.add(i);
        }

        this.setPourGagner(2);
        choixPourGagner = listeChoix;

    }
}
