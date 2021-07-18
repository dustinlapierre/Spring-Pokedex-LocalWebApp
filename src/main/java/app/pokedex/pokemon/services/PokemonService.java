package app.pokedex.pokemon.services;

import app.pokedex.pokemon.model.Pokemon;
import app.pokedex.pokemon.repos.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private final PokemonRepo pokemonRepo;

    //Spring will inject the dependency
    @Autowired
    public PokemonService(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    //Returns a list of all pokemon in the H2 database
    //no endpoint selected
    public ResponseEntity<List<Pokemon>> getPokemon(){
        List<Pokemon> pokemon = new ArrayList<>();
        pokemonRepo.findAll().forEach(a -> pokemon.add(a));
        return new ResponseEntity<List<Pokemon>>(pokemon, HttpStatus.OK);
    }

    //returns a list of only the matching pokemon
    //endpoint selected
    public ResponseEntity<List<Pokemon>> getPokemonById(Long endpoint){
        List<Pokemon> pokemon = new ArrayList<>();
        Optional<Pokemon> optionalPK = pokemonRepo.findById(endpoint);
        if(optionalPK.isPresent()) {
            pokemon.add(optionalPK.get());
            return new ResponseEntity<List<Pokemon>>(pokemon, HttpStatus.OK);
        }
        else{
            System.out.println("Invalid Request!");
            return new ResponseEntity<List<Pokemon>>(HttpStatus.BAD_REQUEST);
        }
    }
}
