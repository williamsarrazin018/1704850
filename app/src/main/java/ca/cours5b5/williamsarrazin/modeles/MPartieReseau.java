package ca.cours5b5.williamsarrazin.modeles;


import java.util.Map;
import java.util.Objects;

import ca.cours5b5.williamsarrazin.controleurs.ControleurAction;
import ca.cours5b5.williamsarrazin.controleurs.ControleurPartieReseau;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.williamsarrazin.exceptions.ErreurAction;
import ca.cours5b5.williamsarrazin.exceptions.ErreurSerialisation;
import ca.cours5b5.williamsarrazin.global.GCommande;
import ca.cours5b5.williamsarrazin.global.GConstantes;
import ca.cours5b5.williamsarrazin.serialisation.AttributSerialisable;
import ca.cours5b5.williamsarrazin.usagers.UsagerCourant;

public class MPartieReseau extends MPartie implements Fournisseur, Identifiable {

    private boolean tour;

    @AttributSerialisable
    public String idJoueurInvite;
    private String __idJoueurInvite = GConstantes.CLE_ID_JOUEUR_INVITE;

    @AttributSerialisable
    public String idJoueurHote;
    private String __idJoueurHote = GConstantes.CLE_ID_JOUEUR_HOTE;

    public MPartieReseau(MParametresPartie parametres) {
        super(parametres);

        fournirActionRecevoirCoup();

    }


    private void fournirActionRecevoirCoup() {

        ControleurAction.fournirAction(this,
                GCommande.RECEVOIR_COUP_RESEAU,
                new ListenerFournisseur() {
                    @Override
                    public void executer(Object... args) {

                        String idColonne = (String) args[0];
                        recevoirCoupReseau(Integer.valueOf(idColonne));

                        prochainTour();


                    }
                });
    }


    @Override
    protected void fournirActionPlacerJeton() {

        ControleurAction.fournirAction(this,
                GCommande.PLACER_JETON_ICI,
                new ListenerFournisseur() {



                    @Override
                    public void executer(Object... args) {
                        try{

                            int colonne = (Integer) args[0];

                            jouerCoup(colonne);


                            ControleurPartieReseau.getInstance().transmettreCoup(colonne);

                            prochainTour();

                        }catch(ClassCastException e){

                            throw new ErreurAction(e);

                        }
                    }


                    public boolean siExecutable(Objects... args) {
                            int colonne = Integer.parseInt(args[0].toString());

                            return siCoupLegal(colonne);
                    }

                });
    }


    private void recevoirCoupReseau(int colonne){

        if(super.siCoupLegal(colonne)){
            super.jouerCoupLegal(colonne);
        }
    }


    @Override
    protected boolean siCoupLegal(int colonne) {

        boolean legal = false;

        if (super.siCoupLegal(colonne) && tour) {
            legal = true;
        }

        return legal;

    }


    public void setIdJoueurs(String idJoueurHote, String idJoueurInvite){
        this.idJoueurHote = idJoueurHote;
        this.idJoueurInvite = idJoueurInvite;
    }


    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurSerialisation {
        super.aPartirObjetJson(objetJson);

        idJoueurHote = (String) objetJson.get(__idJoueurHote);
        idJoueurInvite = (String) objetJson.get(__idJoueurInvite);

        chargerData();

    }


    @Override
    public Map<String, Object> enObjetJson() throws ErreurSerialisation {
        Map<String, Object> objetJson = super.enObjetJson();

        objetJson.put(__idJoueurHote, idJoueurHote);
        objetJson.put(__idJoueurInvite, idJoueurInvite);

        return objetJson;

    }


    public String getId() {
        return idJoueurHote;
    }

    private void prochainTour() {
        tour = !tour;
    }

    private void chargerData() {
        tour = UsagerCourant.getId().equals(idJoueurHote);
    }
}
