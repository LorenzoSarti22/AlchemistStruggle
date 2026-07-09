package it.unicam.cs.mpgc.rpg118664.model.alchemy;

import it.unicam.cs.mpgc.rpg118664.model.characters.Alchemist;
import it.unicam.cs.mpgc.rpg118664.model.items.Item;
import it.unicam.cs.mpgc.rpg118664.model.items.Ingredient; 
import it.unicam.cs.mpgc.rpg118664.model.items.Potion;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Laboratory {

    public boolean canCraft(Alchemist alchemist, Recipe recipe) {
        Map<String, Integer> available = getInventoryCount(alchemist);
        
        for (Map.Entry<String, Integer> entry : recipe.getRequiredIngredients().entrySet()) {
            if (available.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false; 
            }
        }
        return true;
    }

    public boolean craft(Alchemist alchemist, Recipe recipe) {
        if (!canCraft(alchemist, recipe)) {
            return false;
        }

        recipe.getRequiredIngredients().forEach((ingName, quantity) -> 
            consumeIngredient(alchemist, ingName, quantity)
        );

        alchemist.addItem(new Potion(recipe.getResultType()));
        return true;
    }

 private void consumeIngredient(Alchemist alchemist, String ingName, int quantity) {
    List<Item> toRemove = new ArrayList<>(); 
    int consumed = 0;

    for (Item item : alchemist.getInventory()) {
        if (item.getName().equalsIgnoreCase(ingName)) {
            toRemove.add(item); 
            consumed++;        
            if (consumed == quantity) {
                break; 
            }
        }
    }

    for (Item item : toRemove) {
        alchemist.removeItem(item);
    }
}

    private Map<String, Integer> getInventoryCount(Alchemist alchemist) {
        Map<String, Integer> counts = new HashMap<>();
        for (Item item : alchemist.getInventory()) {
            if (item instanceof Ingredient) {
                counts.put(item.getName(), counts.getOrDefault(item.getName(), 0) + 1);
            }
        }
        return counts;
    }
}