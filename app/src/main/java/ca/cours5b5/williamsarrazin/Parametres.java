package ca.cours5b5.williamsarrazin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class Parametres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);

        Log.d("Bonjour",  this.getResources().getString(R.string.message_bienvenue) + this.getResources().getString(R.string.orientation));


    }
}
