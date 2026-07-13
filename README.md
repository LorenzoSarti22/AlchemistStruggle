# AlchemistStruggle

AlchemistStruggle è un piccolo RPG turn-based sviluppato in Java con interfaccia grafica Swing per il corso di Metodologie di Programmazione per apprendere concetti approfonditi a lezione, tra cui:

- programmazione ad oggetti
- GUI Swing
- persistenza JSON
- parsing XML
- testing con JUnit
- Gradle e Git

## Funzionalità

- combattimento turn-based
- inventario
- sistema di alchimia (crafting di pozioni tramite ricette)
- raccolta ingredienti dai nemici sconfitti
- utilizzo pozioni (cura e potenziamenti permanenti)
- save/load della partita
- caricamento nemici da XML con spawn pesato
- GUI Swing

## Tecnologie utilizzate

| Tecnologia | Utilizzo |
| --- | --- |
| Java 25 | Linguaggio principale |
| Swing | GUI |
| Gson 2.11.0 | Serializzazione JSON |
| DOM Parser | Parsing XML |
| JUnit 5 | Testing |
| Gradle 9.1 | Build system |
| Git/GitHub | Versionamento |

## Clone del progetto

```
git clone https://github.com/LorenzoSarti22/AlchemistStruggle.git
cd AlchemistStruggle
```

## Build ed esecuzione

### Build

```
./gradlew build
```

### Avvio

```
./gradlew run
```

### Test

```
./gradlew test
```

## Struttura del progetto

- `model` — logica di gioco (Game, GameFactory)
- `model.characters` — alchimista e nemici (Alchemist, Monster, Slime, Golem, Fairy)
- `model.items` — oggetti, ingredienti e pozioni (Item, Ingredient, Potion, PotionType)
- `model.alchemy` — sistema di crafting (Laboratory)
- `persistence` — salvataggio/caricamento e dati statici (GameRepository, StaticDataRepository, TypeAdapter)
- `panel` — interfaccia grafica Swing (GamePanel, LaboratoryPanel)

## Uso di strumenti AI

Durante lo sviluppo sono stati utilizzati strumenti AI come supporto principalmente per:

- debugging
- refactor
- chiarimenti teorici
- documentazione
- interfaccia grafica 
- testing
