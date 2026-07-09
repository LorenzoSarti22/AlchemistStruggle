package it.unicam.cs.mpgc.rpg118664.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg118664.model.Game;
import it.unicam.cs.mpgc.rpg118664.model.characters.Monster;
import it.unicam.cs.mpgc.rpg118664.model.items.Item;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameRepository {
    private final Gson gson;

    public GameRepository() {
        this.gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(Item.class, new ItemTypeAdapter())
                .registerTypeHierarchyAdapter(Monster.class, new MonsterTypeAdapter())
                .setPrettyPrinting()
                .create();
    }

    public void saveGame(final Game game, final String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            this.gson.toJson(game, writer);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save the game data", e);
        }
    }

    public Game loadGame(final String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            return this.gson.fromJson(reader, Game.class);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load the game data", e);
        }
    }
}