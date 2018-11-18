package ca.cours5b5.williamsarrazin.donnees;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import ca.cours5b5.williamsarrazin.exceptions.ErreurModele;

public final class Serveur extends SourceDeDonnees {

    private Serveur(){}

    private static final Serveur instance = new Serveur();

    public static Serveur getInstance(){
        return instance;
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson){

        //On crée un noeud dans la db firebase avec le modele dedans
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);

        noeud.setValue(objetJson);

    }

    @Override
    public void chargerModele(final String cheminSauvegarde, final ListenerChargement listenerChargement){

        Log.d("atelier12", "charger modele bd");
        //Charger le modele du serveur
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);


        noeud.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Log.d("serveur", "iffffffffff");
                    Log.d("atelier12", "bd on data changed");

                    //Les données sont lues

                    Map<String, Object> objetJson = (Map<String, Object>) dataSnapshot.getValue();

                    listenerChargement.reagirSucces(objetJson);

                } else {
                    Log.d("serveur", "elseeeeee");
                    //Pas de données dans ce noeud donc erreur
                    ErreurModele e = new ErreurModele("Clé introuvée");

                    listenerChargement.reagirErreur(e);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Erreur de lecturee
            }
        });


    }

    public void detruireSauvegarde(String cheminSauvegarde) {
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);

        noeud.removeValue();
    }

}