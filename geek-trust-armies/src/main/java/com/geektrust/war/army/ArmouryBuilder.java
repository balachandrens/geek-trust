package com.geektrust.war.army;

import java.util.HashMap;
import java.util.Map;

public class ArmouryBuilder {
    public static Map<Weapon, Integer> createArmoury(Integer horseCount, Integer elephantCount, Integer tankCount, Integer gunCount){
        Map<Weapon, Integer> armoury = new HashMap<>();
        armoury.put(Weapon.HORSE,horseCount);
        armoury.put(Weapon.ELEPHANT,elephantCount);
        armoury.put(Weapon.ARMOURED_TANK,tankCount);
        armoury.put(Weapon.SLING_GUN,gunCount);
        return  armoury;
    }
}
