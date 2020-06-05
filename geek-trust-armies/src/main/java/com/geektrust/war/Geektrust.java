package com.geektrust.war;


public class Geektrust {

    public static void main(String[] args) {
        if (0 == args.length || args[0].isEmpty()){
            System.out.println("Usage : java -jar geektrust.jar <path-to-input-file>");
        } else {
            InputProcessor.populateDefaultArmies();
            InputProcessor.processInputFile(args[0]);
        }
    }

}
