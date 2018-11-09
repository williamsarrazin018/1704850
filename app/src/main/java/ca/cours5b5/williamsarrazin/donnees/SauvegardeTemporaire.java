package ca.cours5b5.williamsarrazin.donnees;

import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import ca.cours5b5.williamsarrazin.exceptions.ErreurModele;
import ca.cours5b5.williamsarrazin.serialisation.Jsonification;

public class SauvegardeTemporaire extends SourceDeDonnees {

    private Bundle bundle;

    public SauvegardeTemporaire(Bundle bundle){
        this.bundle = bundle;
    }

    @Override
    public void sauvegarderModele(String cheminSauvegarde, Map<String, Object> objetJson) {
        if(bundle != null){

            String json = Jsonification.enChaineJson(objetJson);
            bundle.putString(cheminSauvegarde, json);

        }
    }

    @Override
    public void chargerModele(String cheminSauvegarde, ListenerChargement listenerChargement) {
        try {
            Log.d("chargerModele", "Sauvegarde temp debuuuut");
            //Si contient le chemin de sauvegarde des données
            if (bundle != null && bundle.containsKey(cheminSauvegarde)) {
                Log.d("chargerModele", "Sauvegarde temp success");
                String json = bundle.getString(cheminSauvegarde);

                Map<String, Object> objetJson = Jsonification.aPartirChaineJson(json);

                //Chargement Reussi
                listenerChargement.reagirSucces(objetJson);

            } else {
                Log.d("chargerModele", "Sauvegarde temp elseeee");
                ErreurModele e = new ErreurModele("Clé introuvée");

                listenerChargement.reagirErreur(e);

            }

        } catch (Exception e) {
            Log.d("chargerModele", "Sauvegarde temp fail");
            listenerChargement.reagirErreur(e);
        }
    }

}