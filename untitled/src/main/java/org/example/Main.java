package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static Pelea pelea = new Pelea();
    public static void main(String[] args){
        Random random = new Random();
        int r1 = random.nextInt(1025);
        int r2 = random.nextInt(1025);
        System.out.println("El primer numero es: "+r1+" y el esgundo es: "+r2);
        Pokemon pokemon1 = new Pokemon();
        Pokemon pokemon2 = new Pokemon();
        pokemon1.getPokemon(r1);
        System.out.println("Los datos del pokemon son 1: " + pokemon1);
        pokemones.add(pokemon1);
        pokemon2.getPokemon(r2);
        System.out.println("Los datos del pokemon son2 : "+ pokemon2);
        pokemones.add(pokemon2);
        ManejadorArchivos.convertidorJSON(pokemones);
    }
}
