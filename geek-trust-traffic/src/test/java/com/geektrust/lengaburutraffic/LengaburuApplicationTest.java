package com.geektrust.lengaburutraffic;

import com.geektrust.lengaburutraffic.Planner.DefaultRoutePlannerCreator;
import com.geektrust.lengaburutraffic.Planner.RoutePlanner;
import com.geektrust.lengaburutraffic.Planner.RoutePlannerCreator;
import com.geektrust.lengaburutraffic.Planner.TrafficStatus;
import com.geektrust.lengaburutraffic.vehicles.VehicleType;
import com.geektrust.lengaburutraffic.weather.WeatherType;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class LengaburuApplicationTest {

    @Parameter(0)
    public String weatherTypeStr;
    @Parameter(1)
    public Double orbit1Speed;
    @Parameter(2)
    public Double orbit2Speed;
    @Parameter(3)
    public VehicleType vehicleType;
    @Parameter(4)
    public String orbitName;


    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                { "SUNNY" , 12.0, 10.0, VehicleType.TUKTUK, "ORBIT1"},
                { "WINDY" , 14.0, 20.0, VehicleType.CAR, "ORBIT2"},
                { "RAINY" , 8.0,  15.0, VehicleType.TUKTUK, "ORBIT2"},
                { "RAINY" , 22.0, 12.0, VehicleType.TUKTUK, "ORBIT2"}};
        return Arrays.asList(data);
    }

    @Test
    public void testFasterRideMode() {
        RoutePlannerCreator routePlannerCreator = new DefaultRoutePlannerCreator();
        List<Double> orbitSpeedList = new ArrayList<>(Arrays.asList(orbit1Speed,orbit2Speed));
        RoutePlanner routePlanner = routePlannerCreator.createRoutePlanner(orbitSpeedList);
        WeatherType weatherType = WeatherType.valueOf(weatherTypeStr);
        TrafficStatus trafficStatus = routePlanner.calculateFastestMode(weatherType);
        assertEquals(vehicleType,trafficStatus.getVehicle().getVehicleType());
        assertEquals(orbitName,trafficStatus.getOrbit().getOrbitName());
    }
}
