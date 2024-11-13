package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ManejadorArchivos {
    public static void convertidorJSON(List<Pokemon> p1){
        String fileName = "salida.json";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(fileName), p1);
            System.out.println("JSON file created: " + fileName);
        } catch (IOException e ){
            e.printStackTrace();
        }
    }
}
