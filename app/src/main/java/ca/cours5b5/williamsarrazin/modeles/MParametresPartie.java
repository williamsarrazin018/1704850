package ca.cours5b5.williamsarrazin.modeles;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.williamsarrazin.exceptions.ErreurSerialisation;
import ca.cours5b5.williamsarrazin.serialisation.AttributSerialisable;

public class MParametresPartie extends Modele {

    @AttributSerialisable
    public Integer hauteur;
    protected final String __hauteur = "hauteur";

    @AttributSerialisable
    public Integer largeur;
    protected final String __largeur = "largeur";

    @AttributSerialisable
    public Integer pourGagner;
    protected final String __pourGagner = "pourGagner";


    public static MParametresPartie aPartirMParametres(MParametres mParametres){

        MParametresPartie mParametresPartie = new MParametresPartie();

        mParametresPartie.setHauteur(mParametres.getHauteur());
        mParametresPartie.setLargeur(mParametres.getLargeur());
        mParametresPartie.setPourGagner(mParametres.getPourGagner());

        return mParametresPartie;
    }

    public MParametresPartie cloner() throws CloneNotSupportedException {
        MParametresPartie modele = (MParametresPartie) this.clone();
        return modele;
    }

    public MParametresPartie(){

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

    public void setHauteur(Integer hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    public void setPourGagner(Integer pourGagner) {
        this.pourGagner = pourGagner;
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {

        for(Map.Entry<String, Object> entry : objetJson.entrySet()){

            String chaineValeur = (String) entry.getValue();

            switch (entry.getKey()){

                case __hauteur:

                    hauteur = Integer.valueOf(chaineValeur);
                    break;

                case __largeur:

                    largeur = Integer.valueOf(chaineValeur);
                    break;


                case __pourGagner:

                    largeur = Integer.valueOf(chaineValeur);
                    break;

                default:

                    throw new ErreurSerialisation("Attribut inconnu: " + entry.getKey());
            }
        }

    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__hauteur, hauteur.toString());
        objetJson.put(__largeur, largeur.toString());
        objetJson.put(__pourGagner, pourGagner.toString());

        return objetJson;
    }
}
