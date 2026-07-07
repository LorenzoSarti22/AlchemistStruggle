package it.unicam.cs.mpgc.rpg118664.model.items;

public enum PotionType {
    HEALTH_POTION("Pozione di Vita", "Aumenta permanentemente i tuoi HP massimi", 40, 0, 0),
    STRENGTH_POTION("Pozione della Forza", "Aumenta permanentemente il tuo attacco", 0, 15, 0),
    IRON_POTION("Pozione di Ferro", "Aumenta permanentemente la tua difesa", 0, 0, 10);

    private final String name;
    private final String description;
    private final int hpMaxBonus;
    private final int attackBonus;
    private final int defenseBonus;

    PotionType(String name, String description, int hpMaxBonus, int attackBonus, int defenseBonus) {
        this.name = name;
        this.description = description;
        this.hpMaxBonus = hpMaxBonus;
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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
}