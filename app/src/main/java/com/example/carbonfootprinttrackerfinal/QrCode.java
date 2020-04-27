package com.example.carbonfootprinttrackerfinal;




public class QrCode {

    private int id;
    private String flightId;

    public QrCode() {

    }

    public QrCode(int id, String flightId) {
        super();
        this.id = id;
        this.flightId = flightId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

}


