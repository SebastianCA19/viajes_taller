package com.demo.itinerario.entities;

public class Itinerary {
    private String id;
    private Money price;
    private FlightInfo flight;
    private HotelInfo hotel;

    public Itinerary() {}
    public Itinerary(String id, Money price, FlightInfo flight, HotelInfo hotel) {
        this.id = id;
        this.price = price;
        this.flight = flight;
        this.hotel = hotel;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Money getPrice() { return price; }
    public void setPrice(Money price) { this.price = price; }
    public FlightInfo getFlight() { return flight; }
    public void setFlight(FlightInfo flight) { this.flight = flight; }
    public HotelInfo getHotel() { return hotel; }
    public void setHotel(HotelInfo hotel) { this.hotel = hotel; }
}
