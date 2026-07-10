package it.unicam.cs.mpgc.rpg118664.model.items;

import it.unicam.cs.mpgc.rpg118664.persistence.StaticDataRepository;
import it.unicam.cs.mpgc.rpg118664.persistence.StaticDataRepository.PotionData;
import java.util.Map;

public enum PotionType {
    HEALTH_POTION,
    STRENGTH_POTION,
    IRON_POTION;

    private static final Map<String, PotionData> DATA = new StaticDataRepository().loadPotions();

    private PotionData data() {
        PotionData potionData = DATA.get(this.name());
        if (potionData == null) {
            throw new IllegalStateException("Dati mancanti in potions.json per: " + this.name());
        }
        return potionData;
    }

    public String getName() {
        return data().name();
    }

    public String getDescription() {
        return data().description();
    }

    public int getHpMaxBonus() {
        return data().hpMaxBonus();
    }

    public int getAttackBonus() {
        return data().attackBonus();
    }

    public int getDefenseBonus() {
        return data().defenseBonus();
    }

    public Map<String, Integer> getRequiredIngredients() {
        return data().requiredIngredients();
    }
}