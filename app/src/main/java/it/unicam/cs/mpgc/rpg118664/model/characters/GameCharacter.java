package it.unicam.cs.mpgc.rpg118664.model.characters;

public abstract class GameCharacter {
    private final String name;
    private int hp;
    private int hpMax;
    private int attack;
    private int defense;

    public GameCharacter(String name, int hpMax, int attack, int defense) {
        this.name = name;
        this.hpMax = hpMax;
        this.hp = hpMax;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(hp, this.hpMax));
    }

    public int getHpMax() {
        return hpMax;
    }

    protected void setHpMax(int hpMax) {
        this.hpMax = hpMax;
        if (this.hp > hpMax) {
            this.hp = hpMax;
        }
    }

    public void heal(int amount) {
        if (amount > 0) {
            this.setHp(this.getHp() + amount);
        }
    }

    public int getAttack() {
        return attack;
    }

    protected void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    protected void setDefense(int defense) {
        this.defense = defense;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void attack(GameCharacter opponent) {
        int damage = this.attack - opponent.getDefense();
        if (damage < 1) {
            damage = 0;
        }
        opponent.setHp(opponent.getHp() - damage);
    }
}
