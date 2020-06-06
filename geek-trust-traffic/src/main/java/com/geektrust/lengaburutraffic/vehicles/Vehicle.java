package com.geektrust.lengaburutraffic.vehicles;

import com.geektrust.lengaburutraffic.weather.WeatherType;
import java.util.ArrayList;
import java.util.List;


public class Vehicle {

    private final VehicleType vehicleType;
    private final Double speed;
    private final Double craterOverTakeTime;
    private final List<WeatherType> suitableWeathersConditions;

    public Vehicle(VehicleType vehicleType, Double speed, Double craterOverTakeTime, List<WeatherType> suitableWeathersConditions){
        this.vehicleType = vehicleType;
        this.speed = speed;
        this.craterOverTakeTime = craterOverTakeTime;
        this.suitableWeathersConditions = new ArrayList<>();
        this.suitableWeathersConditions.addAll(suitableWeathersConditions);
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Double getSpeed() {
        return speed;
    }

    public Double getCraterOverTakeTime() {
        return craterOverTakeTime;
    }

    public boolean isSuitableForWeatherCondition(WeatherType weatherType) {
        return suitableWeathersConditions.contains(weatherType);
    }
}
