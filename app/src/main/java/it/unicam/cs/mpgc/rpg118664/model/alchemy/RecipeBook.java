package it.unicam.cs.mpgc.rpg118664.model.alchemy;

import it.unicam.cs.mpgc.rpg118664.model.items.PotionType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class RecipeBook {
    private final List<Recipe> recipes;

    public RecipeBook() {
        this.recipes = new ArrayList<>();
        initRecipes();
    }

    private void initRecipes() {

        recipes.add(new Recipe(
            PotionType.HEALTH_POTION,
            Map.of("Gelatina", 2)
        ));

        recipes.add(new Recipe(
            PotionType.STRENGTH_POTION,
            Map.of("Polvere Fatata", 2)
        ));

        recipes.add(new Recipe(
            PotionType.IRON_POTION,
            Map.of("Cristallo", 2)
        ));
    }

    public List<Recipe> getRecipes() {
        return Collections.unmodifiableList(recipes);
    }
}