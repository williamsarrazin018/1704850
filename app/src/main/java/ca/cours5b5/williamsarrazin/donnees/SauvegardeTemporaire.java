package ca.cours5b5.williamsarrazin.donnees;

import android.os.Bundle;

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

            //Si contient le chemin de sauvegarde des données
            if (bundle.containsKey(cheminSauvegarde) && bundle != null) {

                String json = bundle.getString(cheminSauvegarde);

                Map<String, Object> objetJson = Jsonification.aPartirChaineJson(json);

                //Chargement Reussi
                listenerChargement.reagirSucces(objetJson);

            } else {

                ErreurModele e = new ErreurModele("Clé introuvée");

                listenerChargement.reagirErreur(e);

            }

        } catch (Exception e) {
            listenerChargement.reagirErreur(e);
        }
    }

}