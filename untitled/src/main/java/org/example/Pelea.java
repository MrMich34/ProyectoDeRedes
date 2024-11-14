package org.example;

public class Pelea {

    Pokemon pokemon1;
    Pokemon pokemon2;

    public Pelea(){
        pokemon1 = new Pokemon();
        pokemon2 = new Pokemon();

    }

    public void anadirNombre1(String nombrePokemon){

        pokemon1.setNombre(nombrePokemon);

    }

    public void anadirNombre2(String nombrePokemon){

        pokemon2.setNombre(nombrePokemon);
    }


    public Pokemon getPokemon1(){
        System.out.println(pokemon1);
        return pokemon1;
    }
    public Pokemon getPokemon2(){
        System.out.println(pokemon2);
        return pokemon2;
    }



}
