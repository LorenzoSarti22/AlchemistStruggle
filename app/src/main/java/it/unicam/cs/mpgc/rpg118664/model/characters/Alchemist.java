package it.unicam.cs.mpgc.rpg118664.model.characters;

import it.unicam.cs.mpgc.rpg118664.model.items.Item;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Alchemist extends GameCharacter {
    private final List<Item> inventory;

    public Alchemist(String name, int hpMax, int attack, int defense) {
        super(name, hpMax, attack, defense);
        this.inventory = new ArrayList<>();
    }

    public List<Item> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public void addItem(Item item) {
        this.inventory.add(item);
    }

    public boolean removeItem(Item item) {
        return this.inventory.remove(item);
    }

    public void boostStats(int hpMaxBonus, int attackBonus, int defenseBonus) {
        setHpMax(getHpMax() + hpMaxBonus);
        setAttack(getAttack() + attackBonus);
        setDefense(getDefense() + defenseBonus);
    }
}
