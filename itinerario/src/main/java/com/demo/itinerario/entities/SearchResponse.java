package com.demo.itinerario.entities;

import java.util.List;

public class SearchResponse {
    private List<Itinerary> itineraries;
    private String searchId;

    public SearchResponse() {}
    public SearchResponse(List<Itinerary> itineraries, String searchId) {
        this.itineraries = itineraries;
        this.searchId = searchId;
    }

    public List<Itinerary> getItineraries() { return itineraries; }
    public void setItineraries(List<Itinerary> itineraries) { this.itineraries = itineraries; }
    public String getSearchId() { return searchId; }
    public void setSearchId(String searchId) { this.searchId = searchId; }
}
