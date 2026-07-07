package it.unicam.cs.mpgc.rpg118664.model.characters;

import it.unicam.cs.mpgc.rpg118664.model.items.Item;
import it.unicam.cs.mpgc.rpg118664.model.items.AlchemicalIngredient;

public class Fairy extends Monster {
    public Fairy(String name, int hpMax, int attack, int defense) {
        super(name, hpMax, attack, defense);
    }

    @Override
    public Item getDrop() {
        return new AlchemicalIngredient("Polvere Fatata", "Una polvere fine e luminescente rilasciata da una fata.");
    }
}
