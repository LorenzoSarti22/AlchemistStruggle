package it.unicam.cs.mpgc.rpg118664.persistence;

import com.google.gson.*;
import it.unicam.cs.mpgc.rpg118664.model.items.AlchemicalIngredient;
import it.unicam.cs.mpgc.rpg118664.model.items.Item;
import it.unicam.cs.mpgc.rpg118664.model.items.Potion;
import it.unicam.cs.mpgc.rpg118664.model.items.PotionType;
import java.lang.reflect.Type;

public class ItemTypeAdapter implements JsonSerializer<Item>, JsonDeserializer<Item> {

    @Override
    public JsonElement serialize(
            final Item item,
            final Type type,
            final JsonSerializationContext context
    ) {
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", item.getName());
        jsonObject.addProperty("description", item.getDescription());

        if (item instanceof Potion potion) {
            jsonObject.addProperty("type", "POTION");
            jsonObject.addProperty("potionType", potion.getType().name());
        } else if (item instanceof AlchemicalIngredient) {
            jsonObject.addProperty("type", "ALCHEMICAL_INGREDIENT");
        }

        return jsonObject;
    }

    @Override
    public Item deserialize(
            final JsonElement json,
            final Type type,
            final JsonDeserializationContext context
    ) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        if (!jsonObject.has("type") || jsonObject.get("type").isJsonNull()) {
            throw new JsonParseException("Campo 'type' mancante nel JSON dell'item.");
        }

        final String itemType = jsonObject.get("type").getAsString();
        final String name = jsonObject.get("name").getAsString();
        final String description = jsonObject.get("description").getAsString();

        if ("POTION".equals(itemType)) {
            final PotionType potionType = PotionType.valueOf(
                jsonObject.get("potionType").getAsString()
            );
            return new Potion(potionType);
        }

        if ("ALCHEMICAL_INGREDIENT".equals(itemType)) {
            return new AlchemicalIngredient(name, description);
        }

        throw new JsonParseException("Tipo di item sconosciuto: " + itemType);
    }
}