package com.demo.itinerario.entities;

import java.time.OffsetDateTime;

public class FlightLeg {
    private String carrier;
    private String number;
    private OffsetDateTime depart;
    private OffsetDateTime arrive;
    private int stops;

    public FlightLeg() {}
    public FlightLeg(String carrier, String number, OffsetDateTime depart, OffsetDateTime arrive, int stops) {
        this.carrier = carrier;
        this.number = number;
        this.depart = depart;
        this.arrive = arrive;
        this.stops = stops;
    }

    public String getCarrier() { return carrier; }
    public void setCarrier(String carrier) { this.carrier = carrier; }
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public OffsetDateTime getDepart() { return depart; }
    public void setDepart(OffsetDateTime depart) { this.depart = depart; }
    public OffsetDateTime getArrive() { return arrive; }
    public void setArrive(OffsetDateTime arrive) { this.arrive = arrive; }
    public int getStops() { return stops; }
    public void setStops(int stops) { this.stops = stops; }
}
