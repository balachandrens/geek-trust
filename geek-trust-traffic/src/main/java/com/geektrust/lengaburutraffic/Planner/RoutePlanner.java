package com.geektrust.lengaburutraffic.Planner;

import com.geektrust.lengaburutraffic.vehicles.Vehicle;
import com.geektrust.lengaburutraffic.weather.WeatherInfo;
import com.geektrust.lengaburutraffic.weather.WeatherType;
import java.util.*;

public class RoutePlanner {

    private final List<Orbit> availableOrbits;
    private final List<Vehicle> availableVehicles;
    private final WeatherInfo weatherInfo;

    public RoutePlanner(List<Orbit> availableOrbits, List<Vehicle> availableVehicles, WeatherInfo weatherInfo){
        this.availableOrbits = new ArrayList<>(availableOrbits);
        this.availableVehicles = new ArrayList<>(availableVehicles);
        this.weatherInfo = weatherInfo;
    }

    private Double timeToTravel(Vehicle vehicle, Orbit orbit, WeatherType weatherType) {
        Double maxSpeed = vehicle.getSpeed() < orbit.getTrafficSpeed() ? vehicle.getSpeed() : orbit.getTrafficSpeed();
        Double craterCount = orbit.getCraterCount() + (orbit.getCraterCount() * weatherInfo.getCraterInfluence(weatherType) / 100.0);
        return (((orbit.getDistance() / maxSpeed) * 60.0) + (vehicle.getCraterOverTakeTime() * craterCount));
    }

    private void CompareAndSetStatus(Vehicle vehicle, Orbit orbit, WeatherType weatherType, TrafficStatus trafficStatus){
        Double time;
        if (vehicle.isSuitableForWeatherCondition(weatherType)){
            time = timeToTravel(vehicle, orbit, weatherType);
            if (Double.compare(time, trafficStatus.getTimeTaken()) < 0 || trafficStatus.getTimeTaken() == 0.0) {
                trafficStatus.setTimeTaken(time);
                trafficStatus.setVehicle(vehicle);
                trafficStatus.setOrbit(orbit);
            }
        }
    }

    public TrafficStatus calculateFastestMode(WeatherType weatherType) {
        TrafficStatus trafficStatus = new TrafficStatus();
        for(Vehicle vehicle : availableVehicles)
            for(Orbit orbit : availableOrbits)
                CompareAndSetStatus(vehicle,orbit,weatherType,trafficStatus);
        return trafficStatus;
    }
}
