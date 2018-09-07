package ca.cours5b5.williamsarrazin.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.cours5b5.williamsarrazin.R;
import ca.cours5b5.williamsarrazin.modeles.MParametres;

public class VParametres extends Vue {

    static{
        Log.d("Atelier04",  VParametres.class + "::static");
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

        MParametres test = new MParametres();
        
        ArrayAdapter<Integer> adapterHauteur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, MParametres.instance.getChoixHauteur());
        spinnerHauteur.setAdapter(adapterHauteur);
        spinnerHauteur.setSelection(MParametres.instance.getHauteur());

        ArrayAdapter<Integer> adapterLargeur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, MParametres.instance.getChoixLargeur());
        spinnerLargeur.setAdapter(adapterLargeur);
        spinnerLargeur.setSelection(MParametres.instance.getLargeur());

        ArrayAdapter<Integer> adapterPourGagner = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item, MParametres.instance.getChoixPourGagner());
        spinnerPourGagner.setAdapter(adapterPourGagner);
        spinnerPourGagner.setSelection(MParametres.instance.getPourGagner());

        /*
        //Hauteur
        spinnerHauteur = this.findViewById(R.id.spinnerHauteur);

        ArrayAdapter<String> adapterHauteur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);

        spinnerHauteur.setAdapter(adapterHauteur);

        for (int i = GConstantes.HAUTEUR_MIN; i <= GConstantes.HAUTEUR_MAX; i++) {
            adapterHauteur.add(i + "");
        }



        spinnerHauteur.setSelection(2);

        //Largeur
        spinnerLargeur = this.findViewById(R.id.spinnerLargeur);

        ArrayAdapter<String> adapterLargeur = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);

        spinnerLargeur.setAdapter(adapterLargeur);

        for (int i = GConstantes.LARGEUR_MIN; i <= GConstantes.LARGEUR_MAX; i++) {
            adapterLargeur.add(i + "");
        }

        spinnerLargeur.setSelection(3);

        //PourGagner
        spinnerPourGagner = this.findViewById(R.id.spinnerPourGagner);

        ArrayAdapter<String> adapterPourGagner = new ArrayAdapter<>(this.getContext(), R.layout.support_simple_spinner_dropdown_item);

        spinnerPourGagner.setAdapter(adapterPourGagner);

        for (int i = GConstantes.GAGNER_MIN; i <= GConstantes.GAGNER_MAX; i++) {
            adapterPourGagner.add(i + "");
        }

        spinnerPourGagner.setSelection(1);
        */
    }

}
