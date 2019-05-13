package com.crud.sample.restful.service;

import lombok.RequiredArgsConstructor;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class PokemonService {
    private final PokeApiClient pokeApiClient;
    private List<Pokemon> pokedex;

    @PostConstruct
    public void initPokedex(){
        pokedex = IntStream.rangeClosed(1, 150)
                .mapToObj(i -> pokeApiClient.getPokemon(i))
                .collect(Collectors.toList());
    }

    /**
     * Find pokemon that start by name
     * @param name
     * @return
     */
    public List<Pokemon> findByName(String name){
        return pokedex.stream()
                .filter(pokemon -> pokemon.getName().startsWith(name))
                .collect(Collectors.toList());
    }
}
