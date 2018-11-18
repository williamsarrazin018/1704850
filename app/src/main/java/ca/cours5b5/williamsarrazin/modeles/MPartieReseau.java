package ca.cours5b5.williamsarrazin.modeles;

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
    public String idJoueurInvite = "CQSxbPFSGbhjHOxMBIJiP4R9Hn92";
    private String __idJoueurInvite = "idJoueurInvite";

    @AttributSerialisable
    public String idJoueurHote = "85NOvo4XZ7QWS5NAEGZkJJFjz9M2";
    private String __idJoueurHote = "idJoueurHote";

    public MPartieReseau(MParametresPartie parametres) {
        super(parametres);
        fournirActionRecevoirCoup();
    }

    public String getId() {

        return idJoueurHote;
    }

    private void fournirActionRecevoirCoup() {
        ControleurAction.fournirAction(this, GCommande.RECEVOIR_COUP_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {
                        try{
                            //Chercher coup du reseau
                            recevoirCoupReseau( Integer.parseInt((String)args[0]));

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

                            jouerCoup((int) args[0]);

                            ControleurPartieReseau.getInstance().transmettreCoup(colonne);

                        }catch(ClassCastException e){

                            throw new ErreurAction(e);

                        }
                    }
                });
    }

    private void recevoirCoupReseau(int colonne) {
        //Jouer le coup
        jouerCoup(colonne);
    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        //Charger les champs

        super.aPartirObjetJson(objetJson);
        idJoueurInvite = (String) objetJson.get(__idJoueurInvite);
        idJoueurHote = (String) objetJson.get(__idJoueurHote);

    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {

        //Appel methode de la superclasse pour init le map
        Map<String, Object> objetJson = super.enObjetJson();

        objetJson.put(__idJoueurInvite, idJoueurInvite);

        objetJson.put(__idJoueurHote, idJoueurHote);

        return objetJson;
    }
}