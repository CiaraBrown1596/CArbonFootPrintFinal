package com.example.carbonfootprinttrackerfinal;



import java.sql.Time;
import java.util.Date;

public class Flight{
    private String flightID;
    private String Aircraft;
    private String seat;
    private String date;
    private String Distance;
    private String time;
    private String emissionPerPassenger;
    
    public Flight(){}


    public Flight(String flightID, String aircraft, String seat, String date, String time, String emissionPerPassenger){
        this.flightID = flightID;
       this.Aircraft = aircraft;
        this.seat = seat;
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

    public String getAircraft() {
        return Aircraft;
    }

    public Flight setAircraft(String aircraft) {
        Aircraft = aircraft;
        return this;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String ticket) {
        this.seat = ticket;
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

    public String getDistance() {
        return Distance;
    }

    public Flight setDistance(String distance) {
        Distance = distance;
        return this;
    }
}


