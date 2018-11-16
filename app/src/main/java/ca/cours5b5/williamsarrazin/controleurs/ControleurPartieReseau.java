package ca.cours5b5.williamsarrazin.controleurs;

import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.williamsarrazin.donnees.Serveur;
import ca.cours5b5.williamsarrazin.global.GCommande;
import ca.cours5b5.williamsarrazin.global.GConstantes;
import ca.cours5b5.williamsarrazin.modeles.MPartieReseau;
import ca.cours5b5.williamsarrazin.modeles.Modele;
import ca.cours5b5.williamsarrazin.proxy.ProxyListe;
import ca.cours5b5.williamsarrazin.usagers.UsagerCourant;

public final class ControleurPartieReseau {

    private static final ControleurPartieReseau instance = new ControleurPartieReseau();

    public static ControleurPartieReseau getInstance(){
        return instance;
    }

    private ProxyListe proxyEmettreCoups;
    private ProxyListe proxyRecevoirCoups;

    public void connecterAuServeur() {

        ControleurModeles.getModele(MPartieReseau.class.getSimpleName(), new ListenerGetModele() {

            @Override
            public void reagirAuModele(Modele modele) {

                MPartieReseau mPartieReseau = (MPartieReseau) modele;

                connecterAuServeur(mPartieReseau.getId());

            }
        });




    }

    private void connecterAuServeur(String idJoueurHote) {


        if(UsagerCourant.getId().equals(idJoueurHote)){

            connecterEnTantQueJoueurHote(getCheminCoupsJoueurHote(idJoueurHote), getCheminCoupsJoueurInvite(idJoueurHote));

        }else{

            connecterEnTantQueJoueurInvite(getCheminCoupsJoueurHote(idJoueurHote), getCheminCoupsJoueurInvite(idJoueurHote));

        }

        proxyEmettreCoups.connecterAuServeur();

        proxyRecevoirCoups.connecterAuServeur();

        proxyRecevoirCoups.setActionNouvelItem(GCommande.RECEVOIR_COUP_RESEAU);

    }

    private void connecterEnTantQueJoueurHote(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {

        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurHote);

        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurInvite);


    }

    private void connecterEnTantQueJoueurInvite(String cheminCoupsJoueurHote, String cheminCoupsJoueurInvite) {

        proxyRecevoirCoups = new ProxyListe(cheminCoupsJoueurHote);
        proxyEmettreCoups = new ProxyListe(cheminCoupsJoueurInvite);

    }

    public void deconnecterDuServeur() {

        proxyEmettreCoups.detruireValeurs();

        proxyEmettreCoups.deconnecterDuServeur();
        proxyRecevoirCoups.deconnecterDuServeur();

    }

    public void transmettreCoup(Integer idColonne) {

        proxyEmettreCoups.ajouterValeur(idColonne);

    }

    private String getCheminCoupsJoueurInvite(String idJoueurHote) {

        return getCheminPartie(idJoueurHote) + '/' + GConstantes.CLE_COUPS_JOUEUR_INVITE;
    }

    private String getCheminCoupsJoueurHote(String idJoueurHote) {

        return getCheminPartie(idJoueurHote) + '/' + GConstantes.CLE_COUPS_JOUEUR_HOTE;
    }

    private String getCheminPartie(String idJoueurHote) {

        return MPartieReseau.class.getSimpleName() + '/' + idJoueurHote;
    }

    public void detruireSauvegardeServeur() {

        Serveur.getInstance().detruireSauvegarde(getCheminPartie(UsagerCourant.getId()));
    }

}