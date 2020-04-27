package com.example.carbonfootprinttrackerfinal;

public class User {
    private int ID;
    private String firstName;
    private String surname;
    private int flights;
    private double totalEmissions;
    private int totalvehicles;
    private double totalDomestic;

    public User(String id, int userId, String name, String surname, int flights, Double emissions, int vehicles, Double domestic) {
    }

    public User(String firstName, int flights,
                           double totalEmissions, int totalvehicles, double totalDomestic) {
        this.ID = ID;
        this.firstName = firstName;
        this.surname = surname;

        this.flights = flights;
        this.totalEmissions = totalEmissions;
        this.totalvehicles = totalvehicles;
        this.totalDomestic = totalDomestic;
    }

    public User() {
    }

    public User(String id, int userId, String name, String surname, int employees, int flights, Double emissions, int vehicles, Double domestic) {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getFlights() {
        return flights;
    }

    public void setFlights(int flights) {
        this.flights = flights;
    }

    public double getTotalEmissions() {
        return totalEmissions;
    }

    public void setTotalEmissions(double totalEmissions) {
        this.totalEmissions = totalEmissions;
    }

    public int getTotalvehicles() {
        return totalvehicles;
    }

    public void setTotalvehicles(int totalvehicles) {
        this.totalvehicles = totalvehicles;
    }

    public double getTotalDomestic() {
        return totalDomestic;
    }

    public void setTotalDomestic(double totalDomestic) {
        this.totalDomestic = totalDomestic;
    }
}

