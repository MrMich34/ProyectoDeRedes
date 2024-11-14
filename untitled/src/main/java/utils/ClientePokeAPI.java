package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientePokeAPI {

    private String apiUrl = "https://pokeapi.co/api/v2/";


    public ClientePokeAPI() {

    }

    public JSONObject conseguirPokemon(int numeroPokedex){
        JSONObject json = null;
        try {

            URL url = new URL(apiUrl+"pokemon/"+numeroPokedex);

            // Abrir conexión
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Verificar respuesta
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            // Leer respuesta
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            json = new JSONObject(sb.toString());
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    public JSONObject conseguirMovimiento(int numeroMovimiento){
        JSONObject json = null;
        try {

            URL url = new URL(apiUrl+"move/"+numeroMovimiento);

            // Abrir conexión
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Verificar respuesta
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            // Leer respuesta
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            //System.out.println("Contenido del JSON en String: " + sb.toString());
            json = new JSONObject(sb.toString());
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
}
