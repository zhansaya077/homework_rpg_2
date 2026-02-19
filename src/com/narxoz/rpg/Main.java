package com.narxoz.rpg;

import com.narxoz.rpg.builder.BossEnemyBuilder;
import com.narxoz.rpg.enemy.DragonBoss;
import com.narxoz.rpg.enemy.Enemy;
import com.narxoz.rpg.factory.FireComponentFactory;
import com.narxoz.rpg.factory.EnemyComponentFactory;

public class Main {

    public static void main(String[] args) {

        EnemyComponentFactory fireFactory = new FireComponentFactory();

        Enemy fireDragon = new BossEnemyBuilder()
                .setName("Infernal Dragon")
                .setHealth(60000)
                .setDamage(700)
                .setDefense(300)
                .setSpeed(40)
                .setElement("FIRE")
                .setPhases(60000, 40000, 20000)
                .setCanFly(true)
                .setHasBreathAttack(true)
                .setWingspan(25)
                .setComponentFactory(fireFactory)
                .build();

        fireDragon.displayInfo();

        DragonBoss eliteDragon = ((DragonBoss) fireDragon).clone();
        eliteDragon.multiplyStats(1.5);

        System.out.println("\n=== ELITE VARIANT ===");
        eliteDragon.displayInfo();
    }
}
