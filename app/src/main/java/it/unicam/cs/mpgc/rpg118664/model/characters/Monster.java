package it.unicam.cs.mpgc.rpg118664.model.characters;

import it.unicam.cs.mpgc.rpg118664.model.items.Item;

public abstract class Monster extends GameCharacter {
    public Monster(String name, int hpMax, int attack, int defense) {
        super(name, hpMax, attack, defense);
    }

    public abstract Item getDrop();
}
