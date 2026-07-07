package it.unicam.cs.mpgc.rpg118664.model.characters;

import it.unicam.cs.mpgc.rpg118664.model.items.Item;
import it.unicam.cs.mpgc.rpg118664.model.items.AlchemicalIngredient;

public class Slime extends Monster {
    public Slime(String name, int hpMax, int attack, int defense) {
        super(name, hpMax, attack, defense);
    }

    @Override
    public Item getDrop() {
        return new AlchemicalIngredient("Gelatina", "Una sostanza viscosa e traslucida rilasciata da uno Slime.");
    }
}
