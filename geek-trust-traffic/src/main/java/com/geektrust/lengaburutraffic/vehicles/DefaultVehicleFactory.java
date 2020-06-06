package com.geektrust.lengaburutraffic.vehicles;

import com.geektrust.lengaburutraffic.weather.WeatherType;

import java.util.ArrayList;
import java.util.List;

public class DefaultVehicleFactory implements VehicleFactory{

    public Vehicle createBike(){
        List<WeatherType> weatherTypes = new ArrayList<>();
        weatherTypes.add(WeatherType.SUNNY);
        weatherTypes.add(WeatherType.WINDY);
        return new Vehicle(VehicleType.BIKE,10.0,2.0,weatherTypes);
    }

    public Vehicle createTukTuk(){
        List<WeatherType> weatherTypes = new ArrayList<>();
        weatherTypes.add(WeatherType.SUNNY);
        weatherTypes.add(WeatherType.RAINY);
        return new Vehicle(VehicleType.TUKTUK,12.0,1.0,weatherTypes);
    }

    public Vehicle createCar(){
        List<WeatherType> weatherTypes = new ArrayList<>();
        weatherTypes.add(WeatherType.SUNNY);
        weatherTypes.add(WeatherType.RAINY);
        weatherTypes.add(WeatherType.WINDY);
        return new Vehicle(VehicleType.CAR,20.0,3.0,weatherTypes);
    }

    public Vehicle createVehicle(VehicleType vehicleType){
        if(vehicleType.equals(VehicleType.BIKE))
            return  createBike();
        else if(vehicleType.equals(VehicleType.TUKTUK))
            return  createTukTuk();
        else  if(vehicleType.equals(VehicleType.CAR))
            return  createCar();
        else
            return null;
    }
}
