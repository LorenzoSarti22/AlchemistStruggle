package it.unicam.cs.mpgc.rpg118664.persistence;

import com.google.gson.*;
import it.unicam.cs.mpgc.rpg118664.model.characters.Monster;
import it.unicam.cs.mpgc.rpg118664.model.characters.Slime;
import it.unicam.cs.mpgc.rpg118664.model.characters.Golem;
import it.unicam.cs.mpgc.rpg118664.model.characters.Fairy;
import java.lang.reflect.Type;

public class MonsterTypeAdapter implements JsonSerializer<Monster>, JsonDeserializer<Monster> {

    @Override
    public JsonElement serialize(
            final Monster src,
            final Type typeOfSrc,
            final JsonSerializationContext context
    ) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", src.getName());
        jsonObject.addProperty("hp", src.getHp());
        jsonObject.addProperty("hpMax", src.getHpMax());
        jsonObject.addProperty("attack", src.getAttack());
        jsonObject.addProperty("defense", src.getDefense());

        if (src instanceof Slime) {
            jsonObject.addProperty("type", "SLIME");
        } else if (src instanceof Golem) {
            jsonObject.addProperty("type", "GOLEM");
        } else if (src instanceof Fairy) {
            jsonObject.addProperty("type", "FAIRY");
        }

        return jsonObject;
    }

    @Override
    public Monster deserialize(
            final JsonElement json,
            final Type typeOfT,
            final JsonDeserializationContext context
    ) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        if (!jsonObject.has("type") || jsonObject.get("type").isJsonNull()) {
            throw new JsonParseException("Campo 'type' mancante nel JSON del mostro.");
        }

        final String monsterType = jsonObject.get("type").getAsString();
        final String name = jsonObject.get("name").getAsString();
        final int hpMax = jsonObject.get("hpMax").getAsInt();
        final int attack = jsonObject.get("attack").getAsInt();
        final int defense = jsonObject.get("defense").getAsInt();
        final int hp = jsonObject.get("hp").getAsInt();

        Monster monster = switch (monsterType) {
            case "SLIME" -> new Slime(name, hpMax, attack, defense);
            case "GOLEM" -> new Golem(name, hpMax, attack, defense);
            case "FAIRY" -> new Fairy(name, hpMax, attack, defense);
            default -> throw new JsonParseException("Tipo di mostro sconosciuto: " + monsterType);
        };

        monster.setHp(hp);
        return monster;
    }
}