package com.geektrust.war;

import com.geektrust.war.army.ArmouryBuilder;
import com.geektrust.war.army.Army;
import com.geektrust.war.army.Weapon;
import com.geektrust.war.exception.WeaponExhaustedException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Map;

public class InputProcessor {
    private static Army lengaburuArmy;
    private static Army falcorinaArmy;

    public static void populateDefaultArmies(){
        Map<Weapon, Integer> lengaburuArmoury = ArmouryBuilder.createArmoury(100,50,10,5);
        lengaburuArmy = new Army(2.0,lengaburuArmoury);
        Map<Weapon, Integer> falcorinaArmoury = ArmouryBuilder.createArmoury(300,200,40,20);
        falcorinaArmy = new Army(1.0,falcorinaArmoury);
    }

    private static Map<Weapon,Integer> createArmouryFromInput(String[] input){
        Integer horseCount = Integer.valueOf(input[1].substring(0,input[1].length()-1));
        Integer elephantCount = Integer.valueOf(input[2].substring(0,input[2].length()-1));
        Integer tankCount = Integer.valueOf(input[3].substring(0,input[3].length()-2));
        Integer gunCount = Integer.valueOf(input[4].substring(0,input[4].length()-2));
        return ArmouryBuilder.createArmoury(horseCount,elephantCount,tankCount,gunCount);
    }

    private static void processInputString(String[] input) {
        try {
            Map<Weapon,Integer> armoury = InputProcessor.createArmouryFromInput(input);
            Army deployedArmy = falcorinaArmy.deployArmy(armoury);
            String result = lengaburuArmy.defend(deployedArmy).getResultString();
            if (null == result || result.isEmpty())
                System.out.println("Failed to predict result for input <" + Arrays.toString(input) + ">");
            else
                System.out.println(result);
        } catch (WeaponExhaustedException e) {
            System.out.println("Failed to predict result, Input " + Arrays.toString(input) + "> Error : " + e.getMessage());
        }
    }

    private static void validateAndProcessInputString(String[] input) {
        if (input.length >= 5)
            InputProcessor.processInputString(input);
        else
            System.out.println("Invalid Input, Test case should be FALICORNIA_ATTACK <HORSE_COUNT> <ELEPHANT_COUNT> <TANK_COUNT> <GUN_COUNT>");
    }

    public static void processInputFile(String fileName){
        try {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                InputProcessor.validateAndProcessInputString(st.split(" "));
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error during processing file, Error : "+ e.getMessage());
        }
    }
}
