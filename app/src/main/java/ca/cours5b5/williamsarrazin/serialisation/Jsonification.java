package ca.cours5b5.williamsarrazin.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

public class Jsonification {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static  <T extends  Serialisable> T aPartirJson(Class<T> classeAImplanter, String json){

    }

    public static String enJson(Serialisable obj){

    }

    private static  <T extends  Serialisable> T aPartirJson(Serialisable obj, String json){

    }

    private static <T extends  Serialisable> T aPartirObjetJson(Serialisable obj, Map<String, Object> objetJson){

    }

}
