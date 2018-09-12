package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;
import java.util.ListIterator;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.modeles.MParametres;

public class VParametres extends Vue {

    static{
        Log.d("Atelier04",  VParametres.class + "::static");
    }

    protected Spinner spinnerHauteur;
    protected Spinner spinnerLargeur;
    protected Spinner spinnerPourGagner;

    public static ArrayAdapter<String> adapterHauteur;


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

        //Hauteur
        spinnerHauteur = this.findViewById(R.id.spinnerHauteur);

        ArrayAdapter<String> adapterHauteur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);

        spinnerHauteur.setAdapter(adapterHauteur);

        List<Integer> listeHauteur = MParametres.instance.getChoixHauteur();
        ListIterator<Integer> iterateurHauteur = listeHauteur.listIterator();

        int cpt=0;

        while (iterateurHauteur.hasNext()) {
            Integer valeur = iterateurHauteur.next();
            adapterHauteur.add(valeur + "");

            if (valeur == MParametres.instance.getHauteur()) {
                spinnerHauteur.setSelection(cpt);
            }

            cpt++;
        }


        //Largeur
        spinnerLargeur = this.findViewById(R.id.spinnerLargeur);

        ArrayAdapter<String> adapterLargeur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);

        spinnerLargeur.setAdapter(adapterLargeur);

        List<Integer> listLargeur = MParametres.instance.getChoixLargeur();
        ListIterator<Integer> iterateurLargeur = listLargeur.listIterator();

        cpt=0;

        while (iterateurLargeur.hasNext()) {
            Integer valeur = iterateurLargeur.next();
            adapterLargeur.add(valeur + "");
            if (valeur == MParametres.instance.getLargeur()) {
                spinnerLargeur.setSelection(cpt);
            }
            cpt++;
        }

        //PourGagner
        spinnerPourGagner = this.findViewById(R.id.spinnerPourGagner);

        ArrayAdapter<String> adapterPourGagner = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);

        spinnerPourGagner.setAdapter(adapterPourGagner);

        List<Integer> listePourGagner = MParametres.instance.getChoixPourGagner();
        ListIterator<Integer> iterateurPourGagner = listePourGagner.listIterator();

        cpt=0;

        while (iterateurPourGagner.hasNext()) {
            Integer valeur = iterateurPourGagner.next();
            adapterPourGagner.add(valeur + "");
            if (valeur == MParametres.instance.getPourGagner()) {
                spinnerPourGagner.setSelection(cpt);
            }
            cpt++;
        }

        spinnerHauteur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choix = (String) parent.getAdapter().getItem(spinnerHauteur.getSelectedItemPosition());
                MParametres.instance.setHauteur(Integer.valueOf(choix));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerLargeur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choix = (String) parent.getAdapter().getItem(spinnerLargeur.getSelectedItemPosition());
                MParametres.instance.setLargeur(Integer.valueOf(choix));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerPourGagner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String choix = (String) parent.getAdapter().getItem(spinnerPourGagner.getSelectedItemPosition());
                MParametres.instance.setPourGagner(Integer.valueOf(choix));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

}
