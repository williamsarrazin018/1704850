package ca.cours5b5.williamsarrazin.modeles;

import java.util.Map;

public abstract class Modele {

    public abstract void aPartirObjetJson(Map<String, Object> enObjetJson);

    public abstract Map<String, Object> enObjetJson();

}
