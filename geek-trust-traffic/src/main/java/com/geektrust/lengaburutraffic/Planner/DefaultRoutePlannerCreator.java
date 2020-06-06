package com.geektrust.lengaburutraffic.Planner;

import com.geektrust.lengaburutraffic.vehicles.Vehicle;
import com.geektrust.lengaburutraffic.vehicles.DefaultVehicleFactory;
import com.geektrust.lengaburutraffic.vehicles.VehicleFactory;
import com.geektrust.lengaburutraffic.vehicles.VehicleType;
import com.geektrust.lengaburutraffic.weather.WeatherInfo;
import com.geektrust.lengaburutraffic.weather.WeatherType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultRoutePlannerCreator implements RoutePlannerCreator {

    public WeatherInfo createWeatherInfo(){
        return new WeatherInfo.WeatherInfoBuilder().addWeather(WeatherType.SUNNY,-10.0)
                .addWeather(WeatherType.RAINY,20.0)
                .addWeather(WeatherType.WINDY,0.0).build();
    }


    public List<Vehicle> createVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();
        VehicleFactory vehicleFactory = new DefaultVehicleFactory();
        vehicles.add(vehicleFactory.createVehicle(VehicleType.BIKE));
        vehicles.add(vehicleFactory.createVehicle(VehicleType.TUKTUK));
        vehicles.add(vehicleFactory.createVehicle(VehicleType.CAR));
        return vehicles;
    }

    @Override
    public List<Orbit> createOrbits(List<Double> orbitSpeedList) {
        Orbit orbit1 = new Orbit("ORBIT1",18.0,20,orbitSpeedList.get(0));
        Orbit orbit2 = new Orbit("ORBIT2",20.0,10,orbitSpeedList.get(1));
        return new ArrayList<>(Arrays.asList(orbit1,orbit2));
    }

    @Override
    public RoutePlanner createRoutePlanner(List<Double> orbitSpeedList) {
        WeatherInfo weatherInfo = createWeatherInfo();
        List<Vehicle> vehicles = createVehicles();
        List<Orbit> orbits = createOrbits(orbitSpeedList);
        return new RoutePlanner(orbits,vehicles,weatherInfo);
    }

}
