package com.geektrust.war.army;

import com.geektrust.war.exception.ProcessingFailedException;
import com.geektrust.war.exception.WeaponExhaustedException;
import com.geektrust.war.util.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Army {

    private final Map<Weapon, Integer> armoury;
    private final Double strength;
    private final Double substitutionStrength;

    public Army(Double strength, Double substitutionStrength, Map<Weapon, Integer> armoury){
        if(null == armoury){
            this.armoury = new HashMap<>();
        }
        else {
            this.armoury = new HashMap<>(armoury);
        }
        this.strength = strength;
        this.substitutionStrength = substitutionStrength;
    }

    private class WeaponAllocation {
        public Weapon weapon;
        public Integer requiredCount;
        private Integer pendingCount;
        public  WeaponAllocation(Weapon weapon,Integer actualCount){
            this.weapon = weapon;
            this.requiredCount = 0;
            this.pendingCount = actualCount;
        }
        public boolean utilize(Integer count){
            if(pendingCount >= count){
                requiredCount += count;
                pendingCount -= count;
                return true;
            }
            return false;
        }
        public void utilizeAll() {
            requiredCount += pendingCount;
            pendingCount = 0;
        }
    }

    public Double getStrength() {
        return strength;
    }

    public Double getSubstitutionStrength() {
        return substitutionStrength;
    }

    public Integer getWeaponCount(Weapon weapon){
        if(armoury.containsKey(weapon)){
            return armoury.get(weapon);
        } else {
            return 0;
        }
    }

    public void updateWeaponCount(Weapon weapon, Integer count){
        if(armoury.containsKey(weapon)){
            armoury.put(weapon,armoury.get(weapon)+count);
        } else {
            armoury.put(weapon,count);
        }
    }

    public Army deployArmy(Map<Weapon, Integer> armoury) throws WeaponExhaustedException{
        Army army = new Army(this.strength,this.substitutionStrength,null);
        for(Weapon weapon : armoury.keySet()){
            if(this.getWeaponCount(weapon) >= armoury.get(weapon)){
                army.updateWeaponCount(weapon,armoury.get(weapon));
            } else {
                throw new WeaponExhaustedException("Unable to deploy, not enough weapons for : "+weapon);
            }
        }
        return army;
    }

    public String defend(Army enemy) throws ProcessingFailedException {
        String result = "";
        if( null == enemy ){
            throw new ProcessingFailedException("Armies not found !");
        }
        result = getRequiredWeaponCounts(enemy);
        return  result;
    }

    private String getRequiredWeaponCounts(Army enemy){
        Double strength = this.getStrength()/enemy.getStrength();
        Double substitutionStrength = this.getSubstitutionStrength();
        Integer substitutionCount = 0;
        boolean battleWon = true;
        List<WeaponAllocation> weaponAllocations = new ArrayList<>();
        int index = 0;
        for(Weapon weapon: Weapon.values()){
            WeaponAllocation weaponAllocation = new WeaponAllocation(weapon,getWeaponCount(weapon));
            weaponAllocations.add(weaponAllocation);
            Integer requiredWeaponCount =  (int)Math.ceil(enemy.getWeaponCount(weapon)/strength);
            if(weaponAllocations.get(index).utilize(requiredWeaponCount)){
                //Assumes substitutionCount is the count required in current weapon terms.
                if(substitutionCount > 0){
                    if(!weaponAllocations.get(index).utilize(substitutionCount)){
                        battleWon = false;
                        weaponAllocations.get(index).utilizeAll();
                    }
                    substitutionCount = 0;
                }
            } else {
                if(substitutionCount > 0) {
                    battleWon = false;
                }
                substitutionCount = requiredWeaponCount - weaponAllocations.get(index).pendingCount;
                weaponAllocations.get(index).utilizeAll();
                if( index-1 >= 0 ){
                    Integer requirement = (int)Math.ceil(substitutionCount*substitutionStrength);
                    if(weaponAllocations.get(index-1).utilize((int)Math.ceil(requirement))){
                        substitutionCount = 0;
                    } else {
                        requirement = requirement - weaponAllocations.get(index-1).pendingCount;
                        weaponAllocations.get(index-1).utilizeAll();
                        substitutionCount = (int)Math.ceil(requirement/substitutionStrength/substitutionStrength);
                    }
                }
                else {
                    substitutionCount = (int)Math.ceil(substitutionCount/substitutionStrength);
                }
            }
            index++;
        }
        if(substitutionCount > 0) {
            battleWon = false;
        }
        return getResultInOutputFormat(battleWon,weaponAllocations);
    }

    private String getResultInOutputFormat(boolean battleWon, List<WeaponAllocation> weaponAllocations){
        StringBuilder resultBuilder = new StringBuilder();
        if(battleWon){
            resultBuilder.append("WINS");
        } else {
            resultBuilder.append("LOSES");
        }

        resultBuilder.append(" ").append(weaponAllocations.get(0).requiredCount).append("H");
        resultBuilder.append(" ").append(weaponAllocations.get(1).requiredCount).append("E");
        resultBuilder.append(" ").append(weaponAllocations.get(2).requiredCount).append("AT");
        resultBuilder.append(" ").append(weaponAllocations.get(3).requiredCount).append("SG");
        return resultBuilder.toString();
    }

}
