package com.geektrust.war.army;

import com.geektrust.war.exception.WeaponExhaustedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Army {

    private final Map<Weapon, Integer> armoury;
    private final Double strength;
    private boolean isDefended;

    public Army(Double strength, Map<Weapon, Integer> armoury){
        if(null == armoury){
            this.armoury = new HashMap<>();
        }
        else {
            this.armoury = new HashMap<>(armoury);
        }
        this.strength = strength;
        isDefended = true;
    }

    private static class WeaponAllocation {
        public Weapon weapon;
        public Integer requiredCount;
        private Integer pendingCount;
        public  WeaponAllocation(Weapon weapon,Integer actualCount){
            this.weapon = weapon;
            this.requiredCount = 0;
            this.pendingCount = actualCount;
        }
        public Integer utilize(Integer count){
            if (pendingCount >= count) {
                allocate(count);
                count = 0;
            } else {
                count = count - pendingCount;
                allocate(pendingCount);
            }
            return count;
        }
        private void allocate(Integer count){
            requiredCount += count;
            pendingCount -= count;
        }
    }

    public Double getStrength() {
        return strength;
    }

    public Integer getWeaponCount(Weapon weapon){
        if (!armoury.containsKey(weapon)) return 0;
        return armoury.get(weapon);
    }

    public void updateWeaponCount(Weapon weapon, Integer count){
        if(armoury.containsKey(weapon)){
            armoury.put(weapon,armoury.get(weapon)+count);
        } else {
            armoury.put(weapon,count);
        }
    }

    public Army deployArmy(Map<Weapon, Integer> armoury) throws WeaponExhaustedException{
        Army army = new Army(this.strength,null);
        for(Weapon weapon : armoury.keySet()){
            if(this.getWeaponCount(weapon) >= armoury.get(weapon)){
                army.updateWeaponCount(weapon,armoury.get(weapon));
            } else {
                throw new WeaponExhaustedException("Unable to deploy, not enough weapons for : "+weapon);
            }
        }
        return army;
    }

    public BattleStatus defend(Army enemy){
        Integer substitutionCount = 0;
        List<WeaponAllocation> weaponAllocations = new ArrayList<>();
        int index = 0;
        for(Weapon weapon: Weapon.values()){
            weaponAllocations.add(new WeaponAllocation(weapon, getWeaponCount(weapon)));
            Integer requiredWeaponCount = (int)Math.ceil(enemy.getWeaponCount(weapon)/getStrength());
            substitutionCount = allocateWeapon(requiredWeaponCount, weaponAllocations,index,substitutionCount);
            index++;
        }
        isDefended = substitutionCount == 0;
        return createBattleStatus(weaponAllocations);
    }

    private Integer allocateWeapon(Integer requiredWeaponCount, List<WeaponAllocation> weaponAllocations, Integer index, Integer substitutionCount){
        requiredWeaponCount = weaponAllocations.get(index).utilize(requiredWeaponCount);
        if(requiredWeaponCount > 0){
            if(substitutionCount > 0)  isDefended = false;
            substitutionCount = utilizeLowerWeapon(weaponAllocations, index, requiredWeaponCount);
        } else {
            if (substitutionCount > 0 && weaponAllocations.get(index).utilize(substitutionCount) != 0) isDefended = false;
            substitutionCount = 0;
        }
        return  substitutionCount;
    }

    private Integer utilizeLowerWeapon(List<WeaponAllocation> weaponAllocations, Integer index,Integer substitutionCount){
        if (index - 1 >= 0) {
            substitutionCount = (int)Math.ceil(substitutionCount*getStrength());
            substitutionCount = weaponAllocations.get(index - 1).utilize(substitutionCount);
            return (int)Math.ceil(substitutionCount/getStrength()/getStrength());
        }
        return (int)Math.ceil(substitutionCount/getStrength());
    }

    private BattleStatus createBattleStatus(List<WeaponAllocation> weaponAllocations){
        Map<Weapon, Integer> armoury = new HashMap<>();
        for(WeaponAllocation allocation : weaponAllocations)
            armoury.put(allocation.weapon,allocation.requiredCount);
        return new BattleStatus(isDefended,armoury);
    }
}
