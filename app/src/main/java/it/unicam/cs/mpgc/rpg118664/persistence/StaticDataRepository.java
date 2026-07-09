package it.unicam.cs.mpgc.rpg118664.persistence;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticDataRepository {

    public Map<String, PotionData> loadPotions() {
        final InputStream inputStream = this.getClass().getResourceAsStream("/potions.json");
        if (inputStream == null) {
            throw new IllegalStateException("potions.json non trovato tra le risorse.");
        }

        final Gson gson = new Gson();
        final Type listType = new TypeToken<List<PotionData>>() {}.getType();
        final List<PotionData> potionDataList = gson.fromJson(new InputStreamReader(inputStream), listType);

        final Map<String, PotionData> dataByType = new HashMap<>();
        for (PotionData data : potionDataList) {
            dataByType.put(data.type(), data);
        }
        return dataByType;
    }

    public List<MonsterData> loadMonsters() {
        final List<MonsterData> monsters = new ArrayList<>();

        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();

            final InputStream inputStream = this.getClass().getResourceAsStream("/monsters.xml");
            if (inputStream == null) {
                throw new IllegalStateException("monsters.xml non trovato tra le risorse.");
            }

            final Document document = builder.parse(inputStream);
            document.getDocumentElement().normalize();

            final NodeList monsterNodes = document.getElementsByTagName("monster");
            for (int i = 0; i < monsterNodes.getLength(); i++) {
                monsters.add(createMonsterDataFromElement((Element) monsterNodes.item(i)));
            }
        } catch (Exception e) {
            throw new IllegalStateException("Impossibile caricare monsters.xml", e);
        }

        return monsters;
    }

    private MonsterData createMonsterDataFromElement(final Element monsterElement) {
        return new MonsterData(
                getTextContent(monsterElement, "type"),
                getTextContent(monsterElement, "name"),
                Integer.parseInt(getTextContent(monsterElement, "hpMax")),
                Integer.parseInt(getTextContent(monsterElement, "attack")),
                Integer.parseInt(getTextContent(monsterElement, "defense")),
                Integer.parseInt(getTextContent(monsterElement, "spawnWeight"))
        );
    }

    private String getTextContent(final Element element, final String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }

    public record PotionData(
            String type,
            String name,
            String description,
            int hpMaxBonus,
            int attackBonus,
            int defenseBonus,
            Map<String, Integer> requiredIngredients
    ) {
    }

    public record MonsterData(
            String type,
            String name,
            int hpMax,
            int attack,
            int defense,
            int spawnWeight
    ) {
    }
}