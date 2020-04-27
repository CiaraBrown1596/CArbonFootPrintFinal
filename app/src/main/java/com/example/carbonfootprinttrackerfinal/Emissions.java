package com.example.carbonfootprinttrackerfinal;

public class Emissions {

    private double TotalEmissions;
    private double TotalFuel;
    private int TotalVehicles;
    private int TotalFlights;
    private double TotalDomestic;

    public Emissions() {

    }

    public Emissions(String id, double totalEmissions, double totalFuel, int totalVehicles, int totalFlights,
                     double totalDomestic) {
        TotalEmissions = totalEmissions;
        TotalFuel = totalFuel;
        TotalVehicles = totalVehicles;
        TotalFlights = totalFlights;
        TotalDomestic = totalDomestic;
    }

    public double getTotalEmissions() {
        return TotalEmissions;
    }

    public void setTotalEmissions(double totalEmissions) {
        TotalEmissions = totalEmissions;
    }

    public double getTotalFuel() {
        return TotalFuel;
    }

    public void setTotalFuel(double totalFuel) {
        TotalFuel = totalFuel;
    }

    public int getTotalVehicles() {
        return TotalVehicles;
    }

    public void setTotalVehicles(int totalVehicles) {
        TotalVehicles = totalVehicles;
    }

    public int getTotalFlights() {
        return TotalFlights;
    }

    public void setTotalFlights(int totalFlights) {
        TotalFlights = totalFlights;
    }

    public double getTotalDomestic() {
        return TotalDomestic;
    }

    public void setTotalDomestic(double totalDomestic) {
        TotalDomestic = totalDomestic;
    }



}

