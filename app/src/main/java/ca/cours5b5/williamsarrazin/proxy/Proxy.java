package ca.cours5b5.williamsarrazin.proxy;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public abstract class Proxy {

    private String cheminServeur;

    protected DatabaseReference noeudServeur;

    public Proxy(String cheminServeur) {
        this.cheminServeur = cheminServeur;
    }

    public void connecterAuServeur() {

        noeudServeur = FirebaseDatabase.getInstance().getReference(cheminServeur);
        Log.d("Atelier13", "connecterAuServeurproxy");
    }

    public void deconnecterDuServeur() {
        //Oublier noeud
        noeudServeur = null;
        detruireValeurs();

    }

    public abstract void detruireValeurs();
}