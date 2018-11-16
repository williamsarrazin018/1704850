package ca.cours5b5.williamsarrazin.proxy;

import android.app.DownloadManager;

import ca.cours5b5.williamsarrazin.controleurs.Action;
import ca.cours5b5.williamsarrazin.controleurs.ControleurAction;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.global.GCommande;

public class ProxyListe extends Proxy implements Fournisseur {

    private ChildEventListener childEventListener;

    private DownloadManager.Query requete;

    private Action actionNouvelItem;

    private List<DatabaseReference> noeudsAjoutes;

    public ProxyListe(String cheminServeur) {
        super(cheminServeur);
    }

    public void setActionNouvelItem(GCommande commande) {

        this.actionNouvelItem = ControleurAction.demanderAction(commande);
    }


    public void ajouterValeur(Object valeur) {

        DatabaseReference noeud = noeudServeur.push();
        noeud.setValue(valeur);
    }

    @Override
    public void connecterAuServeur() {
        super.connecterAuServeur();

        creerListener();

        requete = getRequete();

        requete.addChildEventListener(childEventListener);

    }

    private void creerListener() {
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Object valeurAjoutee = dataSnapshot.getValue();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


    }


    protected Query getRequete() {

        return noeudServeur.orderByValue().limitToLast(GConstantes.NOMBRE_DE_VALEURS_A_CHARGER_DU_SERVEUR_PAR_DEFAUT);
    }

    @Override
    public void deconnecterDuServeur() {
        /*
         * retirer le listener
         * oublier les noeuds ajoutés
         * déconnecter via super
         */
    }

    @Override
    public void detruireValeurs() {

    }
}