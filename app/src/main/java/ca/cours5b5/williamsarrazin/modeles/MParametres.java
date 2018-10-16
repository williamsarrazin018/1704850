package ca.cours5b5.williamsarrazin.modeles;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.exceptions.ErreurSerialisation;
import ca.cours5b5.williamsarrazin.global.GConstantes;
import ca.cours5b5.williamsarrazin.serialisation.AttributSerialisable;

public class MParametres extends Modele implements Fournisseur {

    public static MParametres instance = new MParametres();

    static{
        Log.d("Atelier04", MParametres.class.getSimpleName() + "::static");

    }

    @AttributSerialisable
    public MParametresPartie parametresPartie;
    private String __parametresPartie = "parametresPartie";

    private List<Integer> choixHauteur;
    private List<Integer> choixLargeur;
    private List<Integer> choixPourGagner;

    @AttributSerialisable
    public Integer hauteur;
    private final String __hauteur = "hauteur";

    @AttributSerialisable
    public Integer largeur;
    private final String __largeur = "largeur";

    @AttributSerialisable
    public Integer pourGagner;
    private final String __pourGagner = "pourGagner";



    public MParametres(){
        super();

        parametresPartie = new MParametresPartie();

        hauteur = GConstantes.HAUTEUR_PAR_DEFAUT;
        largeur = GConstantes.LARGEUR_PAR_DEFAUT;
        pourGagner = GConstantes.POUR_GAGNER_PAR_DEFAUT;

        genererListesDeChoix();
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

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setPourGagner(int pourGagner) {
        this.pourGagner = pourGagner;
    }

    public MParametresPartie getParametresPartie() {
        return parametresPartie;
    }

    private void genererListesDeChoix(){
        genererListeChoixHauteur();
        genererListeChoixLargeur();
        genererListeChoixPourGagner();
    }

    private List<Integer> genererListeChoix(int min, int max){
        List<Integer> listeChoix = new ArrayList<>();

        for(int i = min; i <= max; i++){
            listeChoix.add(i);
        }

        return listeChoix;
    }

    private void genererListeChoixHauteur(){
        choixHauteur = genererListeChoix(GConstantes.HAUTEUR_MIN, GConstantes.HAUTEUR_MAX);
    }

    private void genererListeChoixLargeur(){
        choixLargeur = genererListeChoix(GConstantes.LARGEUR_MIN, GConstantes.LARGEUR_MAX);
    }

    private void genererListeChoixPourGagner(){
        choixPourGagner = genererListeChoix(GConstantes.POUR_GAGNER_MIN, GConstantes.POUR_GAGNER_MAX);
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation{

        //Appeler la methodes de parametresPartie
        parametresPartie.aPartirObjetJson(objetJson);
    }


    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {

        Map<String, Object> objetJson = new HashMap<>();

        Map<String, Object> objetJsonPartie = parametresPartie.enObjetJson();
        objetJson.put(__parametresPartie, objetJsonPartie);


        return objetJson;

    }
}
