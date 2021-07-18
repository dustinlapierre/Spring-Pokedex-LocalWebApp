package app.pokedex.pokemon.init;

import app.pokedex.pokemon.model.Pokemon;
import app.pokedex.pokemon.repos.PokemonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//Detect this as a Spring component
@Component
public class Initialize implements CommandLineRunner {

    private final PokemonRepo pokemonRepo;

    //Spring will use dependency injection to set values
    @Autowired
    public Initialize(PokemonRepo pokemonRepo) {
        this.pokemonRepo = pokemonRepo;
    }

    //This method will run on server start
    @Override
    public void run(String... args) throws Exception {
        //Populate the H2 database with some dummy data
        Pokemon charmander = new Pokemon("Charmander", "Fire");
        Pokemon squirtle = new Pokemon("Squirtle", "Water");
        Pokemon bulbasaur = new Pokemon("Bulbasaur", "Grass/Poison");
        Pokemon pikachu = new Pokemon("Pikachu", "Electric");
        Pokemon haunter = new Pokemon("Haunter", "Ghost/Poison");
        pokemonRepo.save(charmander);
        pokemonRepo.save(squirtle);
        pokemonRepo.save(bulbasaur);
        pokemonRepo.save(pikachu);
        pokemonRepo.save(haunter);

        System.out.println("Data loaded into H2 Database!");

    }

    @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.out.println("Application started ... launching browser now");
        browse("http://localhost:8080");
    }

    public static void browse(String url) {
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
