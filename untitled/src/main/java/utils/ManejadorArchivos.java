package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.example.Pelea;
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

    public static List<Integer> jsonCualesMovimientos(JSONObject objetoJSON){
        List<Integer> in = new ArrayList<>();


        // Extraer el arreglo "moves"
        JSONArray movesArray = objetoJSON.getJSONArray("moves");

        // Recorrer cada movimiento en el arreglo "moves"
        for (int i = 0; i < movesArray.length(); i++) {
            JSONObject moveObject = movesArray.getJSONObject(i).getJSONObject("move");
            String moveUrl = moveObject.getString("url");

            // Extraer el ID de la URL
            String[] urlParts = moveUrl.split("/");
            String moveId = urlParts[urlParts.length - 1];

            in.add(Integer.parseInt(moveId));

        }
        System.out.println(in);
        return in;

    }

        public static List<Integer> jsonEstadisticas(JSONObject objetoJSON){
        List<Integer> estadisticas = new ArrayList<>();

        JSONArray statsArray = objetoJSON.getJSONArray("stats");
        for (int i = 0; i < statsArray.length(); i++) {
            JSONObject statObject = statsArray.getJSONObject(i);
            int baseStat = statObject.getInt("base_stat");
            estadisticas.add(baseStat);
        }

        return estadisticas;
    }


    public static List<String> jsonMovimientos(JSONObject objetoJSON){
        List<String> movimiento = new ArrayList<>();
        String s;
        int x;
        movimiento.add(objetoJSON.getString("name"));
        if(objetoJSON.isNull("accuracy")){
            movimiento.add(String.valueOf(-2));
        }else{
            movimiento.add(String.valueOf(objetoJSON.getInt("accuracy")));
        }
        if(objetoJSON.isNull("power")){
            movimiento.add(String.valueOf(-2));
        }else{
            movimiento.add(String.valueOf(objetoJSON.getInt("power")));
        }
        if(objetoJSON.isNull("pp")){
            movimiento.add(String.valueOf(-2));
        }else{
            movimiento.add(String.valueOf(objetoJSON.getInt("pp")));
        }
        if(objetoJSON.isNull("priority")){
            movimiento.add(String.valueOf(-2));
        }else{
            movimiento.add(String.valueOf(objetoJSON.getInt("priority")));
        }
        JSONObject js = objetoJSON.getJSONObject("type");
        movimiento.add(js.getString("name"));
        js = objetoJSON.getJSONObject("damage_class");
        movimiento.add(js.getString("name"));
        return movimiento;

    }

    public static String jsonImagen1(JSONObject objetoJSON){

        JSONObject spritesObject = objetoJSON.getJSONObject("sprites");
        if(spritesObject.getJSONObject("other").isNull("showdown")){
            return spritesObject.getString("back_default");
        }else{
            JSONObject showdownObject = spritesObject.getJSONObject("other").getJSONObject("showdown");
            return showdownObject.getString("back_default");
        }

    }

    public static String jsonImagen2(JSONObject objetoJSON){

        JSONObject spritesObject = objetoJSON.getJSONObject("sprites");
        if(spritesObject.getJSONObject("other").isNull("showdown")){
            return spritesObject.getString("front_default");
        }else{
            JSONObject showdownObject = spritesObject.getJSONObject("other").getJSONObject("showdown");
            return showdownObject.getString("front_default");
        }
    }

    public static String obtenerTipo1(JSONObject objetoJSON){
        JSONArray typesArray = objetoJSON.getJSONArray("types");
        System.out.println(typesArray);
        JSONObject typeObject = typesArray.getJSONObject(0).getJSONObject("type");
        System.out.println(typeObject);
        return typeObject.getString("name");

    }

    public static String obtenerTipo2(JSONObject objetoJSON){
        JSONArray typesArray = objetoJSON.getJSONArray("types");
        System.out.println(typesArray);
        JSONObject typeObject = typesArray.getJSONObject(0).getJSONObject("type");
        System.out.println(typeObject);
        return typeObject.getString("name");

    }

    public static void jsonEscribirTodo(Pelea p) throws IOException, MqttException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonString = mapper.writeValueAsString(p);

        String broker = "tcp://localhost:1883";
        String clientId = "JavaSample";
        MqttClient sampleClient = new MqttClient(broker, clientId);
        sampleClient.connect();
        MqttMessage message = new MqttMessage(jsonString.getBytes());
        message.setQos(2); sampleClient.publish("pokemon/pelea", message);
        sampleClient.disconnect();


    }


}
