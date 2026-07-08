package it.unicam.cs.mpgc.rpg118664.model.alchemy;

import it.unicam.cs.mpgc.rpg118664.model.items.PotionType;
import java.util.Collections;
import java.util.Map;

public class Recipe {
    private final PotionType resultType;
    private final Map<String, Integer> requiredIngredients;

    public Recipe(PotionType resultType, Map<String, Integer> requiredIngredients) {
        this.resultType = resultType;
        this.requiredIngredients = requiredIngredients;
    }

    public PotionType getResultType() {
        return resultType;
    }

    public Map<String, Integer> getRequiredIngredients() {
        return Collections.unmodifiableMap(requiredIngredients);
    }
}
