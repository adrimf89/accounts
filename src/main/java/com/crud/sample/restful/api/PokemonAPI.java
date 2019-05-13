package com.crud.sample.restful.api;

import com.crud.sample.restful.service.PokemonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pokemon")
@Slf4j
@RequiredArgsConstructor
public class PokemonAPI {
    private final PokemonService pokemonService;

    @GetMapping("/{name}")
    public ResponseEntity getByName(@PathVariable String name){
        return ResponseEntity.ok(pokemonService.findByName(name));
    }
}
