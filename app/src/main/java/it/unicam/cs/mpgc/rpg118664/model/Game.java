package it.unicam.cs.mpgc.rpg118664.model;
 
import it.unicam.cs.mpgc.rpg118664.model.characters.Alchemist;
import it.unicam.cs.mpgc.rpg118664.model.characters.Monster;
import it.unicam.cs.mpgc.rpg118664.model.items.Item;
 
public class Game {
    private final Alchemist alchemist;
    private Monster currentMonster;
 
    public Game(Alchemist alchemist, Monster currentMonster) {
        this.alchemist = alchemist;
        this.currentMonster = currentMonster;
    }
 
    public String executeTurn() {
        if (!alchemist.isAlive()) {
            return "Sei esausto! Non puoi combattere.";
        }
        int monsterHpBefore = currentMonster.getHp();
        alchemist.attack(currentMonster); 
        int damageToMonster = monsterHpBefore - currentMonster.getHp();
 
        StringBuilder log = new StringBuilder();
        log.append(String.format("%s attacca %s infliggendo %d danni!\n", 
                alchemist.getName(), currentMonster.getName(), damageToMonster));
 
        if (!currentMonster.isAlive()) {
            Item drop = currentMonster.getDrop();
            alchemist.addItem(drop);
 
            String deadMonsterName = currentMonster.getName();
            this.currentMonster = new GameFactory().createRandomMonster();
 
            log.append(deadMonsterName + " è stato sconfitto!\n");
            log.append("Hai ottenuto: " + drop.getName() + "!\n");
            log.append("È apparso un nuovo nemico: " + currentMonster.getName() + "!\n");
            
            return log.toString();
        }
 
        // Se il mostro è ancora vivo contrattacca
        int alchemistHpBefore = alchemist.getHp();
        currentMonster.attack(alchemist);
        int damageToAlchemist = alchemistHpBefore - alchemist.getHp();
 
        log.append(String.format("%s contrattacca e infligge %d danni a %s!\n", 
                currentMonster.getName(), damageToAlchemist, alchemist.getName()));
 
        if (!alchemist.isAlive()) {
            log.append("Sei stato sconfitto in battaglia! Game Over.");
        }
 
        return log.toString();
    }
 
    public Alchemist getAlchemist() {
        return alchemist;
    }
 
    public Monster getCurrentMonster() {
        return currentMonster;
    }
 
}