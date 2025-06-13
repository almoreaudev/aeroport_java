package models;

import java.time.LocalDateTime;

public class Vol {
    private int id;
    private String volNumber;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String status;
    private String aircraft;

    // Constructeurs
    public Vol() {}

    public Vol(int id, String volNumber, String departureAirport, String arrivalAirport,
                  LocalDateTime departureTime, LocalDateTime arrivalTime, String status, String aircraft) {
        this.id = id;
        this.volNumber = volNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.aircraft = aircraft;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVolNumber() {
        return volNumber;
    }

    public void setVolNumber(String volNumber) {
        this.volNumber = volNumber;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    // Méthode toString (utile pour debug)
    @Override
    public String toString() {
        return "Vol{" +
                "id=" + id +
                ", volNumber='" + volNumber + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", status='" + status + '\'' +
                ", aircraft='" + aircraft + '\'' +
                '}';
    }
}
