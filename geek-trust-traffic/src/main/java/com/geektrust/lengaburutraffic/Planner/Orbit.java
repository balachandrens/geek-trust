package com.geektrust.lengaburutraffic.Planner;

public class Orbit {

    private final String orbitName;
    private final Double distance;
    private final Integer craterCount;
    private final Double trafficSpeed;

    public Orbit(String orbitName, Double distance, Integer craterCount, Double trafficSpeed) {
        this.orbitName = orbitName;
        this.distance = distance;
        this.craterCount = craterCount;
        this.trafficSpeed = trafficSpeed;
    }

    public String getOrbitName() {
        return orbitName;
    }

    public Double getDistance() {
        return distance;
    }

    public Integer getCraterCount() {
        return craterCount;
    }

    public Double getTrafficSpeed() {
        return trafficSpeed;
    }

}
