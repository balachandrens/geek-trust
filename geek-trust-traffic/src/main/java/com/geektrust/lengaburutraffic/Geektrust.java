package com.geektrust.lengaburutraffic;



import com.geektrust.lengaburutraffic.Planner.DefaultRoutePlannerCreator;
import com.geektrust.lengaburutraffic.Planner.RoutePlanner;
import com.geektrust.lengaburutraffic.Planner.RoutePlannerCreator;
import com.geektrust.lengaburutraffic.weather.WeatherType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Geektrust {

    public void processInput(String[] input){
        try {
            WeatherType weatherType = WeatherType.valueOf(input[0]);
            List<Double> orbitSpeedList = new ArrayList<>(Arrays.asList(Double.valueOf(input[1]),Double.valueOf(input[2])));
            RoutePlannerCreator routePlannerCreator = new DefaultRoutePlannerCreator();
            RoutePlanner routePlanner = routePlannerCreator.createRoutePlanner(orbitSpeedList);
            String result = routePlanner.calculateFastestMode(weatherType).getResultString();
            System.out.println(result);
        } catch (Exception e){
            System.out.println("Input processing failed");
        }
    }

    public static void main(String[] args) {
        if(args[0].isEmpty()) {
            System.out.println("Missing input file path");
            return;
        }
        try {
            File file = new File(args[0]);
            BufferedReader br = new BufferedReader(new FileReader(file));
            Geektrust geektrust = new Geektrust();
            String st;
            while ((st = br.readLine()) != null) {
                String[] input = st.split(" ");
                if(input.length < 3){
                    System.out.println("Invalid Input, Test case should be <WEATHER> <ORBIT_1_TRAFFIC_SPEED> <ORBIT_2_TRAFFIC_SPEED>");
                    continue;
                }
                geektrust.processInput(input);
            }
        } catch (Exception e){
            System.out.println("Error during processing file, Error : "+ e.getMessage());
        }
    }
}
