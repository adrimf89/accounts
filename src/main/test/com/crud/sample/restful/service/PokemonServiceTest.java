package com.crud.sample.restful.service;

import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import me.sargunvohra.lib.pokekotlin.model.Pokemon;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class PokemonServiceTest {

    @InjectMocks
    private PokeApiClient pokeApiClient;

    private PokemonService pokemonService;

    @Before
    public void init(){
        pokemonService = new PokemonService(pokeApiClient);
        pokemonService.initPokedex();
    }

    @Test
    public void testValidName(){
        List<Pokemon> list = pokemonService.findByName("pidg");

        assertThat(list, containsInAnyOrder(
                hasProperty("name", is("pidgey")),
                hasProperty("name", is("pidgeotto")),
                hasProperty("name", is("pidgeot"))
        ));
    }

    @Test
    public void testInvalidName(){
        List<Pokemon> list = pokemonService.findByName("invalid");

        assertThat(list, is(empty()));
    }
}
