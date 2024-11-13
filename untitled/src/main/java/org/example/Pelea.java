package org.example;

public class Pelea {

    Pokemon pokemon1;
    Pokemon pokemon2;

    public Pelea(){
        pokemon1 = new Pokemon();
        pokemon2 = new Pokemon();

    }

    public void getPokemon1(int pokedexNumber){
        pokemon1.getPokemon(pokedexNumber);

    }

    public void getPokemon2(int pokedexNumber){
        pokemon2.getPokemon(pokedexNumber);
    }


}
