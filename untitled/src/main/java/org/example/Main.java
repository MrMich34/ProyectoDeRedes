package org.example;


import org.json.JSONObject;
import utils.ManejadorArchivos;
import utils.ClientePokeAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final ClientePokeAPI api = new ClientePokeAPI();


    public static void main(String[] args){

        Random random = new Random();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

        Runnable generarAleatorio = () -> {


            Pelea pelea = new Pelea();


            int numeroPokedex1 = random.nextInt(1025);
            int numeroPokedex2 = random.nextInt(1025);


            JSONObject jsonPoke1 = api.conseguirPokemon(numeroPokedex1);
            JSONObject jsonPoke2 = api.conseguirPokemon(numeroPokedex2);


            String nombrePoke1 = ManejadorArchivos.jsonCrearPokemon(jsonPoke1);
            String nombrePoke2 = ManejadorArchivos.jsonCrearPokemon(jsonPoke2);


            int numeroMovimientos = ManejadorArchivos.jsonNumeroDeMovimientos(jsonPoke1);
            int numeroMovimientos2 = ManejadorArchivos.jsonNumeroDeMovimientos(jsonPoke2);


            List<Integer> index1 = new ArrayList<>();
            for(int i = 0; i < numeroMovimientos; i++){
                index1.add(random.nextInt(numeroMovimientos));
            }

            List<Integer> index2 = new ArrayList<>();
            for(int i = 0; i < numeroMovimientos; i++){
                index2.add(random.nextInt(numeroMovimientos));
            }
            /**
             * JSONObject movimiento = api.conseguirMovimiento(index1.get(0));
             *
             *             for(String s :  ManejadorArchivos.jsonMovimientos(movimiento)){
             *                 System.out.println(s);
             *             }
             */


            System.out.println(numeroMovimientos);
            System.out.println(numeroMovimientos2);





            pelea.anadirNombre1(nombrePoke1);
            pelea.anadirNombre2(nombrePoke2);





            System.out.println("Ejecutado en " + System.currentTimeMillis());



        };
        executorService.scheduleAtFixedRate(generarAleatorio, 0, 5, TimeUnit.SECONDS);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Deteniendo el servicio de ejecuciones programadas.");
            executorService.shutdown();
        }));

    }

}
