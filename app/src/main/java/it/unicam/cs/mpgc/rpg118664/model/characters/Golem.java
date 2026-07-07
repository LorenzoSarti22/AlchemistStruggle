package it.unicam.cs.mpgc.rpg118664.model.characters;

import it.unicam.cs.mpgc.rpg118664.model.items.Item;
import it.unicam.cs.mpgc.rpg118664.model.items.AlchemicalIngredient;

public class Golem extends Monster {
    public Golem(String name, int hpMax, int attack, int defense) {
        super(name, hpMax, attack, defense);
    }

    @Override
    public Item getDrop() {
        return new AlchemicalIngredient("Cristallo", "Un frammento di roccia cristallina infusa di energia magica rilasciata da un Golem.");
    }
}
