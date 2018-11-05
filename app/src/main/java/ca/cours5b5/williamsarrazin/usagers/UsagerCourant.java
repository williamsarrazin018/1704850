package ca.cours5b5.williamsarrazin.usagers;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class UsagerCourant {

    public static boolean siUsagerConnecte() {

        //On regarde s'il y a des usagers de connectés

        boolean connected = false;

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            connected = true;
        }

        return connected;
    }

    public static String getId(){

        //L'id est 0 par défaut (Pas de connectés)

        String id = "0";

        if (siUsagerConnecte()) {

            //On prend comme id le Uid de l'utilisateur
            id = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        }

        return id;

    }

}
