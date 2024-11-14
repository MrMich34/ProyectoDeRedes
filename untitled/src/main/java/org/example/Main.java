package org.example;


import org.json.JSONArray;
import org.json.JSONObject;
import utils.ManejadorArchivos;
import utils.ClientePokeAPI;

import java.awt.desktop.SystemSleepEvent;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
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


            List<Integer> numeroMovimientosPokemon1 = new ArrayList<>();
            List<Integer> numeroMovimientosPokemon2 = new ArrayList<>();
            numeroMovimientosPokemon1 = ManejadorArchivos.jsonCualesMovimientos(jsonPoke1);
            numeroMovimientosPokemon2 = ManejadorArchivos.jsonCualesMovimientos(jsonPoke2);


            Collections.shuffle(numeroMovimientosPokemon1);
            Collections.shuffle(numeroMovimientosPokemon2);

            int numeroMovimientos = ManejadorArchivos.jsonNumeroDeMovimientos(jsonPoke1);
            int numeroMovimientos2 = ManejadorArchivos.jsonNumeroDeMovimientos(jsonPoke2);

            List<List<String>> cadenasMovimientosPokemon1 = new ArrayList<>();
            List<List<String>> cadenasMovimientosPokemon2 = new ArrayList<>();

            if(numeroMovimientos >= 4){
                for(int i = 0; i < 4; i++){
                    JSONObject objetoMovimiento = api.conseguirMovimiento(numeroMovimientosPokemon1.get(i));
                    cadenasMovimientosPokemon1.add(ManejadorArchivos.jsonMovimientos(objetoMovimiento));
                }
            }else{
                for (Integer integer : numeroMovimientosPokemon1) {
                    JSONObject objetoMovimiento = api.conseguirMovimiento(integer);
                    cadenasMovimientosPokemon1.add(ManejadorArchivos.jsonMovimientos(objetoMovimiento));
                }
            }

            if(numeroMovimientos2 >= 4){
                for(int i = 0; i < 4; i++){
                    JSONObject objetoMovimiento = api.conseguirMovimiento(numeroMovimientosPokemon2.get(i));
                    cadenasMovimientosPokemon2.add(ManejadorArchivos.jsonMovimientos(objetoMovimiento));
                }
            }else{
                for (Integer integer : numeroMovimientosPokemon2) {
                    JSONObject objetoMovimiento = api.conseguirMovimiento(integer);
                    cadenasMovimientosPokemon2.add(ManejadorArchivos.jsonMovimientos(objetoMovimiento));
                }
            }

            System.out.println("Hasta aca está bien");

            String imagenPokemon1 = ManejadorArchivos.jsonImagen1(jsonPoke1);
            String imagenPokemon2 = ManejadorArchivos.jsonImagen2(jsonPoke2);
            List<Integer> estadisticas1 = ManejadorArchivos.jsonEstadisticas(jsonPoke1);
            List<Integer> estadisticas2 = ManejadorArchivos.jsonEstadisticas(jsonPoke1);

            /**
            System.out.println("El pokemon 1 es: " + nombrePoke1);
            System.out.println("El pokemon 2 es: " + nombrePoke2);
            System.out.println("lA CADENA DE MOVIVMIENTOS UNO ES:" + cadenasMovimientosPokemon1);
            System.out.println("lA CADENA DE MOVIVMIENTOS DOS ES:" + cadenasMovimientosPokemon2);
            System.out.println("La imagen del primero es :" + imagenPokemon1);
            System.out.println("La imagen del segundo es :" + imagenPokemon2);
            System.out.println(estadisticas1);
            System.out.println(estadisticas2);
            */
            System.out.println("Se ingresa nombre 1");
            pelea.anadirNombre1(nombrePoke1);
            System.out.println("Se ingresa nombre 2");
            pelea.anadirNombre2(nombrePoke2);
            System.out.println("Se ingresa imagen 1");
            pelea.anadirImagen1(imagenPokemon1);
            System.out.println("Se ingresa imagen 2");
            pelea.anadirImagen2(imagenPokemon2);
            System.out.println("Se ingresa estadisticas 1");
            pelea.anadirEstadisticas1(estadisticas1);
            System.out.println("Se ingresa estadisticas 2");
            pelea.anadirEstadisticas2(estadisticas2);
            System.out.println("Se ingresa movimientos 1");
            pelea.anadirMovimientos1(cadenasMovimientosPokemon1);
            System.out.println("Se ingresa movimientos 2");
            pelea.anadirMovimientos2(cadenasMovimientosPokemon2);
            System.out.println(pelea);

            try {
                ManejadorArchivos.jsonEscribirTodo(pelea);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println("Ejecutado en " + System.currentTimeMillis());



        };
        executorService.scheduleAtFixedRate(generarAleatorio, 0, 5, TimeUnit.MINUTES);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Deteniendo el servicio de ejecuciones programadas.");
            executorService.shutdown();
        }));

    }

}
