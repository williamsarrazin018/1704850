package ca.cours5b5.williamsarrazin.proxy;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import ca.cours5b5.williamsarrazin.controleurs.Action;
import ca.cours5b5.williamsarrazin.controleurs.ControleurAction;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.global.GCommande;
import ca.cours5b5.williamsarrazin.global.GConstantes;

public class ProxyListe extends Proxy implements Fournisseur {

    private ChildEventListener childEventListener;

    private Query requete;

    private Action actionNouvelItem;

    private List<DatabaseReference> noeudsAjoutes;

    public ProxyListe(String cheminServeur) {
        super(cheminServeur);
        this.noeudsAjoutes = new ArrayList<>();
    }

    public void setActionNouvelItem(GCommande commande) {

        this.actionNouvelItem = ControleurAction.demanderAction(commande);
    }


    public void ajouterValeur(Object valeur) {

        //Noeud superclasse
        DatabaseReference noeud = super.noeudServeur.push();

        noeud.setValue(valeur);
        //AjouterNoeuds dans la liste
        noeudsAjoutes.add(noeud);
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

                if (actionNouvelItem != null) {

                    actionNouvelItem.setArguments(dataSnapshot.getValue().toString());
                    actionNouvelItem.executerDesQuePossible();

                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                //unused
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                //unused
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                //unused
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //unused
            }
        };


    }


    protected Query getRequete() {

        return super.noeudServeur.orderByKey().limitToFirst(GConstantes.NOMBRE_DE_VALEURS_A_CHARGER_DU_SERVEUR_PAR_DEFAUT);
    }

    @Override
    public void deconnecterDuServeur() {
        //Deconnecter et enlever listener
        super.deconnecterDuServeur();
        requete.removeEventListener(childEventListener);
    }

    @Override
    public void detruireValeurs() {

        //Enlever valeurs des noeuds
        for(DatabaseReference noeud : noeudsAjoutes){

            noeud.removeValue();

        }

    }
}