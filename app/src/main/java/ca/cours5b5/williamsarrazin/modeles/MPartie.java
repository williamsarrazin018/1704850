package ca.cours5b5.williamsarrazin.modeles;

import java.util.Map;

import ca.cours5b5.williamsarrazin.controleurs.ControleurAction;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.williamsarrazin.exceptions.ErreurSerialisation;
import ca.cours5b5.williamsarrazin.global.GCommande;
import ca.cours5b5.williamsarrazin.global.GCouleur;
import ca.cours5b5.williamsarrazin.serialisation.AttributSerialisable;

public class MPartie extends Modele implements Fournisseur{

    //public static  MPartie instance = new MPartie(MParametres.instance.getParametresPartie());

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";
    private MGrille grille;
    private GCouleur couleurCourante;

    public MPartie(MParametresPartie parametres){

        //Creer la partie de base
        this.parametres = parametres;
        this.grille = new MGrille(parametres.getLargeur());
        initialiserCouleurCourante();
        fournirActionPlacerJeton();

    }

    public MParametresPartie getParametres() {

        return parametres;

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        //Inutilisé
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        //inutilisé
        return null;
    }

    public MGrille getGrille() {

        return grille;
    }

    private void initialiserCouleurCourante() {

        //Par defaut, Rouge pour le premier coup du premier joueur
        couleurCourante = GCouleur.ROUGE;

    }

    private void fournirActionPlacerJeton() {

        ControleurAction.fournirAction(this, GCommande.JOUEUR_COUP_ICI, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {
                //Action qui sera executee quand le joueur clique sur l'entete pour jouer avec comme arguments le numero de colonne
                jouerCoup( (int) args[0] );

            }
        });

    }

    protected void jouerCoup(int colonne) {

        //Placer le jeton dans la bonne colonne et changer la couleur pour passer a l'autre joueur
        grille.placerJeton(colonne, couleurCourante);

        prochaineCouleurCourante();

    }

    private void prochaineCouleurCourante() {

        //Changer de couleur / passer au tour de l'autre joueur
        if(couleurCourante.equals(GCouleur.JAUNE)) {

            couleurCourante = GCouleur.ROUGE;

        } else if (couleurCourante.equals(GCouleur.ROUGE)) {

            couleurCourante = GCouleur.JAUNE;

        }

    }


}
