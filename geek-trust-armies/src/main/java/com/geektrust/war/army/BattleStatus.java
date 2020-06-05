package com.geektrust.war.army;

import java.util.Map;

public class BattleStatus {
    Map<Weapon, Integer> armoury;
    boolean isDefended;

    public BattleStatus(boolean isDefended, Map<Weapon, Integer> armoury){
        this.isDefended = isDefended;
        this.armoury = armoury;
    }

    public String getResultString(){
        return (isDefended ? "WINS" : "LOSES") +
                " " + armoury.get(Weapon.HORSE) + "H" +
                " " + armoury.get(Weapon.ELEPHANT) + "E" +
                " " + armoury.get(Weapon.ARMOURED_TANK) + "AT" +
                " " + armoury.get(Weapon.SLING_GUN) + "SG";
    }
}
