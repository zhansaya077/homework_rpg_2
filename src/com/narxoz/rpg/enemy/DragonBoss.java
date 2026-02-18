package com.narxoz.rpg.enemy;

import com.narxoz.rpg.combat.Ability;
import com.narxoz.rpg.loot.LootTable;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class DragonBoss implements Enemy, Cloneable {

   
    private String name;
    private int health;
    private int damage;
    private int defense;
    private int speed;

  
    private String element;

    
    private List<Ability> abilities;

  
    private Map<Integer, Integer> phases;

   
    private LootTable lootTable;


    private String aiBehavior;


    private boolean canFly;
    private boolean hasBreathAttack;
    private int wingspan;


    public DragonBoss(String name, int health, int damage, int defense,
                      int speed, String element,
                      List<Ability> abilities,
                      int phase1Threshold, int phase2Threshold, int phase3Threshold,
                      LootTable lootTable, String aiBehavior,
                      boolean canFly, boolean hasBreathAttack, int wingspan) {

        this.name = name;
        this.health = health;
        this.damage = damage;
        this.defense = defense;
        this.speed = speed;
        this.element = element;

        this.abilities = abilities != null ? abilities : new ArrayList<>();

        this.phases = new HashMap<>();
        this.phases.put(1, phase1Threshold);
        this.phases.put(2, phase2Threshold);
        this.phases.put(3, phase3Threshold);

        this.lootTable = lootTable;
        this.aiBehavior = aiBehavior;

        this.canFly = canFly;
        this.hasBreathAttack = hasBreathAttack;
        this.wingspan = wingspan;
    }

  

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void displayInfo() {

        System.out.println("=== " + name + " (Dragon Boss) ===");
        System.out.println("Health: " + health + " | Damage: " + damage
                + " | Defense: " + defense + " | Speed: " + speed);
        System.out.println("Element: " + element);

        System.out.println("Abilities (" + abilities.size() + "):");
        for (Ability ability : abilities) {
            System.out.println(" - " + ability.getName()
                    + " (" + ability.getDamage() + " dmg)");
        }

        System.out.println("Boss Phases:");
        for (Map.Entry<Integer, Integer> phase : phases.entrySet()) {
            System.out.println("  Phase " + phase.getKey()
                    + " triggers at " + phase.getValue() + " HP");
        }

        System.out.println("AI Behavior: " + aiBehavior);
        System.out.println("Can Fly: " + canFly
                + " | Breath Attack: " + hasBreathAttack
                + " | Wingspan: " + wingspan);

        if (lootTable != null) {
            System.out.println("Gold Drop: " + lootTable.getGoldDrop());
            System.out.println("XP Drop: " + lootTable.getExperienceDrop());
        }
    }

    

    @Override
    public DragonBoss clone() {

        // Deep copy abilities
        List<Ability> clonedAbilities = new ArrayList<>();
        if (this.abilities != null) {
            for (Ability ability : this.abilities) {
                clonedAbilities.add(ability.clone());
            }
        }

        
        Map<Integer, Integer> clonedPhases = new HashMap<>(this.phases);

        
        LootTable clonedLoot = this.lootTable != null
                ? this.lootTable.clone()
                : null;

        return new DragonBoss(
                this.name,
                this.health,
                this.damage,
                this.defense,
                this.speed,
                this.element,
                clonedAbilities,
                clonedPhases.getOrDefault(1, 0),
                clonedPhases.getOrDefault(2, 0),
                clonedPhases.getOrDefault(3, 0),
                clonedLoot,
                this.aiBehavior,
                this.canFly,
                this.hasBreathAttack,
                this.wingspan
        );
    }


    public void setElement(String element) {
        this.element = element;
    }

    public void multiplyStats(double multiplier) {
        this.health = (int) (this.health * multiplier);
        this.damage = (int) (this.damage * multiplier);
        this.defense = (int) (this.defense * multiplier);
    }
}
