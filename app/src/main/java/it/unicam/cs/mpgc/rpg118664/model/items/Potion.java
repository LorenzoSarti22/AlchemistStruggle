package it.unicam.cs.mpgc.rpg118664.model.items;

import it.unicam.cs.mpgc.rpg118664.model.characters.Alchemist;

public class Potion extends Item implements Usable {
    private final PotionType type;

    public Potion(PotionType type) {
        super(type.getName(), type.getDescription());
        this.type = type;
    }

    public PotionType getType() {
        return type;
    }

    @Override
    public void use(Alchemist alchemist) {
        alchemist.boostStats(type.getHpMaxBonus(), type.getAttackBonus(), type.getDefenseBonus());
    }
}
