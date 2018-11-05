package ca.cours5b5.williamsarrazin.donnees;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

import ca.cours5b5.williamsarrazin.serialisation.Jsonification;

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
    public Map<String, Object> chargerModele(String cheminSauvegarde){

        //Charger le modele du serveur
        DatabaseReference noeud = FirebaseDatabase.getInstance().getReference(cheminSauvegarde);

        return Jsonification.aPartirChaineJson(noeud.toString());


    }

}