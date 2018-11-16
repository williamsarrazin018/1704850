package ca.cours5b5.williamsarrazin.modeles;

import java.util.HashMap;
import java.util.Map;

import ca.cours5b5.williamsarrazin.controleurs.ControleurAction;
import ca.cours5b5.williamsarrazin.controleurs.ControleurPartieReseau;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.williamsarrazin.exceptions.ErreurAction;
import ca.cours5b5.williamsarrazin.exceptions.ErreurSerialisation;
import ca.cours5b5.williamsarrazin.global.GCommande;
import ca.cours5b5.williamsarrazin.serialisation.AttributSerialisable;

public class MPartieReseau extends MPartie implements Fournisseur, Identifiable {

    @AttributSerialisable
    public String idJoueurInvite;
    private String __idJoueurInvite = "idJoueurInvite";

    @AttributSerialisable
    public String idJoueurHote;
    private String __idJoueurHote = "idJoueurHote";

    public MPartieReseau(MParametresPartie parametres) {
        super(parametres);
    }

    public String getId() {
        /*
         * utiliser l'id du joueur hôte
         */
        return null;
    }

    private void fournirActionRecevoirCoup() {
        ControleurAction.fournirAction(this, GCommande.RECEVOIR_COUP_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        try{

                            recevoirCoupReseau( (int) args[0]);

                        }catch(ClassCastException e){

                            throw new ErreurAction(e);

                        }
                    }
                });
    }

    @Override
    protected void fournirActionPlacerJeton() {


        ControleurAction.fournirAction(this, GCommande.JOUER_COUP_ICI,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        try{

                            int colonne = (Integer) args[0];

                            jouerCoup(colonne);

                            ControleurPartieReseau.getInstance().transmettreCoup(colonne);

                        }catch(ClassCastException e){

                            throw new ErreurAction(e);

                        }
                    }
                });
    }

    private void recevoirCoupReseau(int colonne) {

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        /*
         * charger les champs
         * appeler aussi super
         */
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {

        Map<String, Object> objetJson = new HashMap<>();

        objetJson.put(__idJoueurInvite, idJoueurHote.toString());

        objetJson.put(__idJoueurHote, idJoueurInvite.toString());

        return objetJson;
    }
}