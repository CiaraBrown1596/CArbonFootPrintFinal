package com.example.carbonfootprinttrackerfinal;



import java.util.Date;


public class Fuel {

    private String vehiclesRegNumber;
    private String fuelType;
    private String fuelDate;
    private double fuelAmount;

    public Fuel(String id, String reg, Double amount, String type, String date) {

    }
    public Fuel(){}


    public Fuel(String vehiclesRegNumber, String fuelType, String fuelDate, double fuelAmount) {
        this.vehiclesRegNumber = vehiclesRegNumber;
        this.fuelType = fuelType;
        this.fuelDate = fuelDate;
        this.fuelAmount = fuelAmount;
    }

    public String getVehiclesRegNumber() {
        return vehiclesRegNumber;
    }

    public void setVehiclesRegNumber(String vehiclesRegNumber) {
        this.vehiclesRegNumber = vehiclesRegNumber;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getFuelDate() {
        return fuelDate;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(double fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public void setFuelDate(String date) {
    }
}


