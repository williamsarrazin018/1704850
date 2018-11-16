package ca.cours5b5.williamsarrazin.controleurs;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.williamsarrazin.controleurs.interfaces.Fournisseur;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.williamsarrazin.donnees.Disque;
import ca.cours5b5.williamsarrazin.donnees.ListenerChargement;
import ca.cours5b5.williamsarrazin.donnees.Serveur;
import ca.cours5b5.williamsarrazin.donnees.SourceDeDonnees;
import ca.cours5b5.williamsarrazin.exceptions.ErreurModele;
import ca.cours5b5.williamsarrazin.modeles.MParametres;
import ca.cours5b5.williamsarrazin.modeles.MPartie;
import ca.cours5b5.williamsarrazin.modeles.MPartieReseau;
import ca.cours5b5.williamsarrazin.modeles.Modele;
import ca.cours5b5.williamsarrazin.usagers.UsagerCourant;

public final class ControleurModeles {

    private ControleurModeles(){}

    private static Map<String, Modele> modelesEnMemoire;

    private static SourceDeDonnees[] sequenceDeChargement;

    private static List<SourceDeDonnees> listeDeSauvegardes;

    static {

        modelesEnMemoire = new HashMap<>();

        listeDeSauvegardes = new ArrayList<>();
        listeDeSauvegardes.add(Disque.getInstance());
        //Ajout serveur comme source de donnée
        listeDeSauvegardes.add(Serveur.getInstance());


    }

    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement){

        Log.d("atelier12", "sequence");
        ControleurModeles.sequenceDeChargement = sequenceDeChargement;

    }

    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        Modele modele = modelesEnMemoire.get(nomModele);

        String cheminDeSauvegarde = getCheminSauvegarde(nomModele);

        if(modele != null){

            Map<String, Object> objetJson = modele.enObjetJson();

            sourceDeDonnees.sauvegarderModele(cheminDeSauvegarde, objetJson);

        }
    }

    static void getModele(String nomModele, ListenerGetModele listenerGetModele){

        Modele modele = modelesEnMemoire.get(nomModele);

        //Si pas de modele
        if(modele == null){
            Log.d("atelier12", "pas modele");
            final String nomMod = nomModele;
            creerModeleEtChargerDonnees(nomMod, listenerGetModele);

        } else {

            listenerGetModele.reagirAuModele(modele);

        }
    }

/*
    private static Modele chargerViaSequenceDeChargement(final String nomModele){

        Modele modele = creerModeleSelonNom(nomModele);

        modelesEnMemoire.put(nomModele, modele);

        for(SourceDeDonnees sourceDeDonnees : sequenceDeChargement){

            Map<String, Object> objetJson = sourceDeDonnees.chargerModele(getCheminSauvegarde(nomModele));

            if(objetJson != null){

                modele.aPartirObjetJson(objetJson);
                break;

            }

        }

        return modele;
    }
*/
    public static void sauvegarderModele(String nomModele) throws ErreurModele {

        for(SourceDeDonnees source : listeDeSauvegardes){

            sauvegarderModeleDansCetteSource(nomModele, source);

        }

    }


    private static void creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele {

        if(nomModele.equals(MParametres.class.getSimpleName())){

            listenerGetModele.reagirAuModele( new MParametres() );

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres params = (MParametres) modele;
                    //Nouveau modele avec params de nouvelle partie
                    MPartie mPartie = new MPartie(params.getParametresPartie().cloner());
                    listenerGetModele.reagirAuModele(mPartie);
                }
            });

        } else if( nomModele.equals(MPartieReseau.class.getSimpleName()) ){

            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {

                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres params = (MParametres) modele;

                    //Nouveau modele avec params de nouvelle partie
                    MPartie mPartie = new MPartie(params.getParametresPartie().cloner());

                    listenerGetModele.reagirAuModele(mPartie);
                }

            });
        } else{

            throw new ErreurModele("Modèle inconnu: " + nomModele);

        }
    }

    public static void detruireModele(String nomModele) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            modelesEnMemoire.remove(nomModele);

            ControleurObservation.detruireObservation(modele);

            if(modele instanceof Fournisseur){

                ControleurAction.oublierFournisseur((Fournisseur) modele);

            }
        }
    }

    private static String getCheminSauvegarde(String nomModele) {

        //Chemin ou on va sauvegarder
        String chemin = nomModele + '/' + UsagerCourant.getId();

        return chemin;

    }

    private static void creerModeleEtChargerDonnees(final String nomModele, final ListenerGetModele listenerGetModele) {

        creerModeleSelonNom(nomModele, new ListenerGetModele() {

            @Override
            public void reagirAuModele(Modele modele) {

                //Mettre le modele en memoire, puis charger
                modelesEnMemoire.put(nomModele, modele);

                chargerDonnees(modele, nomModele, listenerGetModele);

            }
        });


    }


    private static void chargerDonnees(Modele modele, String nomModele, ListenerGetModele listenerGetModele) {

        String save = getCheminSauvegarde(nomModele);

        int ind = 0;

        //Charger d'apres la sequence
        chargementViaSequence(modele, save, listenerGetModele, ind);

    }

    private static void chargementViaSequence(Modele modele, String cheminDeSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){

        Log.d("atelier12", "load sequence " + indiceSourceCourante + modele.getClass().getSimpleName());
        Log.d("sequence", "seq chargement length  " + sequenceDeChargement.length);
        if( indiceSourceCourante >= sequenceDeChargement.length ){

            terminerChargement(modele, listenerGetModele);

        }else{

            chargementViaSourceCouranteOuSuivante(modele, cheminDeSauvegarde, listenerGetModele, indiceSourceCourante);

        }

    }

    private static void chargementViaSourceCouranteOuSuivante(final Modele modele, final String cheminDeSauvegarde, final ListenerGetModele listenerGetModele, final int indiceSourceCourante) {

        //Charger modele de la source courante
        sequenceDeChargement[indiceSourceCourante].chargerModele(cheminDeSauvegarde, new ListenerChargement() {

            @Override
            public void reagirSucces(Map<String, Object> objetJson) {
                Log.d("chargerModele", "charger modele success" + modele.getClass().getSimpleName());
                terminerChargementAvecDonnees(objetJson, modele, listenerGetModele);

            }

            @Override
            public void reagirErreur(Exception e) {
                Log.d("chargerModele", "charger modele erreur" + modele.getClass().getSimpleName());
                //Erreur, prochaine source
                chargementViaSourceSuivante(modele, cheminDeSauvegarde, listenerGetModele, indiceSourceCourante);

            }
        });

    }

    private static void chargementViaSourceSuivante(Modele modele, String cheminDeSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante) {

        //On charge avec la prochaine source dans la sequence
        chargementViaSequence(modele, cheminDeSauvegarde, listenerGetModele, (indiceSourceCourante + 1));
    }

    private static void terminerChargementAvecDonnees(Map<String, Object> objetJson, Modele modele, ListenerGetModele listenerGetModele) {

        //Obtenir donnees
        modele.aPartirObjetJson(objetJson);

        terminerChargement(modele, listenerGetModele);

    }

    private static void terminerChargement(Modele modele, ListenerGetModele listenerGetModele) {
        Log.d("atelier12", "fin loading" + modele.getClass().getSimpleName());

        //Reagir aux modif du modele
        listenerGetModele.reagirAuModele(modele);

    }




}
