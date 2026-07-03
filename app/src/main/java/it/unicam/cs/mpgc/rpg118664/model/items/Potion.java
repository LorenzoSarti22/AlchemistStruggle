package it.unicam.cs.mpgc.rpg118664.model.items;

import it.unicam.cs.mpgc.rpg118664.model.characters.Alchemist;

public class Potion extends Item implements Usable {
    private final int hpMaxBonus;
    private final int attackBonus;
    private final int defenseBonus;

    public Potion(String name, String description, int hpMaxBonus, int attackBonus, int defenseBonus) {
        super(name, description);
        this.hpMaxBonus = hpMaxBonus;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
    }

    public int getHpMaxBonus() {
        return hpMaxBonus;
    }

    public int getAttackBonus() {
        return attackBonus;
    }

    public int getDefenseBonus() {
        return defenseBonus;
    }

    @Override
    public void use(Alchemist alchemist) {
        alchemist.boostStats(this.hpMaxBonus, this.attackBonus, this.defenseBonus);
    }
}
