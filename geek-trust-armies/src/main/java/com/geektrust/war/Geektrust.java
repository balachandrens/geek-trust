package com.geektrust.war;

import com.geektrust.war.army.Army;
import com.geektrust.war.planet.Planet;
import com.geektrust.war.util.ArmouryBuilder;
import com.geektrust.war.util.Weapon;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;


public class Geektrust {

    public static void main(String[] args) {

        Map<Weapon, Integer> lengaburuArmoury = ArmouryBuilder.createArmoury(100,50,10,5);
        Army lengaburuArmy = new Army(2.0,2.0,lengaburuArmoury);
        Planet lengaburu = new Planet("Lengaburu",lengaburuArmy);

        Map<Weapon, Integer> falcorinaArmoury = ArmouryBuilder.createArmoury(300,200,40,20);
        Army falcorinaArmy = new Army(1.0,0.0,falcorinaArmoury);
        Planet falcorina = new Planet("Falcorina",falcorinaArmy);

        if(0 == args.length || args[0].isEmpty()) {
            System.out.println("Usage : java -jar geektrust.jar <path-to-input-file>");
            return;
        }
        try {
            File file = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));
            //Reading
            String st;
            while ((st = br.readLine()) != null) {
                //validation
                String[] input = st.split(" ");
                if(input.length < 5){
                    System.out.println("Invalid Input, Test case should be FALICORNIA_ATTACK <HORSE_COUNT> <ELEPHANT_COUNT> <TANK_COUNT> <GUN_COUNT>");
                    continue;
                }
                //Translate input
                Integer horseCount = Integer.valueOf(input[1].substring(0,input[1].length()-1));
                Integer elephantCount = Integer.valueOf(input[2].substring(0,input[2].length()-1));
                Integer tankCount = Integer.valueOf(input[3].substring(0,input[3].length()-2));
                Integer gunCount = Integer.valueOf(input[4].substring(0,input[4].length()-2));
                Map<Weapon, Integer> armoury = ArmouryBuilder.createArmoury(horseCount,elephantCount,tankCount,gunCount);
                Army deployedArmy = falcorina.getArmy().deployArmy(armoury);

                //computation
                String result = lengaburu.getArmy().defend(deployedArmy);
                if (result.isEmpty() || null == result) {
                    System.out.println("Failed to predict result for input <" + st + ">");
                } else {
                    System.out.println(result);
                }
            }
        } catch (Exception e){
            System.out.println("Error during processing file, Error : "+ e.getMessage());
        }
    }
}
