package it.unicam.cs.mpgc.rpg118664.model.alchemy;

import it.unicam.cs.mpgc.rpg118664.model.characters.Alchemist;
import it.unicam.cs.mpgc.rpg118664.model.items.AlchemicalIngredient;
import it.unicam.cs.mpgc.rpg118664.model.items.Potion;
import it.unicam.cs.mpgc.rpg118664.model.items.PotionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LaboratoryTest {
    private Alchemist alchemist;
    private Laboratory laboratory;

    @BeforeEach
    public void setUp() {
        alchemist = new Alchemist("Nicolas Flamel", 100, 15, 10);
        laboratory = new Laboratory();
    }

    @Test
    public void testCanCraftAndCraftSuccess() {
        PotionType healthPotion = PotionType.HEALTH_POTION;

        assertFalse(laboratory.canCraft(alchemist, healthPotion));
        assertFalse(laboratory.craft(alchemist, healthPotion));

        alchemist.addItem(new AlchemicalIngredient("Gelatina", "Gelatina di slime"));
        assertFalse(laboratory.canCraft(alchemist, healthPotion));

        alchemist.addItem(new AlchemicalIngredient("Gelatina", "Gelatina di slime"));
        assertTrue(laboratory.canCraft(alchemist, healthPotion));

        assertTrue(laboratory.craft(alchemist, healthPotion));

        assertEquals(1, alchemist.getInventory().size());
        assertInstanceOf(Potion.class, alchemist.getInventory().get(0));
        assertEquals(PotionType.HEALTH_POTION,
                ((Potion) alchemist.getInventory().get(0)).getType());
    }

    @Test
    public void testCraftFailsWithWrongIngredients() {
        alchemist.addItem(new AlchemicalIngredient("Cristallo", "Un cristallo grezzo"));
        alchemist.addItem(new AlchemicalIngredient("Cristallo", "Un cristallo grezzo"));

        assertFalse(laboratory.canCraft(alchemist, PotionType.HEALTH_POTION));
        assertFalse(laboratory.craft(alchemist, PotionType.HEALTH_POTION));

        assertEquals(2, alchemist.getInventory().size());
    }
}