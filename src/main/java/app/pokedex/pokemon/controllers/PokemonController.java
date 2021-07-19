package app.pokedex.pokemon.controllers;

import app.pokedex.pokemon.model.Pokemon;
import app.pokedex.pokemon.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PokemonController {

    private final PokemonService pokemonService;

    //Spring will inject the dependency
    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    //HTTP GET Request requiring id param
    @GetMapping
    public ResponseEntity<List<Pokemon>> getPokemon(@RequestParam(value="id") String id,
                                    @RequestParam(value="endpoint", required=false) Long endpoint){
        //validate id parameter
        //return code 400 if id is blank
        if(id.equals("")){
            System.out.println("Invalid Request!");
            return new ResponseEntity<List<Pokemon>>(HttpStatus.BAD_REQUEST);
        }

        //service request based on optional endpoint param
        if(endpoint == null)
            return pokemonService.getPokemon();
        else
            return pokemonService.getPokemonById(endpoint);
    }

}
