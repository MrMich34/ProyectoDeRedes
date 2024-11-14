package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Pokemon;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManejadorArchivos {

    public static String jsonCrearPokemon(JSONObject objetoJSON){

        return objetoJSON.getString("name");
    }

    public static int jsonNumeroDeMovimientos(JSONObject objetoJSON){

        JSONArray movimientosJSON = objetoJSON.getJSONArray("moves");
        return movimientosJSON.length();

    }

    public static List<String> jsonMovimientos(JSONObject objetoJSON){
        List<String> movimiento = new ArrayList<>();
        String s =objetoJSON.getString("name");
        movimiento.add(s);
        s = objetoJSON.getString("accuracy");

        return movimiento;

    }

}
