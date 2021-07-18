package app.pokedex.pokemon.repos;

import app.pokedex.pokemon.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepo extends JpaRepository<Pokemon, Long> {
    //providing the interface, Spring data JPA will handle the implementation
}
