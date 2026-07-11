package it.unicam.cs.mpgc.rpg118664.model;

import it.unicam.cs.mpgc.rpg118664.model.characters.Alchemist;
import it.unicam.cs.mpgc.rpg118664.model.characters.Monster;
import it.unicam.cs.mpgc.rpg118664.model.characters.Slime;
import it.unicam.cs.mpgc.rpg118664.model.characters.Fairy;
import it.unicam.cs.mpgc.rpg118664.model.characters.Golem;
import it.unicam.cs.mpgc.rpg118664.model.items.AlchemicalIngredient;
import it.unicam.cs.mpgc.rpg118664.persistence.StaticDataRepository;
import it.unicam.cs.mpgc.rpg118664.persistence.StaticDataRepository.MonsterData;

import java.util.List;

public class GameFactory {

    public Game createNewGame(String alchemistName) {
        Alchemist alchemist = new Alchemist(alchemistName, 100, 18, 5);

        alchemist.addItem(new AlchemicalIngredient("Gelatina", "Una sostanza viscosa e traslucida rilasciata da uno Slime."));
        alchemist.addItem(new AlchemicalIngredient("Polvere Fatata", "Una polvere fine e luminescente rilasciata da una fata."));
        alchemist.addItem(new AlchemicalIngredient("Cristallo", "Un frammento di roccia cristallina infusa di energia magica rilasciata da un Golem."));

        Monster firstMonster = createRandomMonster();

        return new Game(alchemist, firstMonster);
    }

    public Monster createRandomMonster() {
        StaticDataRepository staticDataRepository = new StaticDataRepository();
        List<MonsterData> monsters = staticDataRepository.loadMonsters();

        int totalWeight = monsters.stream().mapToInt(MonsterData::spawnWeight).sum();
        int roll = (int) (Math.random() * totalWeight);

        int cumulative = 0;
        for (MonsterData data : monsters) {
            cumulative += data.spawnWeight();
            if (roll < cumulative) {
                return createMonsterFromData(data);
            }
        }
        return createMonsterFromData(monsters.get(monsters.size() - 1));
    }

    private Monster createMonsterFromData(MonsterData data) {
        return switch (data.type()) {
            case "SLIME" -> new Slime(data.name(), data.hpMax(), data.attack(), data.defense());
            case "GOLEM" -> new Golem(data.name(), data.hpMax(), data.attack(), data.defense());
            case "FAIRY" -> new Fairy(data.name(), data.hpMax(), data.attack(), data.defense());
            default -> throw new IllegalStateException("Tipo di mostro sconosciuto in monsters.xml: " + data.type());
        };
    }
}