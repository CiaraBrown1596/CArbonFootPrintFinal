package com.example.carbonfootprinttrackerfinal;



import java.sql.Time;
import java.util.Date;

public class Flight{
    private String flightID;
    private String departure;
    private String destination;
    private String ticket;
    private String date;
    private String time;
    private String emissionPerPassenger;
    
    public Flight(){}


    public Flight(String id, String ID, Double type, Double aircraftType, String aClass, String emissionPerPassenger){

    }

    public Flight(String flightID, String departure, String destination, String ticket, String date, String time, String emissionPerPassenger){
        this.flightID = flightID;
        this.departure = departure;
        this.destination = destination;
        this.ticket = ticket;
        this.date = date;
        this.time = time;
        this.emissionPerPassenger = emissionPerPassenger;
    }

    public Flight(String id, String id1, Double type, Double aircraftType, String aClass, Double emissions) {
    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmissionPerPassenger() {
        return emissionPerPassenger;
    }

    public void setEmissionPerPassenger(String emissionPerPassenger) {
        this.emissionPerPassenger = emissionPerPassenger;
    }
}


