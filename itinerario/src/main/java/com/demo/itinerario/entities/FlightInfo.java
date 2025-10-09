package com.demo.itinerario.entities;

public class FlightInfo {
    private FlightLeg outbound;
    private FlightLeg inbound;

    public FlightInfo() {}
    public FlightInfo(FlightLeg outbound, FlightLeg inbound) {
        this.outbound = outbound;
        this.inbound = inbound;
    }

    public FlightLeg getOutbound() { return outbound; }
    public void setOutbound(FlightLeg outbound) { this.outbound = outbound; }
    public FlightLeg getInbound() { return inbound; }
    public void setInbound(FlightLeg inbound) { this.inbound = inbound; }
}

