package com.demo.itinerario.entities;

import java.time.LocalDate;

public class HotelInfo {
    private String name;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private String roomType;
    private double rating;

    public HotelInfo() {}
    public HotelInfo(String name, LocalDate checkIn, LocalDate checkOut, String roomType, double rating) {
        this.name = name;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.roomType = roomType;
        this.rating = rating;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getCheckIn() { return checkIn; }
    public void setCheckIn(LocalDate checkIn) { this.checkIn = checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public void setCheckOut(LocalDate checkOut) { this.checkOut = checkOut; }
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}

