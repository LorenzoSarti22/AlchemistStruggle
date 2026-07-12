package it.unicam.cs.mpgc.rpg118664.persistence;

import it.unicam.cs.mpgc.rpg118664.model.Game;
import it.unicam.cs.mpgc.rpg118664.model.characters.Alchemist;
import it.unicam.cs.mpgc.rpg118664.model.characters.Slime;
import it.unicam.cs.mpgc.rpg118664.model.items.AlchemicalIngredient;
import it.unicam.cs.mpgc.rpg118664.model.items.Item;
import it.unicam.cs.mpgc.rpg118664.model.items.Potion;
import it.unicam.cs.mpgc.rpg118664.model.items.PotionType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class GameRepositoryTest {

    private GameRepository repository;
    private static final String TEST_FILE = "test_savegame.json";

    @BeforeEach
    public void setUp() {
        repository = new GameRepository();
    }

    @AfterEach
    public void cleanUp() {
        new File(TEST_FILE).delete();
    }

    @Test
    public void testSaveAndLoadGamePreservesState() {
        Alchemist alchemist = new Alchemist("Nicolas", 100, 18, 5);
        alchemist.addItem(new AlchemicalIngredient("Gelatina", "Sostanza viscosa"));
        alchemist.addItem(new Potion(PotionType.HEALTH_POTION));
        alchemist.boostStats(10, 3, 1);

        Slime monster = new Slime("Slime Rosso", 50, 8, 2);
        monster.setHp(30);

        Game original = new Game(alchemist, monster);

        repository.saveGame(original, TEST_FILE);

        Game loaded = repository.loadGame(TEST_FILE);

        assertEquals(alchemist.getName(), loaded.getAlchemist().getName());
        assertEquals(alchemist.getHpMax(), loaded.getAlchemist().getHpMax());
        assertEquals(2, loaded.getAlchemist().getInventory().size());

        Item firstItem = loaded.getAlchemist().getInventory().get(0);
        Item secondItem = loaded.getAlchemist().getInventory().get(1);
        assertInstanceOf(AlchemicalIngredient.class, firstItem);
        assertInstanceOf(Potion.class, secondItem);
        assertEquals("Gelatina", firstItem.getName());
        assertEquals(PotionType.HEALTH_POTION, ((Potion) secondItem).getType());

        assertInstanceOf(Slime.class, loaded.getCurrentMonster());
        assertEquals("Slime Rosso", loaded.getCurrentMonster().getName());
        assertEquals(30, loaded.getCurrentMonster().getHp());
        assertEquals(50, loaded.getCurrentMonster().getHpMax());
    }
}