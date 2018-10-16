package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.controleurs.ControleurObservation;
import ca.cours5b5.williamsarrazin.controleurs.interfaces.ListenerObservateur;
import ca.cours5b5.williamsarrazin.modeles.MParametres;
import ca.cours5b5.williamsarrazin.modeles.Modele;


public class VParametres extends Vue {

    static{

        Log.d("Atelier04", VParametres.class.getSimpleName() + "::static");

    }

    private Spinner spinnerHauteur;
    private Spinner spinnerLargeur;
    private Spinner spinnerPourGagner;

    public VParametres(Context context) {
        super(context);
    }

    public VParametres(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VParametres(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        initialiser();

        afficherLesChoix();

    }

    private void initialiser(){
        spinnerHauteur = this.findViewById(R.id.spinner_hauteur);
        spinnerLargeur = this.findViewById(R.id.spinner_largeur);
        spinnerPourGagner = this.findViewById(R.id.spinner_pour_gagner);

        initialiserSpinner(spinnerHauteur);
        initialiserSpinner(spinnerLargeur);
        initialiserSpinner(spinnerPourGagner);

        installerListeners();
    }

    private void initialiserSpinner(Spinner spinner){
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getContext(), R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void installerListeners() {
        installerListenerHauteur();
        installerListenerLargeur();
        installerListenerPourGagner();
    }

    private void installerListenerHauteur(){
        spinnerHauteur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int leChoix = (int) parent.getAdapter().getItem(position);
                //De la partie
                MParametres.instance.parametresPartie.setHauteur(leChoix);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void installerListenerLargeur(){
        spinnerLargeur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int leChoix = (int) parent.getAdapter().getItem(position);
                //De la partie
                MParametres.instance.parametresPartie.setLargeur(leChoix);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void installerListenerPourGagner(){
        spinnerPourGagner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int leChoix = (int) parent.getAdapter().getItem(position);
                //De la partie
                MParametres.instance.parametresPartie.setPourGagner(leChoix);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void afficherLesChoix(){
        afficherChoixHauteur();
        afficherChoixLargeur();
        afficherChoixPourGagner();
    }

    private void afficherChoixHauteur(){
        mettreAJourSpinner(spinnerHauteur,
                MParametres.instance.getChoixHauteur(),
                //De la partie
                MParametres.instance.parametresPartie.getHauteur());
    }

    private void afficherChoixLargeur(){
        mettreAJourSpinner(spinnerLargeur,
                MParametres.instance.getChoixLargeur(),
                //De la partie
                MParametres.instance.parametresPartie.getLargeur());
    }

    private void afficherChoixPourGagner(){
        mettreAJourSpinner(spinnerPourGagner,
                MParametres.instance.getChoixPourGagner(),
                //De la partie
                MParametres.instance.parametresPartie.getPourGagner());
    }

    private void mettreAJourSpinner(Spinner spinner, List<Integer> choix, int selectionCourante){
        ArrayAdapter<Integer> adapter = (ArrayAdapter<Integer>) spinner.getAdapter();
        adapter.clear();

        for(int i=0; i < choix.size(); i++){
            int leChoix = choix.get(i);
            adapter.add(leChoix);

            if(leChoix == selectionCourante){
                spinner.setSelection(i);
            }
        }
    }
}
