package com.example.carbonfootprinttrackerfinal;



public class Vehicle {
    private String regNumber;
    private String type;
    private double mileage;
    private  String vehicleEmissions;

    public Vehicle(){}

    public Vehicle(String id, String regNumber, int litres, String type, String vehicleEmissions) {

    }

    public Vehicle(String regNumber, String type, double mileage, String vehicleEmissions) {
        super();
        this.regNumber = regNumber;
        this.type = type;
        this.mileage = mileage;
        this.vehicleEmissions = vehicleEmissions;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getVehicleEmissions() {
        return vehicleEmissions;
    }

    public void setVehicleEmissions(String vehicleEmissions) {
        this.vehicleEmissions = vehicleEmissions;
    }
}

