package com.example.carbonfootprinttrackerfinal;



public class Vehicle {
    private String VehicleReg;
    private String Description;
    private String LitersUsed;
    private String VehicleEmissions;
    private String Date;
    private String Time;

    public Vehicle(){}

    public Vehicle(String id, String VehicleReg, String LitersUsed, String Description, String VehicleEmissions,String date, String time) {

    }

    public Vehicle(String VehicleReg, String Description, String LitersUsed, String VehicleEmissions, String Date, String Time) {
        super();
        this.VehicleReg = VehicleReg;
        this.Description = Description;
        this.LitersUsed = LitersUsed;
        this.VehicleEmissions = VehicleEmissions;
        this.Date = Date;
        this.Time = Time;
    }

    public String getVehicleReg() {
        return VehicleReg;
    }

    public void setVehicleReg(String VehicleReg) {
        this.VehicleReg = VehicleReg;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getLitersUsed() {
        return LitersUsed;
    }

    public void setLitersUsed(String LitersUsed) {
        this.LitersUsed = LitersUsed;
    }

    public String getVehicleEmissions() {
        return VehicleEmissions;
    }

    public void setVehicleEmissions(String VehicleEmissions) {
        this.VehicleEmissions = VehicleEmissions;
    }

    public String getDate() {
        return Date;
    }

    public Vehicle setDate(String date) {
        Date = date;
        return this;
    }

    public String getTime() {
        return Time;
    }

    public Vehicle setTime(String time) {
        Time = time;
        return this;
    }
}

