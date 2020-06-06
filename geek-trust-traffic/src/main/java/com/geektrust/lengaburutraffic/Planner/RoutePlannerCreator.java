package com.geektrust.lengaburutraffic.Planner;

import com.geektrust.lengaburutraffic.vehicles.Vehicle;
import com.geektrust.lengaburutraffic.weather.WeatherInfo;
import java.util.List;

public interface RoutePlannerCreator {

    WeatherInfo createWeatherInfo();

    List<Vehicle> createVehicles();

    List<Orbit> createOrbits(List<Double> orbitSpeedList);

    RoutePlanner createRoutePlanner(List<Double> orbitSpeedList);
}
