package com.geektrust.war.planet;

import com.geektrust.war.army.Army;

public class Planet {

    private final String name;
    private final Army army;

    public Planet(String name, Army army){
        this.name = name;
        this.army = army;
    }

    public String getName() {
        return name;
    }

    public Army getArmy() {
        return army;
    }
}
