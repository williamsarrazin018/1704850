package ca.cours5b5.williamsarrazin.serialisation;

import ca.cours5b5.williamsarrazin.exceptions.ErreurDeConstruction;

public class Construction {

    public static <T extends  Constructible> T construire(Class<T> classeAInstancier) throws ErreurDeConstruction{

    }

    private static  <T extends  Constructible> T finaliserConstruction(Class<T> classeAInstancier, Object obj){

    }

}
