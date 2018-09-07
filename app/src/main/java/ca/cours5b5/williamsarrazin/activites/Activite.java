package ca.cours5b5.williamsarrazin.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import ca.cours5b5.williamsarrazin.R;

public abstract class Activite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d("Atelier04",  this.getClass().getSimpleName() + "::onCreate");

    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.d("Atelier04",  this.getClass().getSimpleName() + "::onResume");

    }

    @Override
    protected void onPause() {

        super.onPause();
        Log.d("Atelier04",  this.getClass().getSimpleName() + "::onPause");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        Log.d("Atelier04",  this.getClass().getSimpleName() + "::onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.d("Atelier04",  this.getClass().getSimpleName() + "::onDestroy");
    }


}
