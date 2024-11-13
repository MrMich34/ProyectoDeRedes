package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Pokemon {
    String nombre;
    List<String> movimientos;
    List<String> estadisticas;

    public Pokemon() {
        nombre = "";
        movimientos = new ArrayList<String>();
        estadisticas = new ArrayList<String>();
    }

    public void getPokemon(int numPokedex){

        try {
            // URL de la API para obtener información de Ditto
            String apiUrl = "https://pokeapi.co/api/v2/pokemon/"+numPokedex;
            URL url = new URL(apiUrl);

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



            // Parsear JSON
            JSONObject json = new JSONObject(sb.toString());
            JSONArray movesArray = json.getJSONArray("moves");
            String nombre = json.getString("name");
            this.nombre = nombre;
            // Extraer movimientos
            for (int i = movesArray.length()/4; i < movesArray.length(); i =  i+ movesArray.length()/4) {
                JSONObject move = movesArray.getJSONObject(i);
                String moveName = move.getJSONObject("move").getString("name");
                movimientos.add(moveName);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<String> getEstadisticas(){

        return estadisticas;
    }
    public List<String> getMovimientos(){

        return movimientos;
    }
    public String getNombre(){

        return nombre;
    }

    @Override
    public String toString() {
        return "La cadena con datos es "+ nombre+ "Y los movimientos son: " +movimientos;
    }

}