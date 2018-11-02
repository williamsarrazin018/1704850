package ca.cours5b5.williamsarrazin.usagers;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class UsagerCourant {

    public static boolean siUsagerConnecte() {

        boolean connected = false;

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            connected = true;
        }

        return connected;
    }

    public static String getId(){

        String id = "0";

        if (siUsagerConnecte()) {

            id = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

        }

        return id;

    }

}
