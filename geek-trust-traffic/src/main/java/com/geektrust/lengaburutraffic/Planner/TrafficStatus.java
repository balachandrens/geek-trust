package com.geektrust.lengaburutraffic.Planner;

import com.geektrust.lengaburutraffic.vehicles.Vehicle;

public class TrafficStatus {
    private Vehicle vehicle;
    private Orbit orbit;
    private Double timeTaken;

    public TrafficStatus(){
        this.timeTaken = 0.0;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Orbit getOrbit() {
        return orbit;
    }

    public Double getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Double timeTaken) {
        this.timeTaken = timeTaken;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setOrbit(Orbit orbit) {
        this.orbit = orbit;
    }

    public String getResultString(){
        if(null == vehicle || null == orbit) return "";
        return vehicle.getVehicleType().name()
            + " "
            + orbit.getOrbitName();
    }
}

