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

        ControleurModeles.sequenceDeChargement = sequenceDeChargement;

    }

    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            Map<String, Object> objetJson = modele.enObjetJson();

            sourceDeDonnees.sauvegarderModele(nomModele, objetJson);

        }
    }

    static void getModele(String nomModele, ListenerGetModele listenerGetModele){

        Modele modele = modelesEnMemoire.get(nomModele);

        //Si pas de modele
        if(modele == null){

            creerModeleEtChargerDonnees(nomModele, listenerGetModele);

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


    private static Modele creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele {

        if(nomModele.equals(MParametres.class.getSimpleName())){

            listenerGetModele.reagirAuModele( new MParametres() );

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres params = (MParametres) modele;
                    //Nouveau modele avec params de nouvelle partie
                    listenerGetModele.reagirAuModele(new MPartie(params.getParametresPartie().cloner()));
                }
            });

        }else{

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

        //Charger d'apres la sequence
        chargementViaSequence(modele, save, listenerGetModele, 0);

    }

    private static void chargementViaSequence(Modele modele, String cheminDeSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){


        if( indiceSourceCourante < sequenceDeChargement.length ){

            //Source suivante ou courante
            chargementViaSourceCouranteOuSuivante(modele, cheminDeSauvegarde, listenerGetModele, indiceSourceCourante);

        }else{

            //On fini le chargement
            terminerChargement(modele, listenerGetModele);

        }

    }

    private static void chargementViaSourceCouranteOuSuivante(final Modele modele, final String cheminDeSauvegarde, final ListenerGetModele listenerGetModele, final int indiceSourceCourante) {

        //Charger modele de la source courante
        sequenceDeChargement[indiceSourceCourante].chargerModele(cheminDeSauvegarde, new ListenerChargement() {

            @Override
            public void reagirSucces(Map<String, Object> objetJson) {

                terminerChargementAvecDonnees(objetJson, modele, listenerGetModele);

            }

            @Override
            public void reagirErreur(Exception e) {

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
        Log.d("atelier12", "fin loading");

        //Reagir aux modif du modele
        listenerGetModele.reagirAuModele(modele);

    }




}
